package com.m2i.tpspringangular.voyage.services;

import com.m2i.tpspringangular.voyage.entities.ClientEntity;
import com.m2i.tpspringangular.voyage.entities.HotelEntity;
import com.m2i.tpspringangular.voyage.entities.ResaEntity;
import com.m2i.tpspringangular.voyage.repositories.ResaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ResaService {

    private ResaRepository rr;

    public ResaService(ResaRepository rr) {
        this.rr = rr;
    }

    public Iterable<ResaEntity> getList(){
        return rr.findAll();
    }

    public ResaEntity find(int id) {
        return rr.findById( id ).get();
    }

    public ResaEntity addResa(int client, int hotel, Date datedeb, Date datefin, int num_chambre ) throws Exception {
        ResaEntity r = new ResaEntity();
        ClientEntity clientR = new ClientEntity();
        clientR.setId(client);
        r.setClient(clientR);
        HotelEntity hotelR = new HotelEntity();
        hotelR.setId(hotel);
        r.setHotel(hotelR);
        r.setDatedeb(datedeb);
        r.setDatefin(datefin);
        r.setNum_chambre(num_chambre);

        rr.save(r);

        return r;
    }

    public ResaEntity editResa(int idr, int client, int hotel, Date datedeb, Date datefin, int num_chambre ) throws Exception {
        ResaEntity r = rr.findById(idr).get();
        ClientEntity clientR = new ClientEntity();
        clientR.setId(client);
        r.setClient(clientR);
        HotelEntity hotelR = new HotelEntity();
        hotelR.setId(hotel);
        r.setHotel(hotelR);
        r.setDatedeb(datedeb);
        r.setDatefin(datefin);
        r.setNum_chambre(num_chambre);

        rr.save(r);

        return r;
    }

    /*public ResaEntity findByEmail(String email) {
        try {
            return pr.findByEmail( email ).get();
        }catch( Exception e ) {
            return null;
        }

    }*/

    public void delete(int id) {
        rr.deleteById(id);
    }
}
