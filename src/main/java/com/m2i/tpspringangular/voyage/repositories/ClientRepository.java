package com.m2i.tpspringangular.voyage.repositories;

import com.m2i.tpspringangular.voyage.entities.ClientEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClientRepository extends CrudRepository<ClientEntity, Integer> {
}
