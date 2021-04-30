package it.airpoll.common;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import it.airpoll.common.exception.ItemNotFoundException;
import lombok.RequiredArgsConstructor;

/**
 * @author Giacomo
 * 
 * Pattern for basic service implementation
 * 
 * @param <E> is the model Entity
 * @param <D> is the DTO
 * @param <ID> is the ID class
 * @param <R> is the repository extends JpaRepository
 * @param <M> is the mapstruct Mapper
 *  
 */

@RequiredArgsConstructor(onConstructor = @__({ @Autowired }))
public abstract class AirpollCommonService<E, D, ID, R extends JpaRepository<E, ID>, M extends AirpollCommonMapper<D, E>> {
	
	protected final R repository;
	protected final M mapper;
	
	protected List<D> getAll() throws ItemNotFoundException {
		List<E> entities = repository.findAll();
		if (entities.isEmpty()) throw new ItemNotFoundException("Entities not found!");
		List<D> dtos = entities.stream().map(mapper::toDto).collect(Collectors.toList());
		return dtos;
	}
	
	protected D findById(ID Id) throws ItemNotFoundException {
		Optional<E> entity = repository.findById(Id);
		return entity.map(i -> mapper.toDto(i))
				.orElseThrow(() -> new ItemNotFoundException("Entity not Found"));
	}

}
