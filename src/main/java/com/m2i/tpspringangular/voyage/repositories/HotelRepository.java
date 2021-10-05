package com.m2i.tpspringangular.voyage.repositories;

import com.m2i.tpspringangular.voyage.entities.HotelEntity;
import org.springframework.data.repository.CrudRepository;

public interface HotelRepository extends CrudRepository<HotelEntity, Integer> {
}
