package it.airpoll.mapper;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;

import it.airpoll.common.AirpollCommonMapper;
import it.airpoll.dto.AirpollDto;
import it.airpoll.model.City;
import it.airpoll.model.Country;

/**
 * @author Giacomo
 *
 * MapStruct mapper to generate DTO with main information required by spec. Will be used only in default mode
 * 
 */

@Mapper(componentModel = "spring")
public interface AirpollMapper extends AirpollCommonMapper<AirpollDto, Country> {

	default List<AirpollDto> toDtoS(List<Country> country) {
		List<AirpollDto> result = country.stream()
			.map(x -> x.getCities())
			.flatMap(List::stream)
			.map(i -> i.getLocation())
			.flatMap(List::stream)
			.map(y -> y.getMeasurements())
			.flatMap(List::stream)
			.map(obj -> {
				AirpollDto dto = new AirpollDto();
				dto.setLocation(obj.getLocation().getIdentifier());
				dto.setCity(obj.getLocation().getCity().getCity());
				dto.setCountry(obj.getLocation().getCity().getCountry().getName());
				dto.setPm25(obj.getPm25());
				dto.setNo2(obj.getNo2());
				dto.setCo(obj.getCo());
				dto.setPm10(obj.getPm10());
				dto.setO3(obj.getO3());
				dto.setLatitude(obj.getLocation().getLatitude());
				dto.setLongitude(obj.getLocation().getLongitude());
				dto.setLocal(obj.getLocal());
				return dto;
			}).collect(Collectors.toList());
		return result;
	}
	
	default List<AirpollDto> toDTOS(List<City> city) {
		List<AirpollDto> result = city.stream()
			.map(i -> i.getLocation())
			.flatMap(List::stream)
			.map(y -> y.getMeasurements())
			.flatMap(List::stream)
			.map(obj -> {
				AirpollDto dto = new AirpollDto();
				dto.setLocation(obj.getLocation().getIdentifier());
				dto.setCity(obj.getLocation().getCity().getCity());
				dto.setCountry(obj.getLocation().getCity().getCountry().getName());
				dto.setPm25(obj.getPm25());
				dto.setNo2(obj.getNo2());
				dto.setCo(obj.getCo());
				dto.setPm10(obj.getPm10());
				dto.setO3(obj.getO3());
				dto.setLatitude(obj.getLocation().getLatitude());
				dto.setLongitude(obj.getLocation().getLongitude());
				dto.setLocal(obj.getLocal());
				return dto;
			}).collect(Collectors.toList());
		return result;
	}
	
	default LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}

}
