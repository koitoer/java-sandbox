package com.koitoer.testing.passvault.repository.impl;

import com.koitoer.testing.passvault.dto.User;
import com.koitoer.testing.passvault.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Local Implementation of the User repository with hard coded values.
 * Created by mmena on 3/31/17.
 */
public class UserRepositoryLocal implements UserRepository{

    private final static Set<User> users = new HashSet<>();

    static{
        users.add(new User(1L, "user1", "password1", new int[]{1,2,3,4}, "User 1"));
        users.add(new User(2L, "user2", "paSSword1", new int[]{11,22,33,44}, "User 2"));
        users.add(new User(3L, "user3", "passWord1", new int[]{11,12,13,14}, "User 3"));
        users.add(new User(4L, "user4", "password4", new int[]{1,2,3,4,5,6}, "User Last"));
    }

    @Override
    public List<User> findUsers() {
        return users.stream().collect(Collectors.toList());
    }

    @Override
    public User getUser(Long id) {
        return this.findUsers().stream().filter(user -> user.getId().equals(id)).findAny().get();
    }

    @Override
    public void deleteUser(Long id) {
        User user = this.getUser(id);
        users.remove(user);
    }
}
