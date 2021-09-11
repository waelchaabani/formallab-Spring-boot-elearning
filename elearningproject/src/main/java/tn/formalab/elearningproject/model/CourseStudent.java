package tn.formalab.elearningproject.model;


import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class CourseStudent implements Serializable {
    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "course_id")
    private Integer courseId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseStudent)) return false;
        CourseStudent that = (CourseStudent) o;
        return Objects.equals(studentId, that.studentId) && Objects.equals(courseId, that.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId);
    }
}
