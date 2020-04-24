// Listing 7-17. LineTokenizer Interface

package org.springframework.batch.item.file.transform;

public interface LineTokenizer {

    FieldSet tokenize(String line);
}
