package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.entities.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {

    /**
     * Example: JPA generate Query by Field
     */

    List<Person> findByName(String name);
    void deleteByName(String name);




    /**
     * Example: Write Custom Query
     *  @Query("select p from Person p where p.name = :name")
     *     Optional<Person> findByName(@Param("name") String  name);
     */


        @Query("select p from Person p where p.name = :name and p.pass = :pass")
        Person Login(@Param("name") String  name, @Param("pass") String  pass);

}
