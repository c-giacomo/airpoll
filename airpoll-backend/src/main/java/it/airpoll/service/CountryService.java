package it.airpoll.service;

import org.springframework.stereotype.Service;

import it.airpoll.common.AirpollCommonService;
import it.airpoll.dto.CountryDto;
import it.airpoll.mapper.CountryMapper;
import it.airpoll.model.Country;
import it.airpoll.repository.CountryRepository;

/**
 * @author Giacomo
 * 
 * Country controller used by common mode to retrieve Country entity
 *
 */

@Service
public class CountryService extends AirpollCommonService<Country, CountryDto, Integer, CountryRepository, CountryMapper> {

	public CountryService(CountryRepository repository, CountryMapper mapper) {
		super(repository, mapper);
	}

}
