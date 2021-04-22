package it.airpoll.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import it.airpoll.common.AirpollCommonService;
import it.airpoll.common.exception.ItemNotFoundException;
import it.airpoll.dto.AirpollDto;
import it.airpoll.mapper.AirpollMapper;
import it.airpoll.model.Country;
import it.airpoll.repository.CountryRepository;
import it.airpoll.repository.specification.CountrySpecification;

@Service
public class AirpollService extends AirpollCommonService<Country, AirpollDto, Integer, CountryRepository, AirpollMapper> {

	public AirpollService(CountryRepository repository, AirpollMapper mapper) {
		super(repository, mapper);
	}
	
	public List<AirpollDto> getData(Integer countryId, Integer cityId, Integer page) throws ItemNotFoundException {
		Specification<Country> spec = Specification.where(CountrySpecification.findByCountry(countryId)
											.and(CountrySpecification.findByCity(cityId)));
		
		Page<Country> entities = repository.findAll(spec, PageRequest.of(0, page));
		if (entities.isEmpty()) throw new ItemNotFoundException("Entities not found!");
		return mapper.toDtoS(entities.toList());
	}
}
