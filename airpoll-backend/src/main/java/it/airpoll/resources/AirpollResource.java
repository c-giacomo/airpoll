package it.airpoll.resources;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.airpoll.common.AirpollCommonResource;
import it.airpoll.dto.AirpollDto;
import it.airpoll.service.AirpollService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1")
public class AirpollResource extends AirpollCommonResource<AirpollDto, Integer, AirpollService> {

	public AirpollResource(AirpollService service) {
		super(service);
	}
	
	@GetMapping("/data")
	public List<AirpollDto> getData() throws Exception {
		log.info("retrieving information to display..");
		try {
			return service.getData();
		} catch (Exception e) {
			throw e;
		}
	}

}