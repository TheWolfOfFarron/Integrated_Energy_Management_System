package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.dtos.builders.PersonBuilder;
import ro.tuc.ds2020.entities.Person;
import ro.tuc.ds2020.repositories.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class PersonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonDTO> findPersons() {
        List<Person> personList = personRepository.findAll();
        System.out.println(personList.toString());
        return personList.stream()
                .map(PersonBuilder::toPersonDTO)
                .collect(Collectors.toList());
    }

    public PersonDetailsDTO findPersonById(UUID id) {
        Optional<Person> prosumerOptional = personRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Person with id {} was not found in db", id);
            throw new ResourceNotFoundException(Person.class.getSimpleName() + " with id: " + id);
        }
        return PersonBuilder.toPersonDetailsDTO(prosumerOptional.get());
    }
    public PersonDetailsDTO findPersonByUser(String user) {
        List<Person> prosumerOptional= personRepository.findByName(user);

        if (prosumerOptional.isEmpty()) {
            LOGGER.error("Person with id {} was not found in db");
            throw new ResourceNotFoundException(Person.class.getSimpleName() );
        }
        return  PersonBuilder.toPersonDetailsDTO(prosumerOptional.get(0));
    }


    public UUID insert(PersonDetailsDTO personDTO) {
        Person person = PersonBuilder.toEntity(personDTO);
        person = personRepository.save(person);
        LOGGER.debug("Person with id {} was inserted in db", person.getId());
        return person.getId();
    }
    public UUID delete(PersonDetailsDTO personDTO) {
        Person person = PersonBuilder.toComleteEntity(personDTO);
         personRepository.delete(person);
        System.out.println("Person with id {} was deleted in db");
        return person.getId();
    }

    public UUID update(PersonDetailsDTO personDTO){
        Person person =PersonBuilder.toComleteEntity((personDTO));
        person = personRepository.save(person);
        LOGGER.debug("Person with id {} was updated in db", person.getId());
        return person.getId();

    }


    public Person login(String user, String pass){
        Person personDTO=personRepository.Login(user,pass);

        return personDTO;
    }

    public Integer deleteByUser(String personDTO) {

        personRepository.deleteByName(personDTO);
        return 0;
    }
}
