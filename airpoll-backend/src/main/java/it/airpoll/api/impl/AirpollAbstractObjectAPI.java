package it.airpoll.api.impl;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import it.airpoll.api.AirpollAbstractAPI;

/**
 * @author Giacomo
 * 
 * Parametric implementation of AirpollAbstractAPI.
 *
 * @param <V> HashMap key - based on the implementation
 * @param <T> HashMap value - based on implementation
 * 
 */


public abstract class AirpollAbstractObjectAPI<V, T> extends AirpollAbstractAPI {
	
	@SuppressWarnings("unchecked")
	public Multimap<V, T> get(Multimap<String, String> params, String url) {
		Multimap<V, T> result = ArrayListMultimap.create();
        Map<String, Object> apiCallResult = super.callApi(params, url);
        if (apiCallResult.get("results") != null) {
        	List<Map<String, Object>> list = (List<Map<String, Object>>) apiCallResult.get("results");
        	result = convert(list);
        }

        return result;
    }
	
	public abstract Multimap<V, T> convert(List<Map<String, Object>> map);

}
