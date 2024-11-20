package ro.tuc.ds2020.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.dtos.builders.PersonBuilder;
import ro.tuc.ds2020.entities.Person;
import ro.tuc.ds2020.services.PersonService;
import sun.net.www.protocol.http.HttpURLConnection;

import javax.validation.Valid;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;
import java.util.Stack;
import java.util.UUID;

import static jdk.nashorn.internal.runtime.PropertyDescriptor.GET;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/person")// /{user}{pass}
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
        public ResponseEntity<List<PersonDTO>> getPersons() {
        List<PersonDTO> dtos = personService.findPersons();
        System.out.printf(dtos.toString());
        for (PersonDTO dto : dtos) {
            Link personLink = linkTo(methodOn(PersonController.class)
                    .getPerson(dto.getId())).withRel("personDetails");
            dto.add(personLink);
        }
        System.out.printf(dtos.toString());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody PersonDetailsDTO personDTO) {
        try {
            URL url = new URL("http://localhost:8081/user");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            String jsonData ="{\"username\":\""+personDTO.getName()+"\"}";
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonData.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            int responseCode = con.getResponseCode();
            System.out.println(responseCode);


        }catch (Exception e)
        {
            e.printStackTrace();
        }
        UUID personID = personService.insert(personDTO);
        return new ResponseEntity<>(personID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")//"35bf8e1b-c07a-4a86-9a74-db30a6afc03d"
    public ResponseEntity<PersonDetailsDTO> getPerson(@PathVariable("id") UUID personId) {
        PersonDetailsDTO dto = personService.findPersonById(personId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @PutMapping(value = "/up={id}")
    public ResponseEntity<UUID>updatePerson(@Valid @RequestBody PersonDetailsDTO personDTO, @PathVariable("id") String name){
        if(name!="22") {
            try {
                URL url = new URL("http://localhost:8081/user/up=" + name);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("PUT");
                con.setRequestProperty("Content-Type", "application/json");
                con.setDoOutput(true);
                String jsonData = "{\"username\":\"" + personDTO.getName() + "\"}";
                try (OutputStream os = con.getOutputStream()) {
                    byte[] input = jsonData.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }
                int responseCode = con.getResponseCode();
                System.out.println(responseCode);

                 url = new URL("http://localhost:8081/device/up=" + name);
                 con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("PUT");
                con.setRequestProperty("Content-Type", "application/json");
                con.setDoOutput(true);
                 jsonData = "{\"username\":\"" + personDTO.getName() + "\"}";
                try (OutputStream os = con.getOutputStream()) {
                    byte[] input = jsonData.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }
                 responseCode = con.getResponseCode();
                System.out.println(responseCode);
                System.out.println("Dsadsada");
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(name.equals("22"))
                name= personDTO.getName();
            try {
                URL url = new URL("http://localhost:8081/device/up=" + name);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("PUT");
                con.setRequestProperty("Content-Type", "application/json");
                con.setDoOutput(true);
                String jsonData = "{\"username\":\"" + personDTO.getName() + "\"}";
                try (OutputStream os = con.getOutputStream()) {
                    byte[] input = jsonData.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }
                int responseCode = con.getResponseCode();
                System.out.println(responseCode);


                System.out.println("Dsadsada");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        PersonDetailsDTO person = personService.findPersonByUser(name);
        personDTO.setId(person.getId());
        UUID dto = personService.update(personDTO);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping(value = "/del")
    public ResponseEntity<Integer>deletePerson(@Valid @RequestBody PersonDetailsDTO personDTO) {
        PersonDetailsDTO person = personService.findPersonByUser(personDTO.getName());
        personDTO.setId(person.getId());

        try {
            URL url = new URL("http://localhost:8081/user/dela");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            String jsonData ="{\"username\":\""+personDTO.getName()+"\"}";
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonData.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            int responseCode = con.getResponseCode();
            System.out.println(responseCode);


            System.out.println("Dsadsada");
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        try {
            URL url = new URL("http://localhost:8081/device/dela");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            String jsonData ="{\"username\":\""+personDTO.getName()+"\"}";
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonData.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            int responseCode = con.getResponseCode();
            System.out.println(responseCode);


            System.out.println("Dsadsada");
        }catch (Exception e)
        {
            e.printStackTrace();
        }


        System.out.println(person.getId());
        personService.deleteByUser(personDTO.getName());
        return new ResponseEntity<>(0, HttpStatus.OK);
    }


}
