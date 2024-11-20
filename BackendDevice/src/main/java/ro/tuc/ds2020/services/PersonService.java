package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.ActiveUsersDTO;


import ro.tuc.ds2020.dtos.builders.ActiveUsersBuilder;


import ro.tuc.ds2020.entities.ActiveUsers;


import ro.tuc.ds2020.repositories.ActiveUsersRepository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class PersonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
    private final ActiveUsersRepository personRepository;

    @Autowired
    public PersonService(ActiveUsersRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<ActiveUsersDTO> findPersons() {
        List<ActiveUsers> personList = personRepository.findAll();
        return personList.stream()
                .map(ActiveUsersBuilder::toPersonDTO)
                .collect(Collectors.toList());
    }

    public ActiveUsersDTO findPersonById(UUID id) {
        Optional<ActiveUsers> prosumerOptional = personRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Person with id {} was not found in db", id);
            throw new ResourceNotFoundException(ActiveUsers.class.getSimpleName() + " with id: " + id);
        }
        return ActiveUsersBuilder.toPersonDTO(prosumerOptional.get());
    }
    public ActiveUsersDTO findPersonByUser(String user) {
        List<ActiveUsers> prosumerOptional= personRepository.findByUsername(user);

        if (prosumerOptional.isEmpty()) {
            LOGGER.error("Person with id {} was not found in db");
            throw new ResourceNotFoundException(ActiveUsers.class.getSimpleName() );
        }
        return  ActiveUsersBuilder.toPersonDTO(prosumerOptional.get(0));
    }


    public UUID insert(ActiveUsersDTO personDTO) {
        ActiveUsers person = ActiveUsersBuilder.toEntity(personDTO);
        System.out.println(person.getName()+ "dsadasduyasdhiojasod");
        person = personRepository.save(person);
        LOGGER.debug("Person with id {} was inserted in db", person.getId());
        return person.getId();
    }
    public UUID delete(ActiveUsersDTO personDTO) {
        ActiveUsers person = ActiveUsersBuilder.toCompleteEntity(personDTO);
         personRepository.delete(person);
        System.out.println("Person with id {} was deleted in db");
        return person.getId();
    }

    public UUID update(ActiveUsersDTO personDTO){
        ActiveUsers person =ActiveUsersBuilder.toCompleteEntity((personDTO));
        person = personRepository.save(person);
        LOGGER.debug("Person with id {} was updated in db", person.getId());
        return person.getId();

    }

    public void deleteall(String name){
        personRepository.deleteByUsername(name);
    }
}
