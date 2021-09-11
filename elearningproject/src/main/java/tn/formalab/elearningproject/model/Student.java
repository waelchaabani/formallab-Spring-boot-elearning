package tn.formalab.elearningproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {
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




    @Column(name = "is_enabled")
    public Boolean isEnabled = true;

    @Column(name = "role")
    public String role = "student";





    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    public List<Course> courses;


    @OneToMany(mappedBy = "student")
    @JsonIgnore
    public List<Enrollement> enrollments;



}

