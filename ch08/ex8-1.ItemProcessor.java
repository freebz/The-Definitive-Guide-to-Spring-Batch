// Listing 8-1. ItemProcessor Interface

package org.springframework.batch.item;

public interface ItemProcessor<I, O> {

    O process(I item) throws Exception;
}
