package it.airpoll.api.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import it.airpoll.api.AirpollLocationAPI;
import it.airpoll.api.objects.LocationObject;

/**
 * @author Giacomo
 * 
 * Implementation of the airpoll.api to retrieve Location as a function of Country. The default limit of OpenAQ is 100 results
 * here the limit has been increased to 50000 (maximum 100000) to achieve more results.
 *
 */

@Component
@SuppressWarnings("unchecked")
public class AirpollLocationAPIImpl extends AirpollAbstractObjectAPI<String, LocationObject> implements AirpollLocationAPI {

	@Override
	public Multimap<String, String> buildStandardParams() {
		Multimap<String, String> params = ArrayListMultimap.create();
		return params;
	}
	
	@Override
	public Multimap<String, String> buildStandardParams(List<? extends Object> param) {
		Multimap<String, String> params = ArrayListMultimap.create();
		param.forEach(item -> params.put("country", (String)item));
		params.put("limit", "50000");
		return params;
	}

	@Override
	public String buildUrl() {
		return "locations";
	}

	@Override
	public Multimap<String, LocationObject> get() {
		return super.get(this.buildStandardParams(), this.buildUrl());
	}

	@Override
	public Multimap<String, LocationObject> get(Set<String> set) {
		List<String> param = set.stream().collect(Collectors.toList());
		return super.get(this.buildStandardParams(param), this.buildUrl());
	}

	@Override
	public Multimap<String, LocationObject> convert(List<Map<String, Object>> element) {
		Multimap<String, LocationObject> result = ArrayListMultimap.create();
		element.forEach(item -> {
			LocationObject lObj = new LocationObject();
			lObj.setId((int)Math.round((Double)item.get("id")));
			lObj.setCountry((String)item.get("country"));
			lObj.setCity((String)item.get("city"));
			lObj.setLocation((String)item.get("location"));
			
			Map<String, Double> coord = (Map<String, Double>) item.get("coordinates");
			lObj.setLatitude(roundFloor((Double)coord.get("latitude"), 6));
			lObj.setLongitude(roundFloor((Double)coord.get("longitude"), 6));
			
			result.put(lObj.getCity(), lObj);
		});
		return result;
	}

}
