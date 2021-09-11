package tn.formalab.elearningproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.formalab.elearningproject.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findByEmail(String email);
}