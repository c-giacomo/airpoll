package it.airpoll.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import it.airpoll.model.Country;

public class CountrySpecification {
	
	public static Specification<Country> findByCountry(Integer countryId) {
		if (countryId == null)
			return (root, query, cb) -> cb.conjunction();
		else return (root, query, cb) -> cb.equal(root.get("id"), countryId);
	}
	
	public static Specification<Country> findByCity(Integer cityId) {
		if (cityId == null)
			return (root, query, cb) -> cb.conjunction();
		else return (root, query, cb) -> cb.equal(root.join("cities").get("id"), cityId);
	}

}
