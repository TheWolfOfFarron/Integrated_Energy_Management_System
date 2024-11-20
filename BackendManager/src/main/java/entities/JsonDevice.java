package ro.tuc.ds2020.entities;

import java.util.UUID;

public class JsonDevice {

    private UUID idDevice;
  private String maxHour;


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
}
