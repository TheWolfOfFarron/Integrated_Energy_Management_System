package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;
import ro.tuc.ds2020.entities.Device;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

public class ActiveUsersDTO extends RepresentationModel<ActiveUsersDTO> {

    private UUID id;

    @NotNull
    private String username;


    public ActiveUsersDTO(String username) {
        this.username = username;
    }

    public ActiveUsersDTO(UUID id, String username) {
        this.id = id;
        this.username = username;

    }

    public ActiveUsersDTO() {

    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override

        public int hashCode() {
            return Objects.hash(id, username);
        }
}
