import React from 'react';
import APIResponseErrorMessage from "../commons/errorhandling/api-response-error-message";
import {
    Button,
    Card,
    CardHeader,
    Col,
    Modal,
    ModalBody,
    ModalHeader,
    Row
} from 'reactstrap';
import PersonForm from "./components/person-form";
import DeviceForm from "./components/device-form";
import * as API_USERS from "./api/person-api"
import PersonTable from "./components/person-table";
import DeviceTable from "./components/device-table";
import {Chat} from "../Client/websocketchat";



class PersonContainer extends React.Component {

    constructor(props) {
        super(props);
        this.toggleForm = this.toggleForm.bind(this);
        this.toggleDeviceForm =this.toggleDeviceForm.bind(this);
        this.reload = this.reload.bind(this);

        localStorage.setItem("adminToken",localStorage.getItem("token"))

        this.state = {
            token: localStorage.getItem("token"),
            selected: false,
            collapseForm: false,
            tableData: [],
            tableData2: [],
            isLoaded: false,
            isLoadedDevice:false,
            errorStatus: 0,
            isDeviceFormVisible: false,
            error: null
        };
        console.log("sdgyasd"+ this.token)
    }

    componentDidMount() {
        this.fetchPersons();
        this.fetchDevice();
    }
    fetchPersons() {
        console.log(this.state.token)
        return API_USERS.getPersons(this.state.token,(result, status, err) => {

            if (result !== null && (status === 200||status === 201)) {
                this.setState({
                    tableData: result,
                    isLoaded: true
                });
            } else {
                this.setState(({
                    errorStatus: status,
                    error: err
                }));
            }
        });
    }
    fetchDevice() {
        return API_USERS.getDevice(this.state.token,(result, status, err) => {
            console.log('Device result:',result,status)
            if (result !== null && (status === 200||status === 201)) {
                this.setState({
                    tableData2: result,
                    isLoadedDevice: true
                });

            } else {
                this.setState(({
                    errorStatus: status,
                    error: err
                }));
            }
        });
    }

    toggleForm() {
        this.setState({selected: !this.state.selected});
    }

    toggleDeviceForm() {
        this.setState({isDeviceFormVisible: !this.state.isDeviceFormVisible});
    }


    reload() {
        this.setState({
            isLoaded: false,
            isLoadedDevice:false
        });
        this.toggleDeviceForm();
        this.toggleForm();
        this.fetchPersons();
        this.fetchDevice();
    }

    render() {
        if(localStorage.getItem("rol")!=='ADMIN'){
            return (
                <div>
                </div>
            )
        }
        return (



            <div>
                <CardHeader>
                    <strong> Person Management </strong>
                </CardHeader>
                <Card>
                    <br/>
                    <Row>
                        <Col sm={{size: '2', offset: 1}}>
                            <Button color="primary" onClick={this.toggleForm}>CRUD Person </Button>

                        </Col>
                        <Col sm={{ size: '5', offset: 3 }}>
                            <Button color="primary" onClick={this.toggleDeviceForm}>
                                CRUD Device
                            </Button>
                        </Col>
                    </Row>
                    <br/>
                    <Row>
                        <Col sm={{size: '5', offset: 1}}>
                            {this.state.isLoaded && <PersonTable tableData = {this.state.tableData}/>}
                            {this.state.errorStatus > 0 && <APIResponseErrorMessage
                                                            errorStatus={this.state.errorStatus}
                                                            error={this.state.error}
                                                        />   }
                        </Col>

                        <Col sm={{size: '5', offset: 0}}>
                            {this.state.isLoadedDevice && <DeviceTable tableData2 = {this.state.tableData2}/>}
                            {this.state.errorStatus > 0 && <APIResponseErrorMessage
                                errorStatus={this.state.errorStatus}
                                error={this.state.error}
                            />   }
                        </Col>

                    </Row>
                    <Row>
                        <Col sm={{ size: '10', offset: 1 }}>
                            <Chat username={"ADMIN"} rol={"client"} token={localStorage.getItem("token")}/>

                        </Col>
                    </Row>
                </Card>

                <Modal isOpen={this.state.selected} toggle={this.toggleForm}
                       className={this.props.className} size="lg">
                    <ModalHeader toggle={this.toggleForm}> Add Person: </ModalHeader>
                    <ModalBody>
                        <PersonForm token={String(this.state.token)} reloadHandler={this.reload}/>
                    </ModalBody>
                </Modal>

                <Modal isOpen={this.state.isDeviceFormVisible} toggle={this.toggleDeviceForm}
                       className={this.props.className} size="lg">
                    <ModalHeader toggle={this.toggleDeviceForm}> Add Person: </ModalHeader>
                    <ModalBody>
                        <DeviceForm token={String(this.state.token)} reloadHandler={this.reload}/>
                    </ModalBody>
                </Modal>

            </div>
        )

    }
}


export default PersonContainer;
