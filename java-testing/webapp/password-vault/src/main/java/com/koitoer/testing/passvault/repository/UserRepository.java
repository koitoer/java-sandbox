package com.koitoer.testing.passvault.repository;

import com.koitoer.testing.passvault.dto.User;

import java.util.List;

/**
 * Created by mmena on 3/31/17.
 */
public interface UserRepository {

    List<User> findUsers();
    User getUser(Long id);
    void deleteUser(Long id);
}
