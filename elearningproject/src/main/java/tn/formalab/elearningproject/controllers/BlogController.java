package tn.formalab.elearningproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.formalab.elearningproject.model.Blog;
import tn.formalab.elearningproject.model.Course;
import tn.formalab.elearningproject.repositories.BlogRepository;
import tn.formalab.elearningproject.repositories.CourseRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping("blog")

public class BlogController {
    private BlogRepository blogRepository;


    @Autowired
    public BlogController(BlogRepository blogRepository){
        this.blogRepository = blogRepository;
    }
    @PostMapping(path = "add")
    public ResponseEntity<Blog> addProduct(@RequestBody Blog blog){


        Blog savedCourse = this.blogRepository.save(blog);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
    }
    @GetMapping(path = "all")
    public ResponseEntity <List<Blog>> getAllCourse(){


        List<Blog> blog = this.blogRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(blog);
    }
    @GetMapping(path = "one/{id}")
    public ResponseEntity<Blog> getProductById(@PathVariable Integer id  ) {
        try {
            Blog blog = blogRepository.findById(id).get();
            return ResponseEntity.status(HttpStatus.CREATED).body(blog);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Blog());

        }
    }
    @DeleteMapping(path = "delete/{id}")
    public ResponseEntity <Map<String,String>> deleteProduct(@PathVariable Integer id){


        this.blogRepository.deleteById(id);
        HashMap<String,String> map=new HashMap<>();
        map.put("m","blog deleted");
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }
    @PatchMapping (path = "update")
    public ResponseEntity<Blog> updateCategory(@RequestBody Blog blog){

        blogRepository.findById(blog.id);

        Blog updateCategory = this.blogRepository.save(blog);

        return ResponseEntity.status(HttpStatus.CREATED).body(updateCategory);
    }
}


