package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.entities.Person;

public class PersonBuilder {

    private PersonBuilder() {
    }

    public static PersonDTO toPersonDTO(Person person) {
        return new PersonDTO(person.getId(), person.getName(), person.getPass(),person.getRol());
    }

    public static PersonDetailsDTO toPersonDetailsDTO(Person person) {
        return new PersonDetailsDTO(person.getId(), person.getName(), person.getPass(),person.getRol());
    }

    public static Person toEntity(PersonDetailsDTO personDetailsDTO) {
        return new Person(personDetailsDTO.getName(),
                personDetailsDTO.getPass(),
                personDetailsDTO.getRol());
    }

    public static Person toComleteEntity(PersonDetailsDTO personDetailsDTO) {
        return new Person(personDetailsDTO.getId(), personDetailsDTO.getName(),
                personDetailsDTO.getPass(),
                personDetailsDTO.getRol());
    }
}
