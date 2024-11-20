package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.tuc.ds2020.dtos.DeviceDTO;
import ro.tuc.ds2020.dtos.DeviceDataDetailsDTO;
import ro.tuc.ds2020.dtos.builders.DeviceDataBuilder;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.DeviceData;
import ro.tuc.ds2020.repositories.DeviceRepo;
import ro.tuc.ds2020.repositories.PersonRepository;

import java.util.UUID;

@Service
@Transactional
public class DeviceService {


    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceDataService.class);
    private final DeviceRepo personRepository;

    @Autowired
    public DeviceService(DeviceRepo personRepository) {
        this.personRepository = personRepository;
    }


    public Device findbyDevice(UUID id){

        System.out.println(personRepository.findAll());
        System.out.println(id);
        return personRepository.findDeviceByIdDevice(id);
    }

    public UUID insert(Device personDTO) {
        personDTO = personRepository.save(personDTO);
        return personDTO.getId();
    }

    public int delete(Device device){
        try {
            personRepository.delete(device);
        }catch (Exception e){
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    public int update(Device device){
        try {
            personRepository.save(device);
        }catch (Exception e){
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

}
