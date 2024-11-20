import {HOST} from '../../commons/hosts';
import RestApiClient from "../../commons/api/rest-client";
import {func} from "prop-types";


const endpoint = {
    person: '/person',
    device: '/device',
    update: '/up=',
    delete: '/del',
    del:"/dela"
};

function getPersons(token,callback) {

    let request = new Request(HOST.backend_api + endpoint.person, {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer '+token

        }
    });
    console.log(request.url);
    console.log('dasdasda');
    console.log(token);
    RestApiClient.performRequest(request, callback);
}

function getDevice(token,callback){
    let request = new Request(HOST.backend_device_api+endpoint.device,{
        method:'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer '+ token

        }
    })
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function getPersonById(params, callback){
    let request = new Request(HOST.backend_api + endpoint.person + params.id, {
       method: 'GET'
    });

    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function postPerson(token,user, callback){
    let request = new Request(HOST.backend_api + endpoint.person , {
        method: 'POST',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer '+ token
        },

        body: JSON.stringify(user)

    });

    console.log("URL: " + request.url + " "+ JSON.stringify(user));


    RestApiClient.performRequest(request, callback);
}

function updatePerson(token,user,callback,param){
    let request = new Request(HOST.backend_api + endpoint.person+endpoint.update+param , {
        method: 'PUT',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer '+ token
        },
        body: JSON.stringify(user)

    });

    console.log("URL: " + request.url + " "+ JSON.stringify(user));

    RestApiClient.performRequest(request, callback);
}

function deletePerson(token,user,callback,u){
    let request = new Request(HOST.backend_api + endpoint.person+endpoint.delete , {
        method: 'POST',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer '+ token
        },
        body: JSON.stringify(user)

    });







    RestApiClient.performRequest(request, callback);
}

function deleteDevice(token,user,callback){
    let request = new Request(HOST.backend_device_api+endpoint.device + endpoint.delete , {
        method: 'POST',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer '+ token
        },
        body: JSON.stringify(user)

    });

    console.log("URL: " + request.url + " "+ JSON.stringify(user));

    RestApiClient.performRequest(request, callback);
}


function deleteDevices(token,user,callback){
    let request = new Request(HOST.backend_device_api+endpoint.device + endpoint.del , {
        method: 'POST',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer '+ token
        },
        body: JSON.stringify(user)

    });

    console.log("URL: " + request.url + " "+ JSON.stringify(user));

    RestApiClient.performRequest(request, callback);
}

function insertDevice(token,user,callback){
    let request = new Request(HOST.backend_device_api+endpoint.device, {
        method: 'POST',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer '+ token
        },
        body: JSON.stringify(user)

    });

    console.log("URL: " + request.url + " "+ JSON.stringify(user));

    RestApiClient.performRequest(request, callback);
}

function updateDevice(token,user,callback){
    let request = new Request(HOST.backend_device_api+endpoint.device + endpoint.update+"22" , {
        method: 'PUT',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer '+ token
        },
        body: JSON.stringify(user)

    });

    console.log("URL: " + request.url + " "+ JSON.stringify(user));

    RestApiClient.performRequest(request, callback);
}



export {
    getPersons,
    getDevice,
    getPersonById,
    postPerson,
    updatePerson,
    deletePerson,
    updateDevice,
    insertDevice,
    deleteDevice,
    deleteDevices
};
