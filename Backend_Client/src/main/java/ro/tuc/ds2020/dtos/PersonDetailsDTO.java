package ro.tuc.ds2020.dtos;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

public class PersonDetailsDTO {

    private UUID id;
    @NotNull
    private String name;
    @NotNull
    private String pass;
    @NotNull
    private String rol;

    public PersonDetailsDTO() {
    }

    public PersonDetailsDTO(UUID id, String name, String pass, String rol) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.rol = rol;
    }

    public PersonDetailsDTO(String name, String pass, String rol) {
        this.name = name;
        this.pass = pass;
        this.rol = rol;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, pass, rol);
    }
}
