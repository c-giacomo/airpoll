package it.airpoll.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

/**
 * @author Giacomo
 * 
 * Country Model
 * It has 1:N relation with City
 *
 */

@Data
@Entity
public class Country {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String code;
	private String name;
	private Long count;
	
	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
	private List<City> cities;

}
