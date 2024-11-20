package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.DeviceDTO;
import ro.tuc.ds2020.entities.Device;

public class DeviceBuilder {

    private DeviceBuilder() {
    }

    public static DeviceDTO toPersonDTO(Device device) {
        return new DeviceDTO(device.getId(), device.getDescription(), device.getAddress(),device.getMaxHours(),device.getUser());
    }



    public static Device toEntity(DeviceDTO deviceDTO)
    {
        return new Device(deviceDTO.getDescription(), deviceDTO.getAddress(),deviceDTO.getMaxHours(),deviceDTO.getUser());
    }

    public static Device toComleteEntity(DeviceDTO deviceDTO) {
        return new Device(deviceDTO.getId(), deviceDTO.getDescription(), deviceDTO.getAddress(),deviceDTO.getMaxHours(),deviceDTO.getUser());
    }
}
