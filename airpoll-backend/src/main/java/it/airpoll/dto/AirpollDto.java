package it.airpoll.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Giacomo
 * 
 * DTO to show on view side (Angular)
 *
 */


@Getter
@Setter
@ToString
public class AirpollDto {
	
	private String location;
	private String city;
	private String country;
	
	private Double pm25;
	private Double no2;
	private Double co;
	private Double pm10;
	private Double o3;
	
	private Double latitude;
	private Double longitude;
	
	private LocalDate local;
	
}
