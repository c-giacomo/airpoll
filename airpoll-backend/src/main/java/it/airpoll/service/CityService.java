package it.airpoll.service;

import org.springframework.stereotype.Service;

import it.airpoll.common.AirpollCommonService;
import it.airpoll.dto.CityDto;
import it.airpoll.mapper.CityMapper;
import it.airpoll.model.City;
import it.airpoll.repository.CityRepository;

@Service
public class CityService extends AirpollCommonService<City, CityDto, Integer, CityRepository, CityMapper> {

	public CityService(CityRepository repository, CityMapper mapper) {
		super(repository, mapper);
	}

}
