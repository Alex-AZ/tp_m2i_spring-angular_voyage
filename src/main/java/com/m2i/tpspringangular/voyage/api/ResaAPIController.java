package com.m2i.tpspringangular.voyage.api;

import com.m2i.tpspringangular.voyage.entities.ClientEntity;
import com.m2i.tpspringangular.voyage.entities.HotelEntity;
import com.m2i.tpspringangular.voyage.entities.ResaEntity;
import com.m2i.tpspringangular.voyage.services.ClientService;
import com.m2i.tpspringangular.voyage.services.ResaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/api/resa")
public class ResaAPIController {

    @Autowired
    ResaService rs;

    @GetMapping(path = "", produces = "application/json")
    Iterable<ResaEntity> getAllResaApi(HttpServletRequest request) {
        return rs.getList(request.getParameter("search"));
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<ResaEntity> getResaByIdApi(@PathVariable(name = "id") int id) {
        try {
            ResaEntity resa = rs.find(id);
            return ResponseEntity.ok()
                    .body(resa);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "", produces = "application/json")
    public ResponseEntity<ResaEntity> addResaApi(@RequestBody ResaEntity resa) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String dateFormaStrD = format.format(resa.getDatedeb());
        String dateFormaStrF = format.format((resa.getDatefin()));

        try {
            ResaEntity createResa = rs.addResa(resa.getClient().getId(), resa.getHotel().getId(), dateFormaStrD,
                    dateFormaStrF, resa.getNumChambre());

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id")
                    .buildAndExpand(createResa.getId())
                    .toUri();

            return ResponseEntity.created(uri) // created => HTTP 201
                    .body(createResa);
        } catch (Exception e) {
            System.out.println("Je suis ici");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping(path="/{id}", produces = "application/json")
    public ResponseEntity<ResaEntity> updateResaApi(@RequestBody ResaEntity resa, @PathVariable(name = "id") int id) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String dateFormaStrD = format.format(resa.getDatedeb());
        String dateFormaStrF = format.format((resa.getDatefin()));

        try{
            ResaEntity updateResa = rs.editResa(id, resa.getClient().getId(), resa.getHotel().getId(), dateFormaStrD,
                    dateFormaStrF, resa.getNumChambre());


            return ResponseEntity.ok() // OK => HTTP 200
                    .body(updateResa);

        }catch ( Exception e ){
            System.out.println("Je suis ici");
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST , e.getMessage()  );
        }
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id ){
        try {
            rs.delete(id);
            return ResponseEntity.ok() // HTTP 200
                    .body(null);
        }catch( Exception e ){
            return ResponseEntity.notFound().build();
        }
    }
}
