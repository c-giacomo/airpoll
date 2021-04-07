package it.airpoll.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.airpoll.api.objects.LocationObject;
import it.airpoll.model.Location;

/**
 * @author Giacomo
 *
 * MapStruct mapper to transform LocationyObject to Location model
 * 
 */

@Mapper(componentModel = "spring")
public interface LocationMapper {
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "identifier", source = "location")
	@Mapping(target = "city", ignore = true)
	Location toEntity(LocationObject obj);

}
