package br.com.devtree.model;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
public class Student extends AbstractEntity {

    @NotEmpty
    private String name;

    @NotEmpty
    @Email
    private String email;

    public Student(String name) {
        this.name = name;
    }

    public Student(Long id) {
        super(id);
    }

    public Student(Long id, String name) {
        this(name);
        this.setId(id);
    }

    public Student() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
