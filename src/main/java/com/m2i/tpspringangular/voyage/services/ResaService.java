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

    public Iterable<ResaEntity> getList(String search) {
        if (search == null || search.length() == 0) {
            return rr.findAll();
        } else {
            return rr.findByClientNomCompletContains(search);
        }
    }

    public ResaEntity find(int id) {
        return rr.findById(id).get();
    }

    public void checkResa(int numChambre, Date dateDeb) throws Exception {
        if (rr.findByNumChambreAndDatedeb(numChambre, dateDeb).iterator().hasNext())
            throw new Exception("Reservation is already exists");
    }

    public ResaEntity addResa(int client, int hotel, String datedeb, String datefin, int numChambre) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date date = formatter.parse(datedeb);
        checkResa(numChambre, date);

        ResaEntity r = new ResaEntity();
        ClientEntity clientR = new ClientEntity();
        clientR.setId(client);
        r.setClient(clientR);
        HotelEntity hotelR = new HotelEntity();
        hotelR.setId(hotel);
        r.setHotel(hotelR);
        Date dateD = formatter.parse(datedeb);
        r.setDatedeb(dateD);
        Date dateF = formatter.parse(datefin);
        r.setDatefin(dateF);
        r.setNumChambre(numChambre);
        rr.save(r);

        return r;
    }

    public ResaEntity editResa(int idr, int client, int hotel, String datedeb, String datefin, int numChambre) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date dateDebCheck = formatter.parse(datedeb);
        checkResa(numChambre, dateDebCheck);

        ResaEntity r = rr.findById(idr).get();
        ClientEntity clientR = new ClientEntity();
        clientR.setId(client);
        r.setClient(clientR);
        HotelEntity hotelR = new HotelEntity();
        hotelR.setId(hotel);
        r.setHotel(hotelR);
        Date dateD = formatter.parse(datedeb);
        r.setDatedeb(dateD);
        Date dateF = formatter.parse(datefin);
        r.setDatefin(dateF);
        r.setNumChambre(numChambre);
        rr.save(r);

        return r;
    }

    public void delete(int id) {
        rr.deleteById(id);
    }
}
