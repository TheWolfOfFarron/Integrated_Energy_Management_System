package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.DeviceDataDTO;
import ro.tuc.ds2020.dtos.DeviceDataDetailsDTO;
import ro.tuc.ds2020.dtos.builders.DeviceDataBuilder;
import ro.tuc.ds2020.entities.DeviceData;
import ro.tuc.ds2020.repositories.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class DeviceDataService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceDataService.class);
    private final PersonRepository personRepository;

    @Autowired
    public DeviceDataService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<DeviceDataDTO> findPersons() {
        List<DeviceData> deviceDataList = personRepository.findAll();
        System.out.println(deviceDataList.toString());
        return deviceDataList.stream()
                .map(DeviceDataBuilder::toPersonDTO)
                .collect(Collectors.toList());
    }

    public DeviceDataDetailsDTO findPersonById(UUID id) {
        Optional<DeviceData> prosumerOptional = personRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Person with id {} was not found in db", id);
            throw new ResourceNotFoundException(DeviceData.class.getSimpleName() + " with id: " + id);
        }
        return DeviceDataBuilder.toPersonDetailsDTO(prosumerOptional.get());
    }
    public DeviceDataDetailsDTO findPersonByUser(String user) {/*
        List<Person> prosumerOptional;= personRepository.findByName(user);

        if (prosumerOptional.isEmpty()) {
            LOGGER.error("Person with id {} was not found in db");
            throw new ResourceNotFoundException(Person.class.getSimpleName() );
        }
        return  PersonBuilder.toPersonDetailsDTO(prosumerOptional.get(0));*/
        return null;
    }


    public UUID insert(DeviceDataDetailsDTO personDTO) {
        DeviceData deviceData = DeviceDataBuilder.toEntity(personDTO);
        deviceData = personRepository.save(deviceData);
        LOGGER.debug("Person with id {} was inserted in db", deviceData.getId());
        return deviceData.getId();
    }

    public List<DeviceData> findbyidord(UUID name){
        List<DeviceData> deviceDataList= personRepository.findByNames(name);
        return deviceDataList;

    }
    public List<DeviceData> find(){
        List<DeviceData> deviceDataList= personRepository.findByNames();
        return deviceDataList;

    }
    public UUID delete(DeviceDataDetailsDTO personDTO) {
        DeviceData deviceData = DeviceDataBuilder.toComleteEntity(personDTO);
         personRepository.delete(deviceData);
        System.out.println("Person with id {} was deleted in db");
        return deviceData.getId();
    }

    public UUID update(DeviceDataDetailsDTO personDTO){
        DeviceData deviceData = DeviceDataBuilder.toComleteEntity((personDTO));
        deviceData = personRepository.save(deviceData);
        LOGGER.debug("Person with id {} was updated in db", deviceData.getId());
        return deviceData.getId();

    }



    public Integer deleteByUser(String personDTO) {

       // personRepository.deleteByName(personDTO);
        return 0;
    }
}
