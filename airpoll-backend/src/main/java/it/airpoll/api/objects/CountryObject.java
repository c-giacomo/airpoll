package it.airpoll.api.objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Giacomo
 * 
 * Country Object with common OpenAQ paramters
 *  
 */

@Getter
@Setter
@ToString
public class CountryObject {
	private String code;
	private String name;
	private Long count;
	private Integer cities;
	private Integer locations;
}
