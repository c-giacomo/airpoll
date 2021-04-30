package it.airpoll.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Giacomo
 * 
 * CityDto class
 *
 */

@Getter
@Setter
@ToString
public class CityDto {
	
	private Integer id;
	private Integer id_country;
	
	private String name;
}
