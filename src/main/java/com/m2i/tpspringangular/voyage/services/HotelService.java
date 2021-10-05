package com.m2i.tpspringangular.voyage.services;

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

    /*private void  checkClient( String nom_complet, String telephone , String email, String adresse ) throws Exception {
        if( nom_complet.length() < 2 ){
            throw new Exception("Invalid value pour nom_complet");
        }

        if( telephone.length() < 2 ){
            throw new Exception("Invalid value pour telephone");
        }

        if( email.length() < 2 ){
            throw new Exception("Invalid value pour email");
        }

        if( adresse.length() < 2 ){
            throw new Exception("Invalid value pour adresse");
        }
    }*/

    public HotelEntity addhotel(String nom, int etoiles, String adresse, String telephone, String email, String ville) throws Exception {
//        checkClient(nom_complet, telephone, email , adresse);

        HotelEntity h = new HotelEntity();
        h.setNom(nom);
        h.setEtoiles(etoiles);
        h.setTelephone(telephone);
        h.setEmail(email);
        h.setVille(ville);
        hr.save( h );

        return h;
    }

    public HotelEntity editHotel(int idh, String nom, int etoiles, String adresse, String telephone, String email, String ville) throws Exception {
//        checkClient(nom_complet, telephone, email , adresse);

        HotelEntity h = new HotelEntity();
        h.setNom(nom);
        h.setEtoiles(etoiles);
        h.setTelephone(telephone);
        h.setEmail(email);
        h.setVille(ville);
        hr.save( h );

        return h;
    }

    /*public ClientEntity find(int id) {
        return cr.findById(id).get();
    }

    public ClientEntity findByEmail(String email) {
        try {
            return cr.findByEmail( email ).get();
        }catch( Exception e ) {
            return null;
        }
    }*/

    public void delete(int id) {
        hr.deleteById(id);
    }
}
