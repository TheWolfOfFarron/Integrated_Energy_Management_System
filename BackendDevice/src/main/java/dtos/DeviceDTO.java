package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;
import ro.tuc.ds2020.entities.ActiveUsers;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class DeviceDTO extends RepresentationModel<DeviceDTO> implements Serializable {

        private UUID id;


    private String description;


    private String address;


    private String maxHours;

    private String  user;
    public DeviceDTO(UUID id, String description, String address, String maxHours,String user) {
        this.id = id;
        this.description = description;
        this.address = address;
        this.maxHours = maxHours;
        this.user=user;
    }

    public DeviceDTO(String description, String address, String maxHours,String user) {
        this.description = description;
        this.address = address;
        this.maxHours = maxHours;
        this.user=user;
    }

    public DeviceDTO() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMaxHours() {
        return maxHours;
    }

    public void setMaxHours(String maxHours) {
        this.maxHours = maxHours;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, address);
    }
}
