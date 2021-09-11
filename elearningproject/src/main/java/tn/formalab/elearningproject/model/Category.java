package tn.formalab.elearningproject.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")

public class Category {
    @Id
    @GeneratedValue
    public Integer id;
    @Column(name= "name",unique = true,nullable = false)
    public String name;
    @Column(name= "image",nullable = false)
    public String image ;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Course> courses;
}

