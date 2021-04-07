package it.airpoll.api.objects;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Giacomo
 * 
 * Measurement Object with common OpenAQ paramters
 *  
 */

@Getter
@Setter
@ToString
public class MeasurementObject {
	private String location;
	private String parameter;
	private Double value;
	private String unit;
	private String city;
	private String country;
	private Date date;
}
