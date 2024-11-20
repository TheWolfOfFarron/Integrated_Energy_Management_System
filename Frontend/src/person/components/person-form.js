import React from 'react';
import validate from "./validators/person-validators";
import Button from "react-bootstrap/Button";
import * as API_USERS from "../api/person-api";
import APIResponseErrorMessage from "../../commons/errorhandling/api-response-error-message";
import {Col, Row} from "reactstrap";
import { FormGroup, Input, Label} from 'reactstrap';



class DeviceForm extends React.Component {
    field
    constructor(props,token) {
        super(props);
        this.toggleForm = this.toggleForm.bind(this);
        this.reloadHandler = this.props.reloadHandler;

        this.state = {
            token: token,
            errorStatus: 0,
            error: null,

            formIsValid: false,

            formControls: {
                usernameOLD: {
                    value: '',
                    placeholder: 'What is your name?...',
                    valid: true,
                    touched: false,

                },
                username: {
                    value: '',
                    placeholder: 'What is your name?...',
                    valid: false,
                    touched: false,
                    validationRules: {
                        minLength: 3,
                        isRequired: true
                    }
                },
                password: {
                    value: '',
                    placeholder: 'password...',
                    valid: false,
                    touched: false,

                },
                role: {
                    value: '',
                    placeholder: 'ADMIN/CLIENT',
                    valid: false,
                    touched: false,
                    validationRules: {
                        emailValidator: true
                    }
                },

            }
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleUpdate = this.handleUpdate.bind(this);
        this.handleDelete = this.handleDelete.bind(this);

    }

    toggleForm() {
        this.setState({collapseForm: !this.state.collapseForm});
    }


    handleChange = event => {

        const name = event.target.name;
        const value = event.target.value;

        const updatedControls = this.state.formControls;

        const updatedFormElement = updatedControls[name];

        updatedFormElement.value = value;
        updatedFormElement.touched = true;
        updatedFormElement.valid = validate(value, updatedFormElement.validationRules);
        updatedControls[name] = updatedFormElement;

        let formIsValid = true;
        for (let updatedFormElementName in updatedControls) {
            formIsValid = updatedControls[updatedFormElementName].valid && formIsValid;
        }

        this.setState({
            formControls: updatedControls,
            formIsValid: formIsValid
        });

    };

    registerPerson(person) {
        return API_USERS.postPerson(localStorage.getItem("adminToken"),person, (result, status, error) => {
            if (result !== null && (status === 200 || status === 201)) {
                console.log("Successfully inserted person with id: " + result);
                this.reloadHandler();
            } else {
                this.setState(({
                    errorStatus: status,
                    error: error
                }));
            }
        });
    }

    registerPerson(person) {
        return API_USERS.postPerson(localStorage.getItem("adminToken"),person, (result, status, error) => {
            if (result !== null && (status === 200 || status === 201)) {
                console.log("Successfully inserted person with id: " + result);
                this.reloadHandler();
            } else {
                this.setState(({
                    errorStatus: status,
                    error: error
                }));
            }
        });
    }

    updatePerson(person) {
        return API_USERS.updatePerson(localStorage.getItem("adminToken"),person, (result, status, error) => {
            if (result !== null && (status === 200 || status === 201)) {
                console.log("Successfully inserted person with id: " + result);
                this.reloadHandler();
            } else {
                this.setState(({
                    errorStatus: status,
                    error: error
                }));
            }
        },this.state.formControls.usernameOLD.value);
    }

    deletePerson(person) {
        return API_USERS.deletePerson(localStorage.getItem("adminToken"),person, (result, status, error) => {
            if (result !== null && (status === 200 || status === 201)) {
                console.log("Successfully inserted person with id: " + result);
                this.reloadHandler();
            } else {
                this.setState(({
                    errorStatus: status,
                    error: error
                }));
            }
        },this.state.formControls.username.value);
    }

    deleteDevice(person) {
        return API_USERS.deleteDevices(localStorage.getItem("adminToken"),person, (result, status, error) => {
            if (result !== null && (status === 200 || status === 201)) {
                console.log("Successfully inserted person with id: " + result);
                this.reloadHandler();
            } else {
                this.setState(({
                    errorStatus: status,
                    error: error
                }));
            }
        });
    }
    handleSubmit() {

        let person = {
            name: this.state.formControls.username.value,
            pass: this.state.formControls.password.value,
            rol: this.state.formControls.role.value,
        };
    console.log(person);
    this.registerPerson(person);
    }

    handleUpdate() {

        let person = {
            name: this.state.formControls.username.value,
            pass: this.state.formControls.password.value,
            rol: this.state.formControls.role.value,
        };
        console.log(person);
        this.updatePerson(person);
    }

    handleDelete() {

        let person = {
            name: this.state.formControls.username.value,
            pass: this.state.formControls.password.value,
            rol: this.state.formControls.role.value,
        };

        let person1 = {
            id:null,
            description: null,
            address: null,
            maxHours: null,
            user: this.state.formControls.username.value,
        };

        console.log(person);
        this.deletePerson(person);
        this.deleteDevice(person1);

    }


    render() {
        return (
            <div>

                <FormGroup id='usernameOLD'>
                    <Label for='nameOLDField'> OLD Username: </Label>
                    <Input name='usernameOLD' id='usernameOLDField' placeholder={this.state.formControls.usernameOLD.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.usernameOLD.value}
                           touched={this.state.formControls.usernameOLD.touched? 1 : 0}
                           valid={this.state.formControls.usernameOLD.valid}
                           required
                    />

                </FormGroup>

                <FormGroup id='username'>
                    <Label for='nameField'> Username : </Label>
                    <Input name='username' id='usernameField' placeholder={this.state.formControls.username.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.username.value}
                           touched={this.state.formControls.username.touched? 1 : 0}
                           valid={this.state.formControls.username.valid}
                           required
                    />
                    {this.state.formControls.username.touched && !this.state.formControls.username.valid &&
                    <div className={"error-message row"}> * Name must have at least 3 characters </div>}
                </FormGroup>

                <FormGroup id='password'>
                    <Label for='passwordField'> Password: </Label>
                    <Input name='password' id='passwordField' placeholder={this.state.formControls.password.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.password.value}
                           touched={this.state.formControls.password.touched? 1 : 0}
                           valid={this.state.formControls.password.valid}
                           required
                    />

                </FormGroup>

                <FormGroup id='role'>
                    <Label for='roleField'> Role: </Label>
                    <Input name='role' id='roleField' placeholder={this.state.formControls.role.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.role.value}
                           touched={this.state.formControls.role.touched? 1 : 0}
                           valid={this.state.formControls.role.valid}
                           required
                    />
                    {this.state.formControls.role.touched && !this.state.formControls.role.valid &&
                        <div className={"error-message"}> * Role can be ADMIN or CLIENT </div>}
                </FormGroup>


                    <Row>
                        <Col sm={{size: '2', offset: 0}}>
                            <Button type={"submit"} disabled={!this.state.formIsValid} onClick={this.handleSubmit}>  INSERT </Button>
                        </Col>
                        <Col sm={{size: '2', offset: 2}}>
                            <Button type={"submit"} disabled={!this.state.formIsValid} onClick={this.handleUpdate}>  UPDATE </Button>
                        </Col>
                        <Col sm={{size: '2', offset: 4}}>
                            <Button type={"submit"} disabled={!this.state.formIsValid} onClick={this.handleDelete}>  DELETE </Button>
                        </Col>
                    </Row>

                {
                    this.state.errorStatus > 0 &&
                    <APIResponseErrorMessage errorStatus={this.state.errorStatus} error={this.state.error}/>
                }
            </div>
        ) ;
    }
}

export default DeviceForm;
