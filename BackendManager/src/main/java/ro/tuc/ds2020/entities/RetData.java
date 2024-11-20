package ro.tuc.ds2020.entities;

public class RetData {

    String message;
    String deviceId;

    public RetData(String message, String deviceId) {
        this.message = message;
        this.deviceId = deviceId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
