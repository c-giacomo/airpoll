package it.airpoll.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
