import React from 'react';
import APIResponseErrorMessage from "../commons/errorhandling/api-response-error-message";
import {
    Card,
    CardHeader,
    Col,
    Row
} from 'reactstrap';
import * as API_Device from "./Client-api"
import DeviceTable from "../person/components/device-table";

import { connect as connectWebSocket, disconnect as disconnectWebSocket ,sendData } from './WebSocketService';
import {Chat}  from "./websocketchat";

class ClientContainter extends React.Component {



    constructor(props) {
        super(props);

        this.reload = this.reload.bind(this);
        this.state = {

            selected: false,
            username:localStorage.getItem("username"),
            token:localStorage.getItem("token"),
            collapseForm: false,
            tableData2: [],
            isLoaded: false,
            isLoadedDevice:false,
            errorStatus: 0,
            isDeviceFormVisible: false,
            error: null,
            notifications: [],
            showPopup: false,
            popupMessage: '',
        };
      /*  const socket = new WebSocket('ws://localhost:8082/web');
        socket.onerror = (event) => {
            console.error('WebSocket error:', event);
        };


        socket.onopen = () => {
            console.log('WebSocket connection established.');
        };
        socket.onmessage = (event) => {
            console.log('WebSocket message received:', event.data);

            const message = JSON.parse(event.data);
            const deviceId = message.deviceId;
            if(this.state.tableData2.some(device => device.id === deviceId)){
                this.handleNotification(message);
            }
        };

        socket.onclose=(event)=>{
            console.log('WebSocket connection closed:', event);
        }
*/



    }


  /*  handleDataReceived = (data) => {
        console.log('Received data from Spring:', data);
        // Add your logic to handle the incoming data
    };*/

    handleDataReceived = (data) => {
        try {
            const message = JSON.parse(data);
            console.log('WebSocket message received:', message);

            const deviceId = message.deviceId;
            if (this.state.tableData2.some(device => device.id === deviceId)) {
                this.handleNotification(message);

            }
        }catch (error){
            console.log('WebSocket error received:', error);

        }

    }

    handleSendData = () => {
        const dataToSend = { key: 'yourKey', value: 'yourValue' };
        sendData(dataToSend);
    }

    handleNotification(notification) {
        console.log('WebSocket connection established.');
        this.setState({
            showPopup: true,
            popupMessage:notification.deviceId+' '+ notification.message, // Assume your WebSocket message has a 'message' field
            notifications: [...this.state.notifications, notification],
        });
    }

    closePopup = () => {
        this.setState({
            showPopup: false,
            popupMessage: '',
        });
    };
    componentDidMount() {
        console.log('Component did mount.');
        this.fetchDevice();
        connectWebSocket(this.handleDataReceived);
    }

    componentWillUnmount() {
        disconnectWebSocket();

    }

    fetchDevice() {
        return API_Device.getDevice(this.state.token,(result, status, err) => {
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
        },this.state.username);
    }




    reload() {
        this.setState({
            isLoaded: false,
            isLoadedDevice:false
        });

        this.fetchDevice();
    }

    render() {
        console.log('WebSocket connection establisdsadasdasdasdhed. ');
        const { showPopup, popupMessage } = this.state;
        if(localStorage.getItem("username")==null||localStorage.getItem("rol")==null||localStorage.getItem("roll")==='ADMIN'){
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
                        <Col sm={{size: '10', offset: 1}}>
                            {this.state.isLoadedDevice && <DeviceTable tableData2 = {this.state.tableData2}/>}
                            {this.state.errorStatus > 0 && <APIResponseErrorMessage
                                errorStatus={this.state.errorStatus}
                                error={this.state.error}
                            />   }
                        </Col>

                    </Row>
                    <Row>
                        <Col sm={{ size: '10', offset: 1 }}>
                            <Chat username={this.state.username} rol={"admin"} token={localStorage.getItem("token")} />

                        </Col>
                    </Row>
                </Card>

                <button onClick={this.handleSendData}>Send Data</button>
                {showPopup && (alert(popupMessage)
                    //  <NotificationPopup message={popupMessage} onClose={this.closePopup} />
                )}

            </div>
        )

    }
}


export default ClientContainter;
