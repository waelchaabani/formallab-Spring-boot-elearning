package tn.formalab.elearningproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.formalab.elearningproject.model.Category;
import tn.formalab.elearningproject.model.Course;
import tn.formalab.elearningproject.repositories.CourseRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping("courses")

public class CourseController {
    private CourseRepository courseRepository;


    @Autowired
    public CourseController(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }
    @PostMapping(path = "add")
    public ResponseEntity<Course> addProduct(@RequestBody Course course){


        Course savedCourse = this.courseRepository.save(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
    }
    @GetMapping(path = "all")
    public ResponseEntity <List<Course>> getAllCourse(){


        List<Course> course = this.courseRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(course);
    }
    @GetMapping(path = "one/{id}")
    public ResponseEntity<Course> getProductById(@PathVariable Integer id  ) {
        try {
            Course course = courseRepository.findById(id).get();
            return ResponseEntity.status(HttpStatus.CREATED).body(course);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Course());

        }
    }
    @DeleteMapping(path = "delete/{id}")
    public ResponseEntity <Map<String,String>> deleteProduct(@PathVariable Integer id){


        this.courseRepository.deleteById(id);
        HashMap<String,String> map=new HashMap<>();
        map.put("m","Product deleted");
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }
    @PatchMapping (path = "update")
    public ResponseEntity<Course> updateCategory(@RequestBody Course course){

        courseRepository.findById(course.id);

        Course updateCategory = this.courseRepository.save(course);

        return ResponseEntity.status(HttpStatus.CREATED).body(updateCategory);
    }
}

