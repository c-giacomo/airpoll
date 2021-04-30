package it.airpoll.resources;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.airpoll.common.AirpollCommonResource;
import it.airpoll.dto.AirpollDto;
import it.airpoll.service.AirpollProjectionService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Giacomo
 * 
 * AirpollProjection Controller. It will be load in a Projection mode to retrieve with a single call 
 * whole data or filtered data by non null parameters. The call return a List of AirpollDto without any mapping
 *
 */


@Profile("projection")
@Slf4j
@RestController
@RequestMapping(value = "/api/v1")
public class AirpollProjectionResource extends AirpollCommonResource<AirpollDto, Integer, AirpollProjectionService> {
	
	public AirpollProjectionResource(AirpollProjectionService service) {
		super(service);
	}
	
	@GetMapping("/data")
	public List<AirpollDto> getData(
			@RequestParam(value = "countryId", required = false) Integer countryId,
			@RequestParam(value = "cityId", required = false) Integer cityId,
			@RequestParam(value = "page", defaultValue = "1") Integer page) throws Exception {
		log.info("retrieving information to display..");
		
		try {
			return service.get(countryId, cityId, page);
		} catch (Exception e) {
			throw e;
		}
	}

}
