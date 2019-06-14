package com.belajar.restapi.service;

import com.belajar.restapi.dto.UsersDTO;
import com.belajar.restapi.entity.Users;
import com.belajar.restapi.error.Datanotfound;
import com.belajar.restapi.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This is a Javadoc comment
 */
@Component
@Service
@Slf4j
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;


    /**
     * This is a Javadoc comment
     * @return List<Users> get all data
     * get all data
     */

    public List<Users> findAll() {
        return usersRepository.findAll();
    }
    /**
     * This is a get data by id
     * @param id the id to be used
     * @return Users get data by id
     */
    public Users findById(Integer id) {
        Optional<Users> optionalUsers = usersRepository.findById(id);
        if(!optionalUsers.isPresent()){
            throw  new Datanotfound("Users tidak ditemukan");
        }
        return optionalUsers.get();
    }
    public Users findByUsernameAndPassword(String username,String password) {

        Users users = new Users();
        users.setUsername(username);
        users = usersRepository.findByUsernameAndPassword(username,password);
        if(users == null){
            throw  new Datanotfound("Users tidak ditemukan");
        }
        return users;
    }
    public Users findByUsername(String username) {

        Users users = new Users();
        users.setUsername(username);
        users = usersRepository.findByUsername(username);
        if(users == null){
            throw  new Datanotfound("Users tidak ditemukan");
        }
        return users;
    }
    /**
     * This is a Javadoc comment
     * @param id the id to be used
     * @param usersDTO the hardware to be used
     * @return Users update data
     * update data by id
     */
    public UsersDTO update(Integer id, UsersDTO usersDTO) {
        Users users = new Users();
        users.setId(id);
        users.setUsername(usersDTO.getUsername());
        users.setWallet(usersDTO.getWallet());

        usersRepository.save(users);
        return usersDTO;
    }
    /**
     * This is a Javadoc comment
     * @param usersDTO the hardware to be used
     * @return Users create data
     * create data
     */
    public UsersDTO create(UsersDTO usersDTO) {
        Users users = new Users();
        users.setId(usersDTO.getId());
        users.setUsername(usersDTO.getUsername());
        users.setWallet(usersDTO.getWallet());
        usersRepository.save(users);
        return usersDTO;
    }
    /**
     * This is a Javadoc comment
     * @param id the id to be used
     * delete data
     */
    public void delete(Integer id) {
        usersRepository.deleteById(id);
    }
}
