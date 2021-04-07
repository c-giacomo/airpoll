package it.airpoll.api.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import it.airpoll.api.AirpollMeasurementAPI;
import it.airpoll.api.objects.MeasurementObject;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Giacomo
 * 
 * Implementation of the airpoll.api to retrieve Measurements as a function of Country. The default limit of OpenAQ is 100 results
 * here the limit has been increased to 10000 (maximum 100000) to achieve more results.
 *
 */

@Component
@Slf4j
public class AirpollMeasurementsAPIImpl extends AirpollAbstractObjectAPI<String, MeasurementObject> implements AirpollMeasurementAPI {

	@Override
	public Multimap<String, String> buildStandardParams() {
		Multimap<String, String> param = ArrayListMultimap.create();
		return param;
	}
	
	@Override
	public Multimap<String, String> buildStandardParams(List<? extends Object> param) {
		Multimap<String, String> params = ArrayListMultimap.create();
		param.forEach(item -> params.put("country", (String)item));
		params.put("limit", "10000");
		return params;
	}

	@Override
	public String buildUrl() {
		return "measurements";
	}

	@Override
	public Multimap<String, MeasurementObject> get() {
		return null;
	}

	@Override
	public Multimap<String, MeasurementObject> get(Set<String> set) {
		List<String> param = set.stream().collect(Collectors.toList());
		return super.get(this.buildStandardParams(param), this.buildUrl());
	}

	@Override
	@SuppressWarnings("unchecked")
	public Multimap<String, MeasurementObject> convert(List<Map<String, Object>> element) {
		Multimap<String, MeasurementObject> result = ArrayListMultimap.create();
		element.forEach(item -> {
			MeasurementObject mObj = new MeasurementObject();
			mObj.setLocation((String)item.get("location"));
			mObj.setParameter((String)item.get("parameter"));
			mObj.setValue((Double)item.get("value"));
			
			Map<String, String> dates = (Map<String, String>) item.get("date"); 
			mObj.setDate(getDate((String)dates.get("local")));
			mObj.setUnit((String)item.get("unit"));
			mObj.setCity((String)item.get("city"));
			mObj.setCountry((String)item.get("country"));
			
			result.put(mObj.getLocation(), mObj);
		});
		return result;
	}
	
	private Date getDate(String dates) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+'mm:ss");
		SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'-'mm:ss");
		try {
			return formatter.parse(dates);
		} catch (ParseException e) {
			try {
				return formatter2.parse(dates);
			} catch (ParseException pe) {
				try {
					return formatter3.parse(dates);
				} catch (ParseException ppe) {
					log.error(ppe.getLocalizedMessage());
				}
			}
		}
		return null;
	}

}
