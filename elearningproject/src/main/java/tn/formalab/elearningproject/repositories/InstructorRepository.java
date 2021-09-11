package tn.formalab.elearningproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.formalab.elearningproject.model.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
    Instructor findByEmail(String email);

}
