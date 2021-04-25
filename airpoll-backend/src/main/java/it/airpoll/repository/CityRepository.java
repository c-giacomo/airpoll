package it.airpoll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import it.airpoll.model.City;

/**
 * @author Giacomo
 * 
 * Jpa City Repository to retrieve City Entity from DB
 *
 */

@Repository
public interface CityRepository extends JpaRepository<City, Integer>, JpaSpecificationExecutor<City>{

}
