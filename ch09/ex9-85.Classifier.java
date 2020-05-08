// Listing 9-85. The Classifier Interface

package org.springframework.batch.classify;

public interface Classifier<C, T> {

    T classify(C classifiable);
}
