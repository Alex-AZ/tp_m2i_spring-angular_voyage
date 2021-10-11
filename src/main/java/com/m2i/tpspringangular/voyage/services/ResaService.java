package com.m2i.tpspringangular.voyage.services;

import com.m2i.tpspringangular.voyage.entities.ClientEntity;
import com.m2i.tpspringangular.voyage.entities.HotelEntity;
import com.m2i.tpspringangular.voyage.entities.ResaEntity;
import com.m2i.tpspringangular.voyage.repositories.ResaRepository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Service
public class ResaService {

    private ResaRepository rr;

    public ResaService(ResaRepository rr) {
        this.rr = rr;
    }

    public Iterable<ResaEntity> getList(String search){
        if (search == null || search.length() == 0) {
            return rr.findAll();
        } else {
            return rr.findByClientNomCompletContains(search);
        }
    }

    public ResaEntity find(int id) {
        return rr.findById( id ).get();
    }

    public Iterable<ResaEntity> chekResa(int numChambre, Date dateDeb) throws Exception {
        if (rr.findByClientNomCompletContains(numChambre, dateDeb).length() == 0) {
            return rr.save();
        } else {
            throw new Exception("Reservation is already exists");
        }
    }

    public ResaEntity addResa(int client, int hotel, String datedeb, String datefin, int numChambre ) throws Exception {
        ResaEntity r = new ResaEntity();
        ClientEntity clientR = new ClientEntity();
        clientR.setId(client);
        r.setClient(clientR);
        HotelEntity hotelR = new HotelEntity();
        hotelR.setId(hotel);
        r.setHotel(hotelR);

        System.out.println("La date de début est : " + datedeb);
        SimpleDateFormat formatterD = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        Date dateD = formatterD.parse(datedeb);
        r.setDatedeb(dateD);

        System.out.println("La date de fin est : " + datefin);
        SimpleDateFormat formatterF = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        Date dateF = formatterF.parse(datefin);
        r.setDatefin(dateF);

        r.setNumChambre(numChambre);

        rr.save(r);

        return r;
    }

    public ResaEntity editResa(int idr, int client, int hotel, String datedeb, String datefin, int numChambre ) throws Exception {
        ResaEntity r = rr.findById(idr).get();
        ClientEntity clientR = new ClientEntity();
        clientR.setId(client);
        r.setClient(clientR);
        HotelEntity hotelR = new HotelEntity();
        hotelR.setId(hotel);
        r.setHotel(hotelR);

        SimpleDateFormat formatterD = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        Date dateD = formatterD.parse(datedeb);
        r.setDatedeb(dateD);

        SimpleDateFormat formatterF = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        Date dateF = formatterF.parse(datefin);
        r.setDatefin(dateF);

        r.setNumChambre(numChambre);

        rr.save(r);

        return r;
    }

    public void delete(int id) {
        rr.deleteById(id);
    }
}
