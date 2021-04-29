package it.airpoll.dto;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

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
	
	public AirpollDto() {}
	
	public AirpollDto(String location, String city, String country, Double pm25, Double no2, Double co, Double pm10, Double o3, 
			Double latitude, Double longitude, Date local) {
		this.location = location;
		this.city = city;
		this.country = country;
		this.pm25 = pm25;
		this.no2 = no2;
		this.co = co;
		this.pm10 = pm10;
		this.o3 = o3;
		this.latitude = latitude;
		this.longitude = longitude;
		this.local = convertToLocalDateViaInstant(local);
	}
	
	private LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
	
}
