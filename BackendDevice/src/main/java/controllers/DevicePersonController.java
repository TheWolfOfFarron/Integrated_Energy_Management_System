package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.DeviceDTO;
import ro.tuc.ds2020.services.DeviceService;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@CrossOrigin
@Transactional
@RequestMapping(value = "/deviceUser")
public class DevicePersonController {

    private final DeviceService personService;

    @Autowired
    public DevicePersonController(DeviceService personService) {
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

}