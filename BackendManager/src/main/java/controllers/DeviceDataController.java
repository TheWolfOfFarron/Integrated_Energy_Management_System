package ro.tuc.ds2020.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.services.DeviceDataService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@CrossOrigin("*")

@RequestMapping(value = "/dasda")// /{user}{pass}
public class DeviceDataController {

    private static DeviceDataService deviceDataService;

    @Autowired
    public DeviceDataController(DeviceDataService deviceDataService) {
        this.deviceDataService = deviceDataService;
    }


    //TODO: UPDATE, DELETE per resource "2c3ef70a-1870-4c30-8753-6451f1906378"


    public static DeviceDataService getDeviceDataService() {
        return deviceDataService;
    }

    public static void setDeviceDataService(DeviceDataService deviceDataService) {
        DeviceDataController.deviceDataService = deviceDataService;
    }
}
