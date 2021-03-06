package br.com.devfour.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class UserSys extends AbstractEntity{

    @NotEmpty
    @Column(unique=true)
    private String username;

    @NotEmpty
    @JsonIgnore
    private String password;

    @NotEmpty
    private String name;

    @NotEmpty
    private boolean ehAdmin;

    public UserSys(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public boolean isEhAdmin() {
        return ehAdmin;
    }

    public void setEhAdmin(boolean ehAdmin) {
        this.ehAdmin = ehAdmin;
    }
}
