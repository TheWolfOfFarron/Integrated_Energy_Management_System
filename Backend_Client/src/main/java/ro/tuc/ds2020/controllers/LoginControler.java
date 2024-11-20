package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.entities.Person;
import ro.tuc.ds2020.sec.JWTUtils;
import ro.tuc.ds2020.services.PersonService;
import sun.net.www.protocol.http.HttpURLConnection;

import javax.validation.Valid;
import java.io.OutputStream;
import java.net.URL;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/login")
public class LoginControler {

    private final PersonService personService;

    private final JWTUtils jwtUtils;

    @Autowired
    public LoginControler(PersonService personService,JWTUtils jwtUtils) {
        this.personService = personService;
        this.jwtUtils=jwtUtils;
    }

    @GetMapping(value = "/{user}/{pass}")
    public ResponseEntity<String> login(@PathVariable("user") String  user, @PathVariable("pass") String pass){
        Person dto =personService.login(user,pass);

        if(dto==null)
            return new ResponseEntity<>("{}", HttpStatus.NOT_ACCEPTABLE);
        else {
          String token=  jwtUtils.generateToken(dto);
            String ret= "{" +
                    "\"user\":\""+dto.getRol()+ "\"," +
                    "\"username\":\""+dto.getName()+"\"," +
                    "\"token\":\""+token+ "\"" +
                    "}";
            return new ResponseEntity<>(ret, HttpStatus.OK);
        }

    }

    @PostMapping()
    public ResponseEntity<UUID> insertProsumer() {
        PersonDetailsDTO personDTO=new PersonDetailsDTO();
        personDTO.setName("ADMIN");
        personDTO.setPass("ADMIN");
        personDTO.setRol("ADMIN");
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


            System.out.println("Dsadsada");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        UUID personID = personService.insert(personDTO);
        return new ResponseEntity<>(personID, HttpStatus.CREATED);
    }




}
