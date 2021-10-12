package com.m2i.tpspringangular.voyage.repositories;

import com.m2i.tpspringangular.voyage.entities.ResaEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface ResaRepository extends CrudRepository<ResaEntity, Integer> {

    Iterable<ResaEntity> findByClientNomCompletContains(String search);

    Iterable<ResaEntity> findByNumChambreAndDatedeb(int numChambre, Date dateDeb);

}
