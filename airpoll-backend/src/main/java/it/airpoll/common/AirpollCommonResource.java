package it.airpoll.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import it.airpoll.common.exception.ItemNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Giacomo
 * 
 * Pattern for basic resource implementation
 *
 * @param <D> The DTO
 * @param <ID> The class ID of the Entity
 * @param <S> The service
 * 
 */

@Slf4j
@RequiredArgsConstructor(onConstructor = @__({ @Autowired }))
public abstract class AirpollCommonResource<D, ID, S extends AirpollCommonService<?, D, ID, ?, ?>> {
	
	protected final S service;
	
	@GetMapping
	protected List<D> getAll() throws ItemNotFoundException {
		log.info(String.format("calling %s getAll()", service.getClass().getName()));
		return service.getAll();
	}

}
