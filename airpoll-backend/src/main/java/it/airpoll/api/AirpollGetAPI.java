package it.airpoll.api;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Multimap;

/**
 * @author Giacomo
 * 
 * Interface with common method to retrieve Airpoll Object
 *
 * @param <V> the map key
 * @param <T> the map value
 * 
 */

public interface AirpollGetAPI<V, T> {
	
	Multimap<? extends Object, ? extends Object> buildStandardParams();
	
	Multimap<? extends Object, ? extends Object> buildStandardParams(List<? extends Object> param);
	
	String buildUrl();

	Multimap<V, T> get();

	Multimap<V, T> get(Set<String> string);

}
