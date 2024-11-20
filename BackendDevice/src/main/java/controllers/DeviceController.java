package ro.tuc.ds2020.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.ActiveUsersDTO;
import ro.tuc.ds2020.dtos.DeviceDTO;
import ro.tuc.ds2020.entities.ActiveUsers;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.services.DeviceService;
import ro.tuc.ds2020.services.PersonService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@Transactional
@RequestMapping(value = "/device")
public class DeviceController {

    private final DeviceService personService;

    @Autowired
    public DeviceController(DeviceService personService) {
        this.personService = personService;
    }


    @GetMapping(value="/={username}")
    public ResponseEntity<List<DeviceDTO>> findByUsername(@Valid @PathVariable String username){
        List<DeviceDTO> dtos = personService.findByUsername(username);
        for (DeviceDTO dto : dtos) {
            Link personLink = linkTo(methodOn(PersonController.class)
                    .getPerson(dto.getId())).withRel("personDetails");
            dto.add(personLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<DeviceDTO>> getPersons() {
        List<DeviceDTO> dtos = personService.findPersons();
        for (DeviceDTO dto : dtos) {
            Link personLink = linkTo(methodOn(PersonController.class)
                    .getPerson(dto.getId())).withRel("personDetails");
            dto.add(personLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody DeviceDTO personDTO) {
        UUID personID = personService.insert(personDTO);
        return new ResponseEntity<>(personID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")//"35bf8e1b-c07a-4a86-9a74-db30a6afc03d"
    public ResponseEntity<DeviceDTO> getPerson(@PathVariable("id") UUID personId) {
        DeviceDTO dto = personService.findPersonById(personId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }



    @PutMapping(value = "/up={id}")
    public ResponseEntity<UUID>updatePerson(@Valid @RequestBody DeviceDTO personDTO, @PathVariable("id") String userid){
        System.out.println("rtyuio");
            UUID dto = personService.update(personDTO);
            return new ResponseEntity<>(dto, HttpStatus.OK);

    }




    @PostMapping(value = "/del")
    public ResponseEntity<UUID>deletePerson(@Valid @RequestBody DeviceDTO personDTO) {

        UUID dto = personService.delete(personDTO);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @PostMapping(value = "/dela")
    public ResponseEntity<String> deleteAll(@Valid @RequestBody DeviceDTO deviceDTO){

       Integer ret=personService.deleteByUsername(deviceDTO.getUser());
       String a="{\"ret\"=\""+ret+"\" }";
        return new ResponseEntity<>(a, HttpStatus.OK);
    }

    //TODO: UPDATE, DELETE per resource "2c3ef70a-1870-4c30-8753-6451f1906378"

}
