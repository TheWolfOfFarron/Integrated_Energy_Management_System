package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class DeviceData implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;


    @Column(name = "time", nullable = false)
    private String time;

    @Column(name = "consuption", nullable = false)
    private double consuption;

    @Column(name = "consuptionLastHour", nullable = false)
    private double consuptionLastHour;

    @ManyToOne
    @JoinColumn(name = "idDevice", nullable = false, referencedColumnName = "id")
    private Device device;



    public DeviceData() {
    }

    public DeviceData(Device idDevice, String time, double consuption, double maxHour) {
        this.time = time;
        this.consuption = consuption;
        this.device =idDevice;
        this.consuptionLastHour=maxHour;
    }

    public DeviceData(UUID id, Device idDevice, String time, double consuption, String maxHour) {
        this.id = id;
        this.time = time;
        this.consuption = consuption;
        this.device =idDevice;
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


    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public double getConsuptionLastHour() {
        return consuptionLastHour;
    }

    public void setConsuptionLastHour(double consuptionLastHour) {
        this.consuptionLastHour = consuptionLastHour;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", consuption='" + consuption + '\'' +
                '}';
    }
}
