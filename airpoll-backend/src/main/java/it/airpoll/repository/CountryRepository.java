package it.airpoll.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.airpoll.dto.AirpollDto;
import it.airpoll.model.Country;

/**
 * @author Giacomo
 * 
 * Jpa Country Repository to retrieve Country Entity from DB. The readAll() method has been added to work with Projection mode, to retrieve immediately
 * the object required by the frontend. In the default mode the same result is achieved by iterate and flat the Country -> City -> Location -> Measurements
 * by Lambdas.
 *
 */

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer>, JpaSpecificationExecutor<Country> {
	
	@Query("select new it.airpoll.dto.AirpollDto(l.identifier, c.name, co.name, m.pm25, m.no2, m.co, m.pm10, m.o3, l.latitude, l.longitude, m.local)"
			+ "	from"
			+ "	City c, Country co, Location l, Measurements m"
			+ "	where"
			+ "	co.id = c.country.id AND"
			+ "	c.id = l.city.id AND"
			+ "	l.id = m.location.id AND"
			+ " (:countryID is null OR co.id = :countryID) AND"
			+ " (:cityID is null OR c.id = :cityID)")
	public List<AirpollDto> readAll(@Param("countryID") Integer countryID, @Param("cityID") Integer cityID, Pageable pageable);

}
