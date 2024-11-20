package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
public class Device implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @Column(name = "idDevice", nullable = false)

    private UUID idDevice;
    @Column(name = "maxHour", nullable = false)
    private String maxHour;

    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DeviceData> deviceDataList;


    public Device() {
    }

    public Device(UUID idDevice, String maxHour, List<DeviceData> deviceDataList) {
        this.idDevice = idDevice;
        this.maxHour = maxHour;
        this.deviceDataList = deviceDataList;
    }

    public Device(UUID id, UUID idDevice, String maxHour, List<DeviceData> deviceDataList) {
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

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", idDevice=" + idDevice +
                ", maxHour='" + maxHour + '\'' +
                '}';
    }
}
