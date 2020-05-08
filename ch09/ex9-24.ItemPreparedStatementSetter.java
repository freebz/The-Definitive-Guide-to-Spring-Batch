// Listing 9-24. ItemPreparedStatementSetter Interface

package org.springframework.batch.item.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ItemPreparedStatementSetter<T> {
    void setValues(T item, PreparedStatement ps) throws SQLException;
}
