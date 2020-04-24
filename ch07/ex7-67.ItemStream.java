// Listing 7-67. The ItemStream Interface

package org.springframework.batch.item;

public interface ItemStream {

    void open(ExecutionContext executionContext) throws ItemStreamException;
    void update(ExecutionContext executionContext) throws ItemStreamException;
    void close() throws ItemStreamException;
}
