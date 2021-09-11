package tn.formalab.elearningproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        public int id;

        @Column(name = "name",unique = true,nullable = false)
        public String name;


        @Column(name = "description",nullable = false )
        public String description;

        @Column(name = "image",nullable = false)
        public String image;

        @Column(name = "price",nullable = false)
        public double price;


        @Column(name = "instructor",nullable = false)
        public String instructor;


        @Column(name = "date",nullable = false)
        public String date;


        @Column(name = "tag",nullable = false)
        public String tag;

        @Column(name = "difficulty", nullable = false)
        public String difficulty;


        @Column(name = "estimated_duration", nullable = false)
        public String estimated_duration;

        @OneToMany(mappedBy = "course")
        @JsonIgnoreProperties("course")
        private List<Enrollement> enrollments;


        @ManyToOne
        @JoinColumn(name = "idcategory")
        public Category category;

        @ManyToOne
        @JoinColumn(name = "student_id")
        private Student student;


}
