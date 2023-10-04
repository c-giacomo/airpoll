package it.airpoll.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/**
 * @author Giacomo
 * 
 * Measurements Model
 * It has N:1 relation with Location
 *
 */

@Data
@Entity
public class Measurements {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Date local;
	
	private Double pm25;
	private Double no2;
	private Double so2;
	private Double co;
	private Double pm10;
	private Double o3;
	
	@ManyToOne
	private Location location;

}
