package it.airpoll.service;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.airpoll.common.AirpollCommonService;
import it.airpoll.dto.AirpollDto;
import it.airpoll.enumerator.Paging;
import it.airpoll.mapper.AirpollMapper;
import it.airpoll.model.Country;
import it.airpoll.repository.CountryRepository;

/**
 * @author Giacomo
 * 
 * AirpollProjection service. It's loaded in projection mode and call the JPQL Query to retrieve directly AirpollDto filtered by non null value 
 * and paged by the value retrieved from front-end scrolling
 *
 */

@Profile("projection")
@Service
public class AirpollProjectionService extends AirpollCommonService<Country, AirpollDto, Integer, CountryRepository, AirpollMapper> {

	public AirpollProjectionService(CountryRepository repository, AirpollMapper mapper) {
		super(repository, mapper);
	}
	
	public List<AirpollDto> get(Integer countryID, Integer cityID, Integer page) {
		Pageable pageable = PageRequest.of(page, Paging.RESULT_FOR_PAGE.value());
		return repository.readAll(countryID, cityID, pageable);
	}

}
