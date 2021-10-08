package com.m2i.tpspringangular.voyage.services;

import com.m2i.tpspringangular.voyage.entities.ClientEntity;
import com.m2i.tpspringangular.voyage.entities.HotelEntity;
import com.m2i.tpspringangular.voyage.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hr;

    public HotelService(HotelRepository hr) {
        this.hr = hr;
    }

    public Iterable<HotelEntity> getList(){
        return hr.findAll();
    }

    public HotelEntity find(int id) {
        return hr.findById(id).get();
    }

    public HotelEntity addhotel(String nom, int etoiles, String adresse, String telephone, String email, String ville) throws Exception {

        HotelEntity h = new HotelEntity();
        h.setNom(nom);
        h.setEtoiles(etoiles);
        h.setAdresse(adresse);
        h.setTelephone(telephone);
        h.setEmail(email);
        h.setVille(ville);
        hr.save( h );

        return h;
    }

    public HotelEntity editHotel(int idh, String nom, int etoiles, String adresse, String telephone, String email, String ville) throws Exception {

        HotelEntity h = hr.findById(idh).get();
        h.setNom(nom);
        h.setEtoiles(etoiles);
        h.setAdresse(adresse);
        h.setTelephone(telephone);
        h.setEmail(email);
        h.setVille(ville);
        hr.save( h );

        return h;
    }

    public void delete(int id) {
        hr.deleteById(id);
    }
}
