package com.belajar.restapi.implement;

import com.belajar.restapi.dto.UsersDTO;
import com.belajar.restapi.entity.Users;
import com.belajar.restapi.error.Datanotfound;
import com.belajar.restapi.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class UsersServiceImplTest {
    @Autowired
    UsersService usersService;

    @Test
    public void findAll() {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test", "belajarapi", "belajarapi")) {

            System.out.println("Java JDBC H2 Example");
            System.out.println("Connected to H2 database!");
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }

        List<Users> users= (List<Users>) usersService.findAll();
        assertThat(users.size())
                .isNotNull();
        log.info("size : "+users.size());
        log.info("---- List User ----");
        for(int i= 0; i < users.size(); i++){
            log.info("No : "+ (i+1));
            log.info("Username : "+ users.get(i).getUsername());
            log.info("wallet : "+ users.get(i).getWallet());
        }
    }

    @Test
    public void findById() {
        int id =2;
        Users optionalUsers =  usersService.findById(id);
        if(optionalUsers==null){
            throw  new Datanotfound("Users tidak ditemukan");
        }
        assertThat(optionalUsers.getId())
                .isEqualTo(id);
    }
    @Test
    public void update() {
        UsersDTO usersDTO = new UsersDTO();
        int id = 1;
        long wallet = 1500000;
        usersDTO.setWallet(wallet);
        UsersDTO update = usersService.update(id,usersDTO);
        log.info("wallet: "+update.getWallet());
        assertThat(update.getWallet())
                .isEqualTo(wallet);
    }

    @Test
    public void create() {
        UsersDTO usersDTO = new UsersDTO();
        List<Users> listusers= (List<Users>) usersService.findAll();
        int id = listusers.size()+1;
        String username = "roro";
        long wallet = 500000;

        log.info("--- set id ---");
        usersDTO.setId(id);
        log.info("--- set username ---");
        usersDTO.setUsername(username);
        log.info("--- set wallet ---");
        usersDTO.setWallet(wallet);
        UsersDTO create = usersService.create(usersDTO);
        log.info("--- get id ---");
        log.info("id: "+create.getId());
        log.info("--- get username ---");
        log.info("id: "+create.getUsername());
        log.info("--- get wallet ---");
        log.info("id: "+create.getWallet());
        assertThat(create.getId())
                .isEqualTo(id);
        assertThat(create.getUsername())
                .isEqualTo(username);
        assertThat(create.getWallet())
                .isEqualTo(wallet);
    }

    @Test
    public void delete() {
        int id = 1;
        log.info("--- sebelum terdelete ---");
        List<Users> listusers= (List<Users>) usersService.findAll();
        log.info("size "+listusers.size());
        usersService.delete(id);
        log.info(id+" terdelete");
        log.info("--- sesudah terdelete ---");
        List<Users> newUser= (List<Users>) usersService.findAll();
        log.info("size "+newUser.size());
        assertThat(listusers.size())
                .isGreaterThan(newUser.size());
    }
    @Test(expected = Datanotfound.class)
    public void findByIdfail() {
        int id =9;
        Users optionalUsers =  usersService.findById(id);
        if(optionalUsers==null){
            throw  new Datanotfound("Users tidak ditemukan");
        }
    }
}