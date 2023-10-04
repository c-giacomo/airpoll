package it.airpoll.api.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import it.airpoll.api.AirpollCountryAPI;
import it.airpoll.api.objects.CountryObject;

/**
 * @author Giacomo
 * 
 * Implementation of the airpoll.api to retrieve Country, the root of this hierarchical model. The total amount of Countries 
 * available on the OpenAQ DB is 129.
 *
 */

@Component
public class AirpollCountryAPIImpl extends AirpollAbstractObjectAPI<String, CountryObject> implements AirpollCountryAPI {

	@Override
	public Multimap<String, String> buildStandardParams() {
		Multimap<String, String> params = ArrayListMultimap.create();
		params.put("limit", "100");
		return params;
	}
	
	@Override
	public Multimap<String, String> buildStandardParams(List<? extends Object> param) {
		return null;
	}

	@Override
	public Multimap<String, CountryObject> get() {
		return super.get(this.buildStandardParams(), this.buildUrl());
	}

	@Override
	public Multimap<String, CountryObject> get(Set<String> set) {
		return null;
	}

	@Override
	public String buildUrl() {
		return "countries";
	}

	@Override
	public Multimap<String, CountryObject> convert(List<Map<String, Object>> element) {
		Multimap<String, CountryObject> result = ArrayListMultimap.create();
		element.forEach(item -> {
			String code = (String)item.get("code");
			if (code != null && !code.equals("")) {
				CountryObject cObj = new CountryObject();
				cObj.setCode((String)item.get("code"));
				cObj.setName((String)item.get("name"));
				cObj.setCount(Math.round((Double)item.get("count")));
				cObj.setCities((int) Math.round((Double)item.get("cities")));
				cObj.setLocations((int) Math.round((Double)item.get("locations")));
				result.put(cObj.getCode(), cObj);
			}
		});
		return result;
	}

}
