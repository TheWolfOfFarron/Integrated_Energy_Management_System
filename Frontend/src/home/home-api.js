import {HOST} from "../commons/hosts";
import RestApiClient from "../commons/api/rest-client";



const endpoint = {
    login:'/login',
    person: '/person',
    device: '/device',
    update: '/up=',
    delete: '/del'
};


function getPerson(callback,param,param2){
    let request = new Request(HOST.backend_api+endpoint.login +"/"+ param+"/"+param2,{
        method:'GET',

    })
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}
export {
    getPerson
}