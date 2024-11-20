package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.DeviceDataDTO;
import ro.tuc.ds2020.dtos.DeviceDataDetailsDTO;
import ro.tuc.ds2020.entities.DeviceData;

public class DeviceDataBuilder {

    private DeviceDataBuilder() {
    }

    public static DeviceDataDTO toPersonDTO(DeviceData deviceData) {
        return new DeviceDataDTO(deviceData.getId(), null, deviceData.getTime(), deviceData.getConsuption(), null);
    }

    public static DeviceDataDetailsDTO toPersonDetailsDTO(DeviceData deviceData) {
        return new DeviceDataDetailsDTO(deviceData.getId(), deviceData.getTime(), deviceData.getConsuption(), deviceData.getDevice());
    }

    public static DeviceData toEntity(DeviceDataDetailsDTO deviceDataDetailsDTO) {
        return new DeviceData(deviceDataDetailsDTO.getDeviceid(),deviceDataDetailsDTO.getTime(), deviceDataDetailsDTO.getConsuption(),deviceDataDetailsDTO.getConsuptionLastHour());
    }

    public static DeviceData toComleteEntity(DeviceDataDetailsDTO deviceDataDetailsDTO) {
        return new DeviceData(deviceDataDetailsDTO.getId(), deviceDataDetailsDTO.getDeviceid(), deviceDataDetailsDTO.getTime(), deviceDataDetailsDTO.getConsuption(), null);
    }
}
