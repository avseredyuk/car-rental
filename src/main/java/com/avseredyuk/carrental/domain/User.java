package com.avseredyuk.carrental.domain;

import java.util.Date;

/**
 * Created by lenfer on 12/21/16.
 */
public class User implements BasicEntity {
    private Integer id = null;
    private String login;
    private String email;
    private Date registered;
    private String password;
    private String name;
    private String surname;
    private Role role;

    public enum Role {
        ADMINISTRATOR, CLIENT;
    }

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(Integer id, String login, String email, String password, String name, String surname, Role role) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    public User(String login, String email, String password, String name, String surname, Role role) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    public User(String login, String email, Date registered, String password, String name, String surname, Role role) {
        this.login = login;
        this.email = email;
        this.registered = new Date(registered.getTime());
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    public User(Integer id, String login, String email, Date registered, String password, String name, String surname, Role role) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.registered = new Date(registered.getTime());
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() { return login; }

    public void setLogin(String login) {
        this.login = login;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegistered() {
        return new Date(registered.getTime());
    }

    public void setRegistered(Date registered) {
        this.registered = new Date(registered.getTime());
    }
}
