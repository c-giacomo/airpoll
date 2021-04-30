package it.airpoll.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.airpoll.common.AirpollCommonService;
import it.airpoll.common.exception.ItemNotFoundException;
import it.airpoll.dto.AirpollDto;
import it.airpoll.mapper.AirpollMapper;
import it.airpoll.model.City;
import it.airpoll.model.Country;
import it.airpoll.repository.CityRepository;
import it.airpoll.repository.CountryRepository;
import it.airpoll.repository.specification.CitySpecification;
import it.airpoll.repository.specification.CountrySpecification;

/**
 * @author Giacomo
 * 
 * Airpoll Service loaded in default mode. It has two overloaded method who retrieve whole data, by country, by city, by both two params. The
 * Country should be mapped by properly mapper to iterate and flat the data.
 * 
 */

@Profile("default")
@Service
public class AirpollService extends AirpollCommonService<Country, AirpollDto, Integer, CountryRepository, AirpollMapper> {
	
	@Autowired protected CityRepository cityRepository;

	public AirpollService(CountryRepository repository, AirpollMapper mapper) {
		super(repository, mapper);
	}
	
	@Transactional(readOnly = true)
	public List<AirpollDto> get(Integer countryId, Integer page) throws ItemNotFoundException {
		Specification<Country> spec = Specification.where(CountrySpecification.findByCountry(countryId));
		
		Page<Country> entities = repository.findAll(spec, PageRequest.of(0, page));
		if (entities.isEmpty()) throw new ItemNotFoundException("Entities not found!");
		return mapper.toDtoS(entities.toList());
	}
	
	@Transactional(readOnly = true)
	public List<AirpollDto> get(Integer countryId, Integer cityId, Integer page) throws ItemNotFoundException {
		Specification<City> spec = Specification.where(CitySpecification.findByCountry(countryId)
				.and(CitySpecification.findByCity(cityId)));
		
		Page<City> entities = cityRepository.findAll(spec, PageRequest.of(0, page));
		if (entities.isEmpty()) throw new ItemNotFoundException("Entities not found!");
		return mapper.toDTOS(entities.toList());
	}
}
