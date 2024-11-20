package ro.tuc.ds2020.dtos;

import ro.tuc.ds2020.entities.Device;

import java.util.Objects;
import java.util.UUID;

public class DeviceDataDetailsDTO {

    private UUID id;



    private String time;

    private double consuption;


    private Device deviceid;


    private double consuptionLastHour;



    public DeviceDataDetailsDTO() {
    }

    public DeviceDataDetailsDTO(UUID id, String time, double consuption, Device device) {
        this.id = id;
        this.time = time;
        this.consuption = consuption;
        this.deviceid = device;
        consuptionLastHour=0;
    }

    public DeviceDataDetailsDTO(String time, double consuption, Device device) {
        this.time = time;
        this.consuption = consuption;
        this.deviceid = device;
        consuptionLastHour=0;
    }

    public Device getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(Device deviceid) {
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
    public String toString() {
        return "DeviceDataDetailsDTO{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", consuption=" + consuption +
                ", deviceid=" + deviceid +
                ", consuptionLastHour=" + consuptionLastHour +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceid, consuption);
    }
}
