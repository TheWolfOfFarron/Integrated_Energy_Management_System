package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.DeviceData;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class DeviceDataDTO extends RepresentationModel<DeviceDataDTO> {
    private UUID id;


    private String time;

    private double consuption;

    private double consuptionLastHour;


    private List<DeviceData> deviceid;

    public DeviceDataDTO() {
    }




    public DeviceDataDTO(UUID id, List<DeviceData> idDevice, String time, double consuption, String maxHour) {
        this.id = id;
        this.time = time;
        this.consuption = consuption;
        this.deviceid=idDevice;
    }

    public List<DeviceData> getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(List<DeviceData> deviceid) {
        this.deviceid = deviceid;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }



    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getConsuption() {
        return consuption;
    }

    public void setConsuption(double consuption) {
        this.consuption = consuption;
    }

    public double getConsuptionLastHour() {
        return consuptionLastHour;
    }

    public void setConsuptionLastHour(double consuptionLastHour) {
        this.consuptionLastHour = consuptionLastHour;
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, consuption);
    }
}
