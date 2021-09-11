package tn.formalab.elearningproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "instructor")
public class Instructor {

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


    @Column(name = "profession", nullable = false)
    public String profession;


    @Column(name = "accountState")
    public Boolean accountState = true;

    @Column(name = "role")
    public String role = "instructor";

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    @JsonIgnoreProperties("blog")
    public Blog blog;


}

