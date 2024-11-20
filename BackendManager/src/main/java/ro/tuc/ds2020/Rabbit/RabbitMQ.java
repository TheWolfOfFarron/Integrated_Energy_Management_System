package ro.tuc.ds2020.Rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import ro.tuc.ds2020.controllers.DeviceDataController;
import ro.tuc.ds2020.dtos.DeviceDataDTO;
import ro.tuc.ds2020.dtos.DeviceDataDetailsDTO;
import ro.tuc.ds2020.entities.*;
import ro.tuc.ds2020.repositories.PersonRepository;
import ro.tuc.ds2020.services.DeviceDataService;
import com.google.gson.Gson;
import ro.tuc.ds2020.services.DeviceService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Component
public class RabbitMQ {

    private final DeviceDataService deviceDataService;
    private final DeviceService deviceService;
    private final SimpMessagingTemplate messagingTemplate;
    @Autowired
    public RabbitMQ(SimpMessagingTemplate messagingTemplate, DeviceDataService deviceDataService, DeviceService deviceService){
        this.deviceDataService=deviceDataService;
        this.deviceService=deviceService;
        this.messagingTemplate=messagingTemplate;

    }


    @RabbitListener(queues = "Simulator")
    @MessageMapping("/sendData")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
        Gson gson = new Gson();
        JsonData jsonData= gson.fromJson(message,JsonData.class);
        Device device= deviceService.findbyDevice(jsonData.getDevice_id());

        String ret = "{\"deviceid\":"+jsonData.getDevice_id().toString()+"\", \"message\":\"High Consumption Alert!\" }";

        RetData retData =new RetData("High Consumption Alert!",jsonData.getDevice_id().toString());





        DeviceDataDetailsDTO deviceDataDTO=new DeviceDataDetailsDTO(jsonData.getTimestamp(),jsonData.getMeasurement_value(),device);


       UUID id = deviceDataService.insert(deviceDataDTO);

        ArrayList<DeviceData> dataList = (ArrayList<DeviceData>)deviceDataService.findbyidord(jsonData.getDevice_id());
        double sum=0;
        double dif=0;
        if(dataList.size()<6){

            for(int i=0;i<dataList.size();i++){
                sum+=dataList.get(i).getConsuption()-dif;
                dif=dataList.get(i).getConsuption();
            }
            sum/=dataList.size();
        }else {
            for (int i = 0; i < 6; i++) {
                sum+=dataList.get(i).getConsuption()-dif;
                dif=dataList.get(i).getConsuption();
            }
            sum/=6;

        }
        deviceDataDTO.setId(id);
        deviceDataDTO.setConsuptionLastHour(sum);
        deviceDataService.update(deviceDataDTO);

       if(sum>Float.parseFloat(dataList.get(0).getDevice ().getMaxHour())){
           retData.setMessage("High Consumption Alert! " +sum+ " > "+ dataList.get(0).getDevice ().getMaxHour());
           messagingTemplate.convertAndSend("/topic/data", retData);
        }

    }

    @RabbitListener(queues = "Device")
    public void receiveMessage2(String message) {
        System.out.println("Received message: " + message);
        String json =message.substring(1);
        Gson gson = new Gson();
        JsonDevice jsonData= gson.fromJson(json,JsonDevice.class);
        switch (message.charAt(0)){
            case ('i'): deviceService.insert(new Device(jsonData.getIdDevice(),jsonData.getMaxHour(),null));
                break;
            case ('u'): Device device=deviceService.findbyDevice(jsonData.getIdDevice());
            if(device==null)
                break;
                        device.setMaxHour(jsonData.getMaxHour());
                        device.setIdDevice(jsonData.getIdDevice());
                        deviceService.update(device);
                break;
            case('d'): Device devices=deviceService.findbyDevice(jsonData.getIdDevice());
                        deviceService.delete(devices);
                break;
        }


    }

}
