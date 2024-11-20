package ro.tuc.ds2020.entities;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
public class ActiveUsers implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID idU;

    @Column(name = "username",unique = true,nullable = false)
    private  String username;


    public ActiveUsers(UUID id, String name) {
        this.idU = id;
        this.username=name;

    }

    public ActiveUsers(String name) {
        this.username=name;
    }

  public ActiveUsers(){

  }



    public UUID getId() {
        return idU;
    }

    public void setId(UUID id) {
        this.idU = id;
    }

    public UUID getIdU() {
        return idU;
    }

    public void setIdU(UUID idU) {
        this.idU = idU;
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }
}
