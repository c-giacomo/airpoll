package it.airpoll.api.objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Giacomo
 * 
 * Location Object with common OpenAQ paramters
 *  
 */

@Getter
@Setter
@ToString
public class LocationObject {
	private Integer id;
	private String country;
	private String city;
	
	private String location;
	private Double latitude;
	private Double longitude;

}
