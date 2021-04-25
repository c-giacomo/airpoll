package it.airpoll.api.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import it.airpoll.api.AirpollCityAPI;
import it.airpoll.api.objects.CityObject;

/**
 * @author Giacomo
 * 
 * Implementation of the airpoll.api to retrieve City as a function of Country. The default limit of OpenAQ is 100 results
 * here the limit has been increased to 50000. Higher is the result limit more city will be found (maximum 100000)
 *
 */

@Component
public class AirpollCityAPIImpl extends AirpollAbstractObjectAPI<String, CityObject> implements AirpollCityAPI {

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
		return "cities";
	}

	@Override
	public Multimap<String, CityObject> get() {
		return super.get(this.buildStandardParams(), this.buildUrl());
	}

	@Override
	public Multimap<String, CityObject> get(Set<String> set) {
		List<String> param = set.stream().collect(Collectors.toList());
		return super.get(this.buildStandardParams(param), this.buildUrl());
	}

	@Override
	public Multimap<String, CityObject> convert(List<Map<String, Object>> element) {
		Multimap<String, CityObject> result = ArrayListMultimap.create();
		element.forEach(item -> {
			CityObject cObj = new CityObject();
			cObj.setName((String)item.get("name"));
			cObj.setCountry((String)item.get("country"));
			cObj.setCount(Math.round((Double)item.get("count")));
			cObj.setLocations((int) Math.round((Double)item.get("locations")));
			result.put(cObj.getCountry(), cObj);
		});
		return result;
	}

	

}
