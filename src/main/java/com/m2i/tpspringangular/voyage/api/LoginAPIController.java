package com.m2i.tpspringangular.voyage.api;

import com.m2i.tpspringangular.voyage.entities.AdminEntity;
import com.m2i.tpspringangular.voyage.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController()
@RequestMapping("/api/login")
public class LoginAPIController {

    @Autowired
    AdminRepository adminRepository;

    @PostMapping(path = "", produces = "application/json")
    ResponseEntity<AdminEntity> checkLogin(@RequestBody AdminEntity adminEntity) {
        System.out.println(adminEntity.getUsername());

        try {
            AdminEntity admin = adminRepository.findByUsername(adminEntity.getUsername());
            admin.setPassword("");
            return ResponseEntity.ok()
                    .body(admin);
        } catch (Exception e) {
            System.out.println("Je suis ici !");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
