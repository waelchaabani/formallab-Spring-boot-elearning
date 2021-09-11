package tn.formalab.elearningproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "blog")
public class Blog {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "name",unique = true,nullable = false)
    public String name;


    @Column(name = "description",nullable = false )
    public String description;

    @Column(name = "image",nullable = false)
    public String image;



    @Column(name = "instructor",nullable = false)
    public String instructor;


    @Column(name = "date",nullable = false )
    public String date;

    @Column(name = "tag",nullable = false)
    public String tag;

    @OneToMany(mappedBy = "blog")
    @JsonIgnoreProperties("blog")
    public List<Instructor> instructors;








}

