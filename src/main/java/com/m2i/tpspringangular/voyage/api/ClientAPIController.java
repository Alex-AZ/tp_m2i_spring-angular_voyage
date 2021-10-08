package com.m2i.tpspringangular.voyage.api;

import com.m2i.tpspringangular.voyage.entities.ClientEntity;
import com.m2i.tpspringangular.voyage.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController()
@RequestMapping("/api/client")
public class ClientAPIController {

    @Autowired
    ClientService cs;

    @GetMapping(path = "", produces = "application/json")
    Iterable<ClientEntity> getAllClientApi() {
        return cs.getList();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    ClientEntity getClientByIdApi(@PathVariable(name = "id") String id) {
        return cs.find(Integer.parseInt(id));
    }

    @PostMapping(path = "", produces = "application/json")
    public ResponseEntity<ClientEntity> add(@RequestBody ClientEntity client) {
        try {
            ClientEntity createClient = cs.addClient(client.getNom_complet(), client.getTelephone(),
                    client.getEmail(), client.getAdresse());

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createClient.getId())
                    .toUri();

            return ResponseEntity.created(uri) // created => HTTP 201
                    .body(createClient);

        } catch (Exception e) {
            System.out.println("Je suis ici");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<ClientEntity> edit(@RequestBody ClientEntity client, @PathVariable("id") int id) {
        try {
            ClientEntity updatedClient = cs.editClient(id, client.getNom_complet(), client.getTelephone(),
                    client.getEmail(), client.getAdresse());


            return ResponseEntity.ok() // OK => HTTP 200
                    .body(updatedClient);

        } catch (Exception e) {
            System.out.println("Je suis ici");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        try {
            cs.delete(id);
            return ResponseEntity.ok() // HTTP 200
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
