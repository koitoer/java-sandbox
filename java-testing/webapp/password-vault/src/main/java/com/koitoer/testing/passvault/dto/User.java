package com.koitoer.testing.passvault.dto;

/**
 * Created by mmena on 3/25/17.
 */
public class User {

    private Long id;

    private String userId;

    private String password;

    private int[] pin;

    private String name;

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public User(Long id, String userId, String password, int[] pin, String name) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.pin = pin;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id != null ? id.equals(user.id) : user.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

