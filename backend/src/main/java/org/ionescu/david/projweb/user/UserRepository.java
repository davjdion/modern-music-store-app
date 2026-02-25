package org.ionescu.david.projweb.user;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface UserRepository extends ListCrudRepository<User, Integer> {

    @Query("SELECT * FROM \"USER\" WHERE email = :email")
    List<User> findByEmail(String email);

    @Query("SELECT * FROM \"USER\" WHERE email = :email AND password = :password")
    List<User> findByEmailAndPassword(String email, String password);

}
