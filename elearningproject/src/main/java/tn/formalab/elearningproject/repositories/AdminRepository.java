package tn.formalab.elearningproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.formalab.elearningproject.model.Admin;
import tn.formalab.elearningproject.model.Instructor;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findByEmail(String email);

}
