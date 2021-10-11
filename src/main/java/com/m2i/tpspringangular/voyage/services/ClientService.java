package com.m2i.tpspringangular.voyage.services;

import com.m2i.tpspringangular.voyage.entities.ClientEntity;
import com.m2i.tpspringangular.voyage.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository cr;

    public ClientService(ClientRepository cr) {
        this.cr = cr;
    }

    public Iterable<ClientEntity> getList(){
        return cr.findAll();
    }

    private void  checkClient( String nom_complet, String telephone , String email, String adresse ) throws Exception {
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
    }

    public ClientEntity addClient(String nomComplet, String telephone, String email , String adresse) throws Exception {
        checkClient(nomComplet, telephone, email , adresse);

        ClientEntity c = new ClientEntity();
        c.setNomComplet(nomComplet);
        c.setTelephone(telephone);
        c.setEmail(email);
        c.setAdresse(adresse);
        cr.save( c );

        return c;
    }

    public ClientEntity editClient(int idc, String nomComplet, String telephone, String email , String adresse) throws Exception {
        checkClient(nomComplet, telephone, email , adresse);

        ClientEntity c = cr.findById(idc).get();
        c.setNomComplet(nomComplet);
        c.setTelephone(telephone);
        c.setEmail(email);
        c.setAdresse(adresse);
        cr.save( c );

        return c;
    }

    public ClientEntity find(int id) {
            return cr.findById(id).get();
    }

    public void delete(int id) {
        cr.deleteById(id);
    }

}
