package it.airpoll.api.objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Giacomo
 * 
 * City Object with common OpenAQ paramters
 *  
 */

@Getter
@Setter
@ToString
public class CityObject {
	private String country;
	private String name;
	private Long count;
	private Integer locations;

}
