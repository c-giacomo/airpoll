package it.airpoll.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	
	private String name;
	
	@ManyToOne
	private Country country;
	
	@OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
	private List<Location> location;
}
