package it.airpoll.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CountryDto {
	
	private Integer id;
	
	private String code;
	private String name;
}
