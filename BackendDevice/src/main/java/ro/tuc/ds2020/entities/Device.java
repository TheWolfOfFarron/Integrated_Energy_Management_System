package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class  Device  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID idD;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "address", nullable = false)
    private String address;

   @Column(name = "maxHours", nullable = false)
    private String maxHours;

    @Column(name = "username", nullable = true)
    private String username;

    public Device(UUID id, String description, String address, String maxHours,String user) {
        this.idD = id;
        this.description = description;
        this.address = address;
        this.maxHours = maxHours;
        this.username =user;
    }

    public Device(String description, String address, String maxHours,String user) {
        this.description = description;
        this.address = address;
        this.maxHours = maxHours;
        this.username =user;
    }

    public Device() {
    }



    public UUID getId() {
        return idD;
    }

    public void setId(UUID id) {
        this.idD = id;
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
        return username;
    }

    public void setUser(String user) {
        this.username = user;
    }
}
