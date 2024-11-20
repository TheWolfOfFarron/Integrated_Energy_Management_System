package ro.tuc.ds2020.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.ActiveUsersDTO;
import ro.tuc.ds2020.entities.ActiveUsers;
import ro.tuc.ds2020.services.PersonService;
import sun.net.www.protocol.http.HttpURLConnection;

import javax.validation.Valid;
import java.net.URL;
import java.util.List;
import java.util.UUID;

import static jdk.nashorn.internal.runtime.PropertyDescriptor.GET;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@Transactional
@RequestMapping(value = "/user")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public ResponseEntity<List<ActiveUsersDTO>> getPersons() {
        List<ActiveUsersDTO> dtos = personService.findPersons();
        for (ActiveUsersDTO dto : dtos) {
            Link personLink = linkTo(methodOn(PersonController.class)
                    .getPerson(dto.getId())).withRel("personDetails");
            dto.add(personLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertProsumer( @RequestBody ActiveUsersDTO personDTO) {
        System.out.println(personDTO.getUsername());
        UUID personID = personService.insert(personDTO);

        return new ResponseEntity<>(personID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")//"35bf8e1b-c07a-4a86-9a74-db30a6afc03d"
    public ResponseEntity<ActiveUsersDTO> getPerson(@PathVariable("id") UUID personId) {
        ActiveUsersDTO dto = personService.findPersonById(personId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping(value = "/up={id}")
    public ResponseEntity<UUID>updatePerson(@Valid @RequestBody ActiveUsersDTO personDTO,@PathVariable("id") String Name){
        if(Name.equals(22)){
        System.out.println(personDTO.getId());
        UUID dto = personService.update(personDTO);
        return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        else {
            ActiveUsersDTO users=personService.findPersonByUser(Name);
            personDTO.setId(users.getId());
            UUID dto = personService.update(personDTO);

            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/del")
    public ResponseEntity<UUID>deletePerson(@Valid @RequestBody ActiveUsersDTO personDTO) {
        ActiveUsersDTO person = personService.findPersonByUser(personDTO.getUsername());
        personDTO.setId(person.getId());
        System.out.println(person.getId());
        UUID dto = personService.delete(personDTO);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    @PostMapping(value = "/dela")
    public ResponseEntity<Integer>deleteByUser(@Valid @RequestBody ActiveUsersDTO personDTO) {

         personService.deleteall(personDTO.getUsername());
        return new ResponseEntity<>(22, HttpStatus.OK);
    }



}
