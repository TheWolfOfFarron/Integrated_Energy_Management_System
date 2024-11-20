package ro.tuc.ds2020.services;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;


import ro.tuc.ds2020.dtos.DeviceDTO;


import ro.tuc.ds2020.dtos.builders.DeviceBuilder;
import ro.tuc.ds2020.entities.ActiveUsers;


import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.repositories.DeviceRepository;


import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

@Service
@Transactional
public class DeviceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
    private final static String QUEUE_NAME = "Device";

    private final DeviceRepository personRepository;
    ConnectionFactory factory;
    @Autowired
    public DeviceService(DeviceRepository personRepository) {
        this.personRepository = personRepository;
        this.factory= new ConnectionFactory();
        factory.setHost("kangaroo.rmq.cloudamqp.com");
        factory.setPort(5672);
        factory.setUsername("kjuqsuhe");
        factory.setPassword("IJXZhQ3ktm19dUaKxcRQMTMjsjKPYHHR");
        factory.setVirtualHost("kjuqsuhe");
    }

    public List<DeviceDTO> findPersons() {
        List<Device> personList = personRepository.findAll();
        return personList.stream()
                .map(DeviceBuilder::toPersonDTO)
                .collect(Collectors.toList());
    }

    public DeviceDTO findPersonById(UUID id) {
        Optional<Device> prosumerOptional = personRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Person with id {} was not found in db", id);
            throw new ResourceNotFoundException(ActiveUsers.class.getSimpleName() + " with id: " + id);
        }
        return DeviceBuilder.toPersonDTO(prosumerOptional.get());
    }



    public UUID insert(DeviceDTO personDTO) {
        Device person = DeviceBuilder.toEntity(personDTO);
        person = personRepository.save(person);
        LOGGER.debug("Person with id {} was inserted in db", person.getId());

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            String message = "i"+"{\"idDevice\":\"" + person.getId() + "\", \"maxHour\":\"" + person.getMaxHours() + "\"}";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }

        return person.getId();
    }
    public UUID delete(DeviceDTO personDTO) {
        Device person = DeviceBuilder.toComleteEntity(personDTO);
        personRepository.delete(person);
        System.out.println("Person with id {} was deleted in db");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            String message = "d"+"{\"idDevice\":\"" + person.getId() + "\", \"maxHour\":\"" + person.getMaxHours() + "\"}";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }


        return person.getId();
    }

    public UUID update(DeviceDTO personDTO){
        Device person =DeviceBuilder.toComleteEntity((personDTO));
        person = personRepository.save(person);
        LOGGER.debug("Person with id {} was updated in db", person.getId());
        System.out.println("cfgvhbjknlm;,tugybjknlmuhbjkngbjk");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            String message = "u"+"{\"idDevice\":\"" + person.getId() + "\", \"maxHour\":\"" + person.getMaxHours() + "\"}";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }

        return person.getId();

    }

    public Integer deleteByUsername(String user) {
        Integer ret = personRepository.deleteByUsername(user);
        return ret;
    }

    public List<DeviceDTO> findByUsername(String user){
        List<Device> ret =personRepository.findByUsername(user);
          return ret.stream()
                .map(DeviceBuilder::toPersonDTO)
                .collect(Collectors.toList());

    }
    public Integer updateName(String user, String newuser){

        return  personRepository.updateName(newuser,user);
    }



}
