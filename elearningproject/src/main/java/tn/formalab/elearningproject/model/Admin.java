package tn.formalab.elearningproject.model;

import javax.persistence.*;


@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue
    public Integer id;

    @Column(name = "firstname", nullable = false)
    public String firstname;

    @Column(name = "lastname", nullable = false)
    public String lastname;

    @Column(name = "email", unique = true, nullable = false)
    public String email;

    @Column(name = "password", nullable = false)
    public String password;

    @Column(name = "role")
    public String role = "admin";

    @Column(name = "accountState")
    public Boolean accountState = true;



}
