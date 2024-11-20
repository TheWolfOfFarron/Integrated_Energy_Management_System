package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import ro.tuc.ds2020.entities.DeviceData;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

public class DeviceDTO extends RepresentationModel<DeviceDataDTO> {

    private UUID id;


    private UUID idDevice;
    private String maxHour;

    private List<DeviceData> deviceDataList;


    public DeviceDTO(){};


    public DeviceDTO(UUID idDevice, String maxHour, List<DeviceData> deviceDataList) {
        this.idDevice = idDevice;
        this.maxHour = maxHour;
        this.deviceDataList = deviceDataList;
    }

    public DeviceDTO(UUID id, UUID idDevice, String maxHour, List<DeviceData> deviceDataList) {
        this.id = id;
        this.idDevice = idDevice;
        this.maxHour = maxHour;
        this.deviceDataList = deviceDataList;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(UUID idDevice) {
        this.idDevice = idDevice;
    }

    public String getMaxHour() {
        return maxHour;
    }

    public void setMaxHour(String maxHour) {
        this.maxHour = maxHour;
    }

    public List<DeviceData> getDeviceDataList() {
        return deviceDataList;
    }

    public void setDeviceDataList(List<DeviceData> deviceDataList) {
        this.deviceDataList = deviceDataList;
    }
}
