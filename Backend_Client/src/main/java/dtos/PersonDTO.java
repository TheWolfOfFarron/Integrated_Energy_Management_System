package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;
import java.util.UUID;

public class PersonDTO extends RepresentationModel<PersonDTO> {
    private UUID id;
    private String name;
    private String pass;
    private String  rol;

    public PersonDTO() {
    }

    public PersonDTO(UUID id, String name, String  pass,String  rol) {
        this.id = id;
        this.name = name;
        this.rol = rol;
        this.pass= pass;
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
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, pass);
    }
}
