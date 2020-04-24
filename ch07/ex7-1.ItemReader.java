// Listing 7-1. org.springframework.batch.item.ItemReader<T>

package org.springframework.batch.item;

public interface ItemReader<T> {

    T read() throws Exception, UnexpectedInputException, ParseException,
		    NonTransientResourceException;
}
