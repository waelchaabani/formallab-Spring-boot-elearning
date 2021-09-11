package tn.formalab.elearningproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Calendar;
import java.util.GregorianCalendar;


@Entity
public class Enrollement {
    @Id
    @GeneratedValue
    public Integer id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonIgnoreProperties("enrollments")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonIgnoreProperties("enrollments")
    private Course course;

    private Calendar enrollment_date = new GregorianCalendar();
    private float progress = 0;
}




