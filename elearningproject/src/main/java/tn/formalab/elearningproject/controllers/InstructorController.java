package tn.formalab.elearningproject.controllers;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.web.bind.annotation.*;
import tn.formalab.elearningproject.model.Category;
import tn.formalab.elearningproject.model.Instructor;
import tn.formalab.elearningproject.model.Student;
import tn.formalab.elearningproject.repositories.InstructorRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping("instructor")
public class InstructorController {

    private InstructorRepository instructorRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public InstructorController(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }


    @PostMapping(path = "register")
    public ResponseEntity<Instructor> addUser(@RequestBody Instructor instructor) {
        instructor.password = this.bCryptPasswordEncoder.encode(instructor.password);
        Instructor savedUser = instructorRepository.save(instructor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PostMapping(path = "login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody Instructor instructor) {

        HashMap<String, Object> response = new HashMap<>();

        Instructor userFromDB = instructorRepository.findByEmail(instructor.email);

        if (userFromDB == null) {
            response.put("message", "user not found !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {

            Boolean compare = this.bCryptPasswordEncoder.matches(instructor.password, userFromDB.password);

            if (!compare) {
                response.put("message", "user not found !");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            } else {
                String token = Jwts.builder()
                        .claim("data", userFromDB)
                        .signWith(SignatureAlgorithm.HS256, "SECRET")
                        .compact();
                response.put("token", token);

                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        }
    }

    @GetMapping(path = "all")
    public ResponseEntity<List<Instructor>> getAllUsers() {
        List<Instructor> instructors = this.instructorRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(instructors);
    }
    @GetMapping(path = "one/{id}")
    public ResponseEntity<Instructor> getInstructorById(@PathVariable Integer id  ){
        try{
            Instructor instructor = instructorRepository.findById(id).get();
            return ResponseEntity.status(HttpStatus.CREATED).body(instructor);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Instructor());

        }
    }
    @PatchMapping ("updateState/{id}")
    public ResponseEntity<HashMap<String, Object>> updateInstructorState(@PathVariable Integer id) {
        HashMap<String, Object> response = new HashMap<>();
        try {
            //check if student exist, else findById returns an exception
            Instructor instructor = instructorRepository.findById(id)
                    .orElseThrow( () -> new IllegalStateException("student with id: " + id + " not found"));

            Boolean state = instructor.getAccountState();
            instructor.setAccountState(!state);

            this.instructorRepository.save(instructor);
            response.put("result", "State updated successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.put("error", e.toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

    }




}

