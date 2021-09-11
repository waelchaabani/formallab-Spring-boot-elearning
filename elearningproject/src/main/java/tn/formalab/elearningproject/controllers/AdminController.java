package tn.formalab.elearningproject.controllers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tn.formalab.elearningproject.model.Admin;
import tn.formalab.elearningproject.model.Instructor;
import tn.formalab.elearningproject.repositories.AdminRepository;
import tn.formalab.elearningproject.repositories.InstructorRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping("admin")
public class AdminController {

    private AdminRepository adminRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public AdminController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }


    @PostMapping(path = "register")
    public ResponseEntity<Admin> addUser(@RequestBody Admin admin) {
        admin.password = this.bCryptPasswordEncoder.encode(admin.password);
        Admin savedUser = adminRepository.save(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PostMapping(path = "login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody Admin admin) {

        HashMap<String, Object> response = new HashMap<>();

        Admin userFromDB = adminRepository.findByEmail(admin.email);

        if (userFromDB == null) {
            response.put("message", "user not found !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {

            Boolean compare = this.bCryptPasswordEncoder.matches(admin.password, userFromDB.password);

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

    @GetMapping(path = "")
    public ResponseEntity<List<Admin>> getAllUsers() {
        List<Admin> categories = this.adminRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }


}


