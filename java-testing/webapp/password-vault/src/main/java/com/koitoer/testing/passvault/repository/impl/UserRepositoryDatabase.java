package com.koitoer.testing.passvault.repository.impl;

import com.koitoer.testing.passvault.dto.User;
import com.koitoer.testing.passvault.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User Repository
 * Created by mmena on 3/25/17.
 */
@Repository
public class UserRepositoryDatabase implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserRepositoryDatabase(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Get the list of the entire users
     * @return
     */
    public List<User> findUsers(){
        return jdbcTemplate.query("SELECT * FROM USERS", new Object[]{}, (resultSet, i) -> {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
            user.setPassword(resultSet.getString("password"));
            user.setUserId(resultSet.getString("userId"));
            return user;
        });
    }


    /**
     * Get user using the userId
     * @param id
     * @return
     */
    public User getUser(Long id){
        return new User();
    }


    /**
     * Delete the user based on userId
     * @param id
     */
    public void deleteUser(Long id){

    }


}
