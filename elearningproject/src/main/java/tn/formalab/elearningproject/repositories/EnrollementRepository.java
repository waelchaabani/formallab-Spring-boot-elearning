package tn.formalab.elearningproject.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import tn.formalab.elearningproject.model.CourseStudent;
import tn.formalab.elearningproject.model.Enrollement;

public interface EnrollementRepository extends JpaRepository<Enrollement, CourseStudent> {
}
