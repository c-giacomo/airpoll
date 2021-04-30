package it.airpoll.repository.specification;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.domain.Specification;

import it.airpoll.model.City;

/**
 * @author Giacomo
 * 
 * CitySpecification to retrieve City Entity dinamically by parameters. It loaded in default version
 *
 */

@Profile("default")
public class CitySpecification {
	
	public static Specification<City> findByCountry(Integer countryId) {
		if (countryId == null)
			return (root, query, cb) -> cb.conjunction();
		else return (root, query, cb) -> cb.equal(root.get("country").get("id"), countryId);
	}
	
	public static Specification<City> findByCity(Integer cityId) {
		if (cityId == null)
			return (root, query, cb) -> cb.conjunction();
		else return (root, query, cb) -> cb.equal(root.get("id"), cityId);
	}

}
