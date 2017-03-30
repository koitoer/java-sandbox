package com.koitoer.testing.passvault.controller;

import com.koitoer.testing.passvault.dto.User;
import com.koitoer.testing.passvault.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by mmena on 3/25/17.
 */
@RestController
@RequestMapping("/api/user")
public class ApiController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public List<User> getAllUsers(){
        return userRepository.findUsers();
    }

    @RequestMapping("/{id}")
    public User getUser(@PathVariable("id") String id){
        List<User> users = userRepository.findUsers();
        User userToSearch = new User(Long.valueOf(id));
        boolean userExists = users.contains(new User(Long.valueOf(id)));
        if(userExists){
            int index = users.indexOf(userToSearch);
            if(index >= 0){
                userToSearch = users.get(index);
            }
        }else{
            throw new RuntimeException(String.format("User {} does not exists", id));
        }
        return userToSearch;
    }


}
