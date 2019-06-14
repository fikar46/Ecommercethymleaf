package com.belajar.restapi.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.belajar.restapi.entity.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
 * This is a Javadoc comment
// * @param <T> the parameter of the class
 */
public interface UsersRepository extends JpaRepository<Users, Integer> {
    @Query(value = "select * from USERS where USERNAME=:username and PASSWORD=:password",nativeQuery = true)
    Users findByUsernameAndPassword(@Param("username") String username,@Param("password") String password);
    @Query(value = "select * from USERS where USERNAME=:username",nativeQuery = true)
    Users findByUsername(@Param("username") String username);
}
