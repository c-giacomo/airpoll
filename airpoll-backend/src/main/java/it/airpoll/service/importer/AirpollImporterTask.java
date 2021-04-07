package it.airpoll.service.importer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Multimap;

import it.airpoll.api.AirpollCityAPI;
import it.airpoll.api.AirpollCountryAPI;
import it.airpoll.api.AirpollLocationAPI;
import it.airpoll.api.AirpollMeasurementAPI;
import it.airpoll.api.objects.CityObject;
import it.airpoll.api.objects.CountryObject;
import it.airpoll.api.objects.LocationObject;
import it.airpoll.api.objects.MeasurementObject;
import it.airpoll.mapper.CityMapper;
import it.airpoll.mapper.CountryMapper;
import it.airpoll.mapper.LocationMapper;
import it.airpoll.mapper.MeasurementMapper;
import it.airpoll.model.City;
import it.airpoll.model.Country;
import it.airpoll.model.Location;
import it.airpoll.model.Measurements;
import it.airpoll.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Giacomo
 * 
 * This is the importer function triggered at the application start. This function fill the (PostgreSQL) DB defined in the model package.
 * The function build and save a Country object if and only if a complete hierarchy relationship exists. To gain more result it's enough to
 * increase the limit result in the api implementation.
 *
 */


@Component
@Slf4j
@RequiredArgsConstructor(onConstructor = @__({ @Autowired }))
public class AirpollImporterTask {
	
	private final AirpollCountryAPI airpollCountryAPI;
	private final AirpollCityAPI airpollCityAPI;
	private final AirpollLocationAPI airpollLocationAPI;
	private final AirpollMeasurementAPI airpollMeasurementAPI;
	
	private final CountryMapper countryMapper;
	private final CityMapper cityMapper;
	private final LocationMapper locationMapper;
	private final MeasurementMapper measurementMapper;
	
	private final CountryRepository countryRepository;
	
	@Transactional
	public void execute() {
		Multimap<String, CountryObject> countryMap = airpollCountryAPI.get();
		Multimap<String, CityObject> cityMap = airpollCityAPI.get(countryMap.keySet());
		Multimap<String, LocationObject> locationMap = airpollLocationAPI.get(countryMap.keySet());
		Multimap<String, MeasurementObject> measurementMap = airpollMeasurementAPI.get(countryMap.keySet());
		
		List<Country> countries = new ArrayList<Country>();
		
		countryMap.forEach((k, v) -> {
			Collection<CityObject> citiesObj = cityMap.get(k);
			Country country = countryMapper.toEntity(v);
			
			if (!citiesObj.isEmpty()) {
				country.setCities(new ArrayList<City>());
				
				citiesObj.forEach(city -> {
					City c = cityMapper.objToModel(city);
					c.setCountry(country);
					country.getCities().add(c);
					Collection<LocationObject> locationObject = locationMap.get(city.getName());
					
					if (!locationObject.isEmpty()) {
						c.setLocation(new ArrayList<Location>());
						
						locationObject.forEach(location -> {
							Location loc = locationMapper.toEntity(location);
							loc.setCity(c);
							c.getLocation().add(loc);
							Collection<MeasurementObject> measurementObject = measurementMap.get(location.getLocation());
							
							if (!measurementObject.isEmpty()) {
								loc.setMeasurements(new ArrayList<Measurements>());
								Measurements measure = measurementMapper.toEntity(measurementObject);
								measure.setLocation(loc);
								loc.getMeasurements().add(measure);
								
								countries.add(country);
							}
						});
						
					}
				});
				
			}
		});
		
		try {
			countryRepository.saveAll(countries);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
		
	}

}
