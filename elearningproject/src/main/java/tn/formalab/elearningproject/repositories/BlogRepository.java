package tn.formalab.elearningproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.formalab.elearningproject.model.Blog;
import tn.formalab.elearningproject.model.Course;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
}
