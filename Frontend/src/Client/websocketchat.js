import React, { useState, useEffect } from "react";
import Stomp from "stompjs";
import SockJS from "sockjs-client";
import { List, ListItem, ListItemAvatar, Avatar, ListItemText, TextField, Button, Typography } from "@material-ui/core";

const Chat = ({username, rol,token}) => {
    const [messages, setMessages] = useState([]);
    const [message, setMessage] = useState("");
    const [nickname, setUser] = useState("");
    const [toUser, setUserSend] = useState("");

    const [stompClient, setStompClient] = useState(null);

    useEffect(() => {
        //const socket = new SockJS("http:/localhost:8083/web");
         const socket = new SockJS("http://localhost:8083/web?auth=Bearer "+ token);
        const client = Stomp.over(socket);
        console.log()
        console.log("udsgayihdjasdas "+token )
        client.connect({}, () => {
            client.subscribe("/topic/mes", (message) => {
                const receivedMessage = JSON.parse(message.body);
                    console.log(username)
                console.log(rol)
                console.log("ytftdfsagudhisajodsadsadsadadasd   "+ receivedMessage.nickname)
                console.log(receivedMessage.rol)
                console.log(receivedMessage.username)
                console.log(receivedMessage.toUser)

                let parts = receivedMessage.nickname.split(" ");

                    if(parts.length>1){
                        for (let i = 0; i < parts.length; i++) {
                            if (receivedMessage.rol === "client" &&"admin" === rol && parts[i] === username) {
                                receivedMessage.nickname="ADMIN"
                                setMessages((prevMessages) => [...prevMessages, receivedMessage]);
                            }
                        }
                    }


                if (receivedMessage.rol === "client" &&"admin" === rol && receivedMessage.nickname === username) {
                    receivedMessage.nickname="ADMIN"
                    setMessages((prevMessages) => [...prevMessages, receivedMessage]);
                }
                else
                if (receivedMessage.rol === "admin" &&"client" === rol) {
                    setMessages((prevMessages) => [...prevMessages, receivedMessage]);
                }else
                    if(receivedMessage.rol===rol&&username === receivedMessage.username){
                        setMessages((prevMessages) => [...prevMessages, receivedMessage]);
                    }
            });
        });

        setStompClient(client);

        return () => {
            client.disconnect();
        };
    }, [token]);

    const handleUser = (e) => {
        setUser(e.target.value);
    };
    const handleUser1 = (e) => {
        setUserSend(e.target.value);
    };

    const handleMes = (e) => {
        setMessage(e.target.value);
    };

    const sendMessage = () => {
        if (message.trim()) {
            const chatMessage = {
                nickname,
                toUser,
                rol,
                username,
                content: message,
            };

            stompClient.send("/app/chat", {}, JSON.stringify(chatMessage));
            setMessage(""); // Clear the message input after sending
        }
    };
    if(rol==="admin"){
    return (
        <div>
            <List>
                {messages.map((msg, index) => (

                    <ListItem key={index}>
                        <Avatar>{msg.nickname.charAt(0)}</Avatar>
                        <ListItemText
                            primary={<Typography variant="subtitle1" gutterBottom>{msg.nickname}</Typography>}
                            secondary={msg.content}
                        />
                    </ListItem>
                ))}
            </List>

            <div style={{ display: "flex", alignItems: "center" }}>
                <TextField label="User" variant="standard" value={nickname} onChange={handleUser} />
                <TextField label="Message" variant="standard" value={message} onChange={handleMes} />
                <Button variant="contained" onClick={sendMessage}>Send</Button>
            </div>
        </div>
    );}
    if(rol==="client"){
        return (
            <div>
                <List>
                    {messages.map((msg, index) => (

                        <ListItem key={index}>
                            <Avatar>{msg.nickname.charAt(0)}</Avatar>
                            <ListItemText
                                primary={<Typography variant="subtitle1" gutterBottom>{msg.nickname}</Typography>}
                                secondary={msg.content}
                            />
                        </ListItem>
                    ))}
                </List>

                <div style={{ display: "flex", alignItems: "center" }}>
                    <TextField label="User" variant="standard" value={nickname} onChange={handleUser} />
                    <TextField label="ToUser" variant="standard" value={toUser} onChange={handleUser1} />

                    <TextField label="Message" variant="standard" value={message} onChange={handleMes} />
                    <Button variant="contained" onClick={sendMessage}>Send</Button>
                </div>
            </div>
        );}

};

export {Chat};
