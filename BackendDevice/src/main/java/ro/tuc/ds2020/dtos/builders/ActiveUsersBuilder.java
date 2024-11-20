package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.ActiveUsersDTO;
import ro.tuc.ds2020.dtos.DeviceDTO;
import ro.tuc.ds2020.entities.ActiveUsers;
import ro.tuc.ds2020.entities.Device;

public class ActiveUsersBuilder {


    public static ActiveUsersDTO toPersonDTO(ActiveUsers device) {
        return new ActiveUsersDTO(device.getId(), device.getName());
    }



    public static ActiveUsers toEntity(ActiveUsersDTO dto){
        return new ActiveUsers(dto.getUsername());
    }

    public static ActiveUsers toCompleteEntity(ActiveUsersDTO dto){
        return new ActiveUsers(dto.getId(),dto.getUsername());
    }


}
