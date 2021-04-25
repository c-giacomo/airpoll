package it.airpoll.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import it.airpoll.model.City;

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
