import React from 'react';
import { Redirect } from 'react-router-dom'; // Import Redirect
import { Button, Container, Form, FormGroup, Input, Jumbotron } from 'reactstrap';

import BackgroundImg from '../commons/images/future-medicine.jpg';
import * as API from "./home-api"

const backgroundStyle = {
    backgroundPosition: 'center',
    backgroundSize: 'cover',
    backgroundRepeat: 'no-repeat',
    width: '100%',
    height: '1920px',
    backgroundImage: `url(${BackgroundImg})`,
};
const textStyle = { color: 'white' };

class Home extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: '',
            redirectToAdmin: false,
            redirectToClient:false,
        };
    }

    handleUsernameChange = (event) => {
        this.setState({ username: event.target.value });
    };

    handlePasswordChange = (event) => {
        this.setState({ password: event.target.value });
    };


    handleLogin = () => {

        API.getPerson((result, status, err) => {
            console.log('Device result:',result,status)

            localStorage.removeItem("username")
            localStorage.removeItem("rol")
            localStorage.removeItem("token")


            if (result !== null && (status === 200||status === 201)) {
                var data=[];
                for(var i in result)
                    data.push([result[i]])
                console.log('Device result:',data[0][0])
              if(data[0][0]=== 'ADMIN'){
                  localStorage.setItem("rol",data[0][0])
                  localStorage.setItem("token",data[2][0])
                  console.log('poiuy '+data[2][0])

                  this.setState({ redirectToAdmin: true });
              }

                if(data[0][0]=== 'CLIENT'){
                    localStorage.setItem("token",data[2][0])
                    console.log(localStorage.getItem("token"))

                    localStorage.setItem("username",data[1][0])
                    localStorage.setItem("rol",data[0][0])
                    console.log('Device resuldahjsdyuasdt:',localStorage.getItem("username") , localStorage.getItem("token") )
                    this.setState({ redirectToClient: true })
                };


            } else {
                alert('Login failed. Please check your credentials.');
            }
        },this.state.username,this.state.password )

    };

    render() {
        if (this.state.redirectToAdmin) {
            return <Redirect to="/admin" />;
        }

        if (this.state.redirectToClient) {


            return <Redirect to="/client" />;
        }

        return (
            <div>
                <Jumbotron fluid style={backgroundStyle}>
                    <Container fluid>
                        <h1 className="display-3" style={textStyle}>
                            Integrated Medical Monitoring Platform for Home-care assistance
                        </h1>
                        <p className="lead" style={textStyle}>
                            <b>
                                Enabling real-time monitoring of patients, remote-assisted care services, and smart intake mechanism for prescribed medication.
                            </b>
                        </p>
                        <hr className="my-2" />
                        <p style={textStyle}>
                            <b>
                                This assignment represents the first module of the distributed software system "Integrated
                                Medical Monitoring Platform for Home-care assistance that represents the final project
                                for the Distributed Systems course.
                            </b>
                        </p>
                        <Form>
                            <FormGroup>
                                <Input
                                    type="text"
                                    name="username"
                                    placeholder="Username"
                                    value={this.state.username}
                                    onChange={this.handleUsernameChange}
                                />
                            </FormGroup>
                            <FormGroup>
                                <Input
                                    type="password"
                                    name="password"
                                    placeholder="Password"
                                    value={this.state.password}
                                    onChange={this.handlePasswordChange}
                                />
                            </FormGroup>
                            <Button color="primary" onClick={this.handleLogin}>
                                Login
                            </Button>
                        </Form>
                    </Container>
                </Jumbotron>
            </div>
        );
    }
}

export default Home;
