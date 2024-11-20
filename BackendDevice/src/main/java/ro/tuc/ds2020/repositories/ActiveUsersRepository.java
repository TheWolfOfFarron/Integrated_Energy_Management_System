package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.tuc.ds2020.entities.ActiveUsers;

import java.util.List;
import java.util.UUID;

public interface ActiveUsersRepository extends JpaRepository<ActiveUsers, UUID> {

    /**
     * Example: JPA generate Query by Field
     */


    List<ActiveUsers> findByUsername(String name);
    void deleteByUsername(String name);

    /**
     * Example: Write Custom Query
     *  @Query("select p from Person p where p.name = :name")
     *     Optional<Person> findByName(@Param("name") String  name);
     */


}
