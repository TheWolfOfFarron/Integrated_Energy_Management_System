package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ro.tuc.ds2020.entities.Device;

import java.util.List;

import java.util.UUID;

public interface DeviceRepository extends JpaRepository<Device, UUID> {

    /**
     * Example: JPA generate Query by Field
     */

    List<Device> findByUsername(String name);
    Integer deleteByUsername(String name);



    /**
     * Example: Write Custom Query
     *  @Query("select p from Person p where p.name = :name")
     *     Optional<Person> findByName(@Param("name") String  name);
     */

    @Modifying
    @Query("update Device set username= :name where username = :name2")
    Integer  updateName(@Param("name")String name,@Param("name2") String name2);


}
