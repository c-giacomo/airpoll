package it.airpoll.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;

import it.airpoll.api.objects.MeasurementObject;
import it.airpoll.common.exception.BreakException;
import it.airpoll.model.Measurements;

/**
 * @author Giacomo
 *
 * MapStruct mapper to transform MeasurementsObject to Measurements model. The JSON response structure is really malformed.. it haven't all air params
 * (no2, pm25, co, so2, pm10, 03) inside single expression, but just one at row. Following an example:
 * {"location":"Park Away", "parameter":"o3", "value":"2.36", "date":"2021-04-23T13"},{....,"parameter":"so2",....}
 * 
 */

@Mapper(componentModel = "spring")
public interface MeasurementMapper {
	
	
	default List<MeasurementObject> groupByDate(Date date, Collection<MeasurementObject> list) {
		return list.stream().filter(item -> item.getDate().equals(date)).collect(Collectors.toList());
	}
	
	default List<Measurements> toEntity(Collection<MeasurementObject> obj) {
		List<MeasurementObject> list = obj.stream().sorted(Comparator.comparing(MeasurementObject::getDate))
				.collect(Collectors.toCollection(ArrayList::new));
		
		List<Measurements> result = new ArrayList<Measurements>();
		
		try {
			obj.forEach(item -> {
				MeasurementObject o = list.stream().findFirst().orElseThrow(BreakException::new);
				List<MeasurementObject> measures = groupByDate(o.getDate(), list);
				Measurements measure = new Measurements();
				
				measures.forEach(m -> {
					switch(m.getParameter()) {
						case "no2": measure.setNo2(item.getValue());
						break;
						case "pm25": measure.setPm25(item.getValue());
						break;
						case "co": measure.setCo(item.getValue());
						break;
						case "so2": measure.setSo2(item.getValue());
						break;
						case "pm10": measure.setPm10(item.getValue());
						break;
						case "o3": measure.setO3(item.getValue());
						break;
						default:
					}
					measure.setLocal(m.getDate());
				});
				
				result.add(measure);
				list.removeAll(measures);
			});
		} catch (BreakException be) {}
		
		return result;
	}

}
