package it.airpoll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import it.airpoll.model.Country;

/**
 * @author Giacomo
 * 
 * Jpa Country Repository to retrieve Country Entity from DB
 *
 */

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer>, JpaSpecificationExecutor<Country> {

}
