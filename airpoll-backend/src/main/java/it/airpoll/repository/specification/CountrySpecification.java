package it.airpoll.repository.specification;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.domain.Specification;

import it.airpoll.model.Country;

/**
 * @author Giacomo
 * 
 * CountrySpecification to retrieve Country Entity dinamically by parameters. It loaded in default version
 *
 */

@Profile("default")
public class CountrySpecification {
	
	public static Specification<Country> findByCountry(Integer countryId) {
		if (countryId == null)
			return (root, query, cb) -> cb.conjunction();
		else return (root, query, cb) -> cb.equal(root.get("id"), countryId);
	}

}
