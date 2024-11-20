import {HOST} from "../commons/hosts";
import RestApiClient from "../commons/api/rest-client";



const endpoint = {
    person: '/person',
    device: '/device',
    update: '/up=',
    delete: '/del',
    deviceUser:'/deviceUser'
};


function getDevice(token,callback,param){
    let request = new Request(HOST.backend_device_api+endpoint.deviceUser +"/="+ param,{
        method:'GET',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer '+ token
        },
    })
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}
export {
    getDevice
}