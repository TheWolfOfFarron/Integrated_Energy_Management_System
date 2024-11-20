import React from 'react';
import validate from "./validators/person-validators";
import Button from "react-bootstrap/Button";
import * as API_USERS from "../api/person-api";
import APIResponseErrorMessage from "../../commons/errorhandling/api-response-error-message";
import {Col, Row} from "reactstrap";
import { FormGroup, Input, Label} from 'reactstrap';



class PersonForm extends React.Component {
    field =0
    constructor(props) {
        super(props);
        this.toggleForm = this.toggleForm.bind(this);
        this.reloadHandler = this.props.reloadHandler;

        this.state = {

            errorStatus: 0,
            error: null,

            formIsValid: false,

            formControls: {
                ID: {
                    value: '',
                    placeholder: 'ID...',
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
                MaxHours: {
                    value: '',
                    placeholder: 'password...',
                    valid: false,
                    touched: false,

                },
                Address: {
                    value: '',
                    placeholder: 'ADMIN/CLIENT',
                    valid: false,
                    touched: false,

                },
                Description: {
                    value: '',
                    placeholder: 'ADMIN/CLIENT',
                    valid: false,
                    touched: false,

                },

            }
        };

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

    registerDevice(person) {
        return API_USERS.insertDevice(localStorage.getItem("adminToken"),person, (result, status, error) => {
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



    updateDevice(person) {
        return API_USERS.updateDevice(localStorage.getItem("adminToken"),person, (result, status, error) => {
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

    deleteDevice(person) {
        return API_USERS.deleteDevice(localStorage.getItem("adminToken"),person, (result, status, error) => {
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

            description: this.state.formControls.Description.value,
            address: this.state.formControls.Address.value,
            maxHours: this.state.formControls.MaxHours.value,
            user: this.state.formControls.username.value,
        };


        console.log(person);
        this.registerDevice(person);
    }

    handleUpdate() {

        let person = {
            id:this.state.formControls.ID.value,
            description: this.state.formControls.Description.value,
            address: this.state.formControls.Address.value,
            maxHours: this.state.formControls.MaxHours.value,
            user: this.state.formControls.username.value
        };
        console.log(person);
        this.updateDevice(person);
    }

    handleDelete() {

        let person = {
            id:this.state.formControls.ID.value,
            description: this.state.formControls.Description.value,
            address: this.state.formControls.Address.value,
            maxHours: this.state.formControls.MaxHours.value,
            user: this.state.formControls.username.value,
        };
        console.log(person);
        this.deleteDevice(person);
    }


    render() {
        return (
            <div>
                <FormGroup id='ID'>
                    <Label for='IDField'> ID : </Label>
                    <Input name='ID' id='IDField' placeholder={this.state.formControls.ID.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.ID.value}
                           touched={this.state.formControls.ID.touched? 1 : 0}
                           valid={this.state.formControls.ID.valid}
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

                <FormGroup id='MaxHours'>
                    <Label for='MaxHoursField'> MaxHours: </Label>
                    <Input name='MaxHours' id='MaxHoursField' placeholder={this.state.formControls.MaxHours.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.MaxHours.value}
                           touched={this.state.formControls.MaxHours.touched? 1 : 0}
                           valid={this.state.formControls.MaxHours.valid}
                           required
                    />

                </FormGroup>

                <FormGroup id='Address'>
                    <Label for='AddressField'> Address: </Label>
                    <Input name='Address' id='AddressField' placeholder={this.state.formControls.Address.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.Address.value}
                           touched={this.state.formControls.Address.touched? 1 : 0}
                           valid={this.state.formControls.Address.valid}
                           required
                    />

                </FormGroup>
                <FormGroup id='Description'>
                    <Label for='DescriptionField'> Description: </Label>
                    <Input name='Description' id='DescriptionField' placeholder={this.state.formControls.Description.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.Description.value}
                           touched={this.state.formControls.Description.touched? 1 : 0}
                           valid={this.state.formControls.Description.valid}
                           required
                    />

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

export default PersonForm;
