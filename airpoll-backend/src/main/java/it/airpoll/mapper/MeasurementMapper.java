package it.airpoll.mapper;

import java.util.Collection;

import org.mapstruct.Mapper;

import it.airpoll.api.objects.MeasurementObject;
import it.airpoll.model.Measurements;

/**
 * @author Giacomo
 *
 * MapStruct mapper to transform MeasurementsObject to Measurements model
 * 
 */

@Mapper(componentModel = "spring")
public interface MeasurementMapper {
	
	default Measurements toEntity(Collection<MeasurementObject> obj) {
		Measurements measure = new Measurements();
		obj.forEach(item -> {
			switch(item.getParameter()) {
			case "no2": measure.setNo2(item.getValue());
			break;
			case "pm25": measure.setPm25(item.getValue());
			break;
			case "co": measure.setCo(item.getValue());
			break;
			case "pm10": measure.setPm10(item.getValue());
			case "o3": measure.setO3(item.getValue());
			break;
			default:
			}
		});
		MeasurementObject object = obj.iterator().next();
		measure.setLocal(object.getDate());
		return measure;
	}

}
