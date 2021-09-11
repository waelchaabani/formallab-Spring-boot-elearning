package tn.formalab.elearningproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.formalab.elearningproject.model.Category;

public interface CategoryRepository  extends JpaRepository<Category, Integer> {
}

