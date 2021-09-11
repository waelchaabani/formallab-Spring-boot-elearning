package tn.formalab.elearningproject.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.formalab.elearningproject.model.Instructor;
import tn.formalab.elearningproject.model.Student;
import tn.formalab.elearningproject.repositories.StudentRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("students")
public class StudentController {
    private final StudentRepository studentRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public static final String DIRECTORY = "src/main/resources/static";

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping(path = "register")
    public ResponseEntity<Student> addUser(@RequestBody Student student) {
        student.password = this.bCryptPasswordEncoder.encode(student.password);
        Student savedUser = studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
    @PostMapping(path = "login")
    public ResponseEntity<Map<String, Object>> loginStudent(@RequestBody Student student) {

        HashMap<String, Object> response = new HashMap<>();

        Student studentFromDB = studentRepository.findByEmail(student.getEmail());

        if (studentFromDB == null) {
            response.put("message", "student not found !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {

            boolean compare = this.bCryptPasswordEncoder.matches(student.getPassword(), studentFromDB.getPassword());

            if (!compare) {
                response.put("message", "student not found !");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            } else {

                if (!studentFromDB.getIsEnabled()) {
                    response.put("message", "student not allowed !");
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
                } else {
                    String token = Jwts.builder()
                            .claim("data", studentFromDB)
                            .signWith(SignatureAlgorithm.HS256, "SECRET")
                            .compact();

                    response.put("token", token);

                    return ResponseEntity.status(HttpStatus.OK).body(response);
                }
            }
        }
    }

    @GetMapping("all")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = this.studentRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(students);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteStudent(@PathVariable Integer id) {
        HashMap<String, Object> response = new HashMap<>();
        try {
            this.studentRepository.deleteById(id);
            response.put("result", "Student deleted");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception e) {
            response.put("error", e.toString());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

    }

    @PutMapping("update")
    public ResponseEntity<HashMap<String, Object>> updateStudent(@RequestBody Student student) {
        HashMap<String, Object> response = new HashMap<>();
        try {
            //check if student exist, else findById returns an exception
            studentRepository.findById(student.getId());
            student.setPassword(this.bCryptPasswordEncoder.encode(student.getPassword()));
            Student studentToUpdate= this.studentRepository.save(student);
            response.put("result", studentToUpdate);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.put("error", e.toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PutMapping("updateState/{id}")
    public ResponseEntity<HashMap<String, Object>> updateInstructorState(@PathVariable Integer id) {
        HashMap<String, Object> response = new HashMap<>();
        try {
            //check if student exist, else findById returns an exception
            Student student = studentRepository.findById(id)
                    .orElseThrow( () -> new IllegalStateException("student with id: " + id + " not found"));

            Boolean state = student.getIsEnabled();
            student.setIsEnabled(!state);

            this.studentRepository.save(student);
            response.put("result", "State updated successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.put("error", e.toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

    }

    @GetMapping("{id}")
    public ResponseEntity<HashMap<String, Object>> findStudentById(@PathVariable Integer id) {
        HashMap<String, Object> response = new HashMap<>();
        try {
            Student student = this.studentRepository.findById(id)
                    .orElseThrow(() -> new IllegalStateException("student with id: " + id + " not found"));
            response.put("result", student);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        }catch (IllegalStateException e) {
            response.put("error", e.toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("{id}/enrollments")
    public ResponseEntity<HashMap<String, Object>> findStudentEnrollments(@PathVariable Integer id) {
        HashMap<String, Object> response = new HashMap<>();
        try {
            Student student = this.studentRepository.findById(id)
                    .orElseThrow(() -> new IllegalStateException("student with id: " + id + " not found"));
            response.put("result", student.getEnrollments());
            return ResponseEntity.status(HttpStatus.OK).body(response);

        }catch (IllegalStateException e) {
            response.put("error", e.toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }



}