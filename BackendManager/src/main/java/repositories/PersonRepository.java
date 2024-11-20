package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.tuc.ds2020.entities.DeviceData;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<DeviceData, UUID> {

    /**
     * Example: JPA generate Query by Field
     */

  //  List<Person> findByName(String name);
    //void deleteByIdDevice(UUID name);


    @Query("select d from DeviceData  d where d.device.idDevice =:id order by  d.time desc ")
    List<DeviceData> findByNames(@Param("id") UUID  name);


    @Query("select  d.time,d.consuption,d.device.idDevice from DeviceData  d ")
    List<DeviceData> findByNames();
    /**
     * Example: Write Custom Query
     *  @Query("select p from Person p where p.name = :name")
     *     Optional<Person> findByName(@Param("name") String  name);
     */


       // @Query("select p from Person p where p.name = :name and p.pass = :pass")
     //   Person Login(@Param("name") String  name, @Param("pass") String  pass);

}
