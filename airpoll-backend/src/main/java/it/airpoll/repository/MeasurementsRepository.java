package it.airpoll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.airpoll.model.Measurements;

/**
 * @author Giacomo
 * 
 * Jpa Measurements Repository to retrieve Measurements Entity from DB
 *
 */

@Repository
public interface MeasurementsRepository extends JpaRepository<Measurements, Integer> {

}
