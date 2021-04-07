package it.airpoll.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.airpoll.common.AirpollCommonResource;
import it.airpoll.dto.CityDto;
import it.airpoll.service.CityService;

@RestController
@RequestMapping(value = "/api/v1/city")
public class CityResource extends AirpollCommonResource<CityDto, Integer, CityService> {

	public CityResource(CityService service) {
		super(service);
	}

}
