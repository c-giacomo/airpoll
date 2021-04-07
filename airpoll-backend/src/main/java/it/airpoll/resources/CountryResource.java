package it.airpoll.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.airpoll.common.AirpollCommonResource;
import it.airpoll.dto.CountryDto;
import it.airpoll.service.CountryService;

@RestController
@RequestMapping(value = "/api/v1/country")
public class CountryResource extends AirpollCommonResource<CountryDto, Integer, CountryService> {

	public CountryResource(CountryService service) {
		super(service);
	}

}
