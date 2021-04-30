package it.airpoll.enumerator;

import org.springframework.context.annotation.Profile;

/**
 * @author Giacomo
 * 
 * Profiled paged enum for Projection mode, the max result for page is 50 record
 *
 */

@Profile("projection")
public enum Paging {
	RESULT_FOR_PAGE(50);
	
	private final Integer value;

	Paging(final Integer newValue) {
        value = newValue;
    }

    public Integer value() { 
    	return value; 
    }
}
