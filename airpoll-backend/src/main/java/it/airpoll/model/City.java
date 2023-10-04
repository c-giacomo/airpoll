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
 * City Model
 * It has N:1 relation with Country and 1:N relation with Location
 *
 */

@Data
@Entity
public class City {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String city;
	
	@ManyToOne
	private Country country;
	
	@OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
	private List<Location> location;
}
