package it.airpoll.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.airpoll.common.AirpollCommonService;
import it.airpoll.common.exception.ItemNotFoundException;
import it.airpoll.dto.AirpollDto;
import it.airpoll.mapper.AirpollMapper;
import it.airpoll.model.Country;
import it.airpoll.repository.CountryRepository;

@Service
public class AirpollService extends AirpollCommonService<Country, AirpollDto, Integer, CountryRepository, AirpollMapper> {

	public AirpollService(CountryRepository repository, AirpollMapper mapper) {
		super(repository, mapper);
	}
	
	public List<AirpollDto> getData() throws ItemNotFoundException {
		List<Country> entities = repository.findAll();
		if (entities.isEmpty()) throw new ItemNotFoundException("Entities not found!");
		List<AirpollDto> dtos = mapper.toDtoS(entities);
		return dtos;
	}
}
