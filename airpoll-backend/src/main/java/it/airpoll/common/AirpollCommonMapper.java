package it.airpoll.common;

/**
 * @author Giacomo
 * 
 * Common Mapper declaration to serialize and deserialize objects
 *  
 */

public interface AirpollCommonMapper<D, E> {
	
	D toDto(E e);
	E toEntity(D d);
}
