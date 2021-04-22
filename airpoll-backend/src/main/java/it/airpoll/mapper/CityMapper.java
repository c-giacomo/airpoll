package it.airpoll.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.airpoll.api.objects.CityObject;
import it.airpoll.common.AirpollCommonMapper;
import it.airpoll.dto.CityDto;
import it.airpoll.model.City;

/**
 * @author Giacomo
 *
 * MapStruct mapper to transform CityObject to City model
 * 
 */

@Mapper(componentModel = "spring")
public interface CityMapper extends AirpollCommonMapper<CityDto, City> {
	
	@Mapping(target = "country", ignore = true)
	@Mapping(target = "location", ignore = true)
	City objToModel(CityObject object);

	@Override
	@Mapping(target = "id_country", source = "country.id")
	CityDto toDto(City e);

}
