package tn.formalab.elearningproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.formalab.elearningproject.model.Course;

public interface CourseRepository  extends JpaRepository<Course, Integer> {
}
