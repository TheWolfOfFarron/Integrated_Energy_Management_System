import Stomp from 'stompjs';
import SockJS from 'sockjs-client';

// Initialize SockJS connection
const token = localStorage.getItem("token");

const socket = new SockJS('http://localhost:8082/web?auth=Bearer ' + token);
const stompClient = Stomp.over(socket);

let onDataReceivedCallback = null;

// Retrieve JWT token from localStorage

// Set headers with Authorization token


// Connect function with JWT headers
const connect = (token,callback) => {
    onDataReceivedCallback = callback;

    stompClient.connect({}, (frame) => {
        console.log('WebSocket connection established:', frame);

        // Subscribe to a topic
        stompClient.subscribe('/topic/data', (message) => {
            console.log("Received message:", message.body);

            // Parse and handle the message
            if (onDataReceivedCallback) {
                onDataReceivedCallback(message.body);
            }
        });
    }, (error) => {
        console.error('WebSocket connection error:', error);
    });
};

// Disconnect function
const disconnect = () => {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
};

// Send data function
const sendData = (data) => {
    stompClient.send('/app/sendData', {}, JSON.stringify(data));
};

export { connect, disconnect, sendData };
