package it.airpoll.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

/**
 * @author Giacomo
 * 
 * Location Model
 * It has N:1 relation with City and 1:N relation with Measurements
 *
 */

@Data
@Entity
public class Location {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer locationId;
	private String identifier;
	
	private Double latitude;
	private Double longitude;
	
	@ManyToOne
	private City city;
	
	@OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
	private List<Measurements> measurements;

}
