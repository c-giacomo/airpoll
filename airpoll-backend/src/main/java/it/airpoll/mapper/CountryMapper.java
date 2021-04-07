package it.airpoll.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.airpoll.api.objects.CountryObject;
import it.airpoll.common.AirpollCommonMapper;
import it.airpoll.dto.CountryDto;
import it.airpoll.model.Country;

/**
 * @author Giacomo
 *
 * MapStruct mapper to transform CountryObject to Country model
 * 
 */

@Mapper(componentModel = "spring")
public interface CountryMapper extends AirpollCommonMapper<CountryDto, Country> {

	@Mapping(target = "cities", ignore = true)
	Country toEntity(CountryObject d);

}
