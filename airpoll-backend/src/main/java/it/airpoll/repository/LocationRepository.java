package it.airpoll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.airpoll.model.Location;

/**
 * @author Giacomo
 * 
 * Jpa Location Repository to retrieve Location Entity from DB
 *
 */

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

}
