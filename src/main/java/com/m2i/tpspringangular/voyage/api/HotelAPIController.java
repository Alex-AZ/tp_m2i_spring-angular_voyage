package com.m2i.tpspringangular.voyage.api;

import com.m2i.tpspringangular.voyage.entities.HotelEntity;
import com.m2i.tpspringangular.voyage.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/hotel")
public class HotelAPIController {

    @Autowired
    HotelService hs;

    @GetMapping(path = "", produces = "application/json")
    Iterable<HotelEntity> getAllHotelApi() {
        return hs.getList();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    HotelEntity getHotelByIdApi(@PathVariable(name = "id") String id) {
        return hs.find(Integer.parseInt(id));
    }

    @PostMapping(path = "", produces = "application/json")
    public ResponseEntity<HotelEntity> add(@RequestBody HotelEntity hotel) {
        try {
            HotelEntity createHotel = hs.addhotel(hotel.getNom(), hotel.getEtoiles(),
                    hotel.getAdresse(), hotel.getTelephone(), hotel.getEmail(), hotel.getVille());

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createHotel.getId())
                    .toUri();

            return ResponseEntity.created(uri) // created => HTTP 201
                    .body(createHotel);

        } catch (Exception e) {
            System.out.println("Je suis ici");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<HotelEntity> edit(@RequestBody HotelEntity hotel, @PathVariable("id") int id) {
        try {
            HotelEntity updatedHotel = hs.editHotel(id, hotel.getNom(), hotel.getEtoiles(),
                    hotel.getAdresse(), hotel.getTelephone(), hotel.getEmail(), hotel.getVille());


            return ResponseEntity.ok() // OK => HTTP 200
                    .body(updatedHotel);

        } catch (Exception e) {
            System.out.println("Je suis ici");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        try {
            hs.delete(id);
            return ResponseEntity.ok() // HTTP 200
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
