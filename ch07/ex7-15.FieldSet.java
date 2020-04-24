// Listing 7-15. FieldSet Interface

package org.springframework.batch.item.file.transform;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Properties;

public interface FieldSet {

    String[] getNames();
    boolean hasNames();
    String[] getValues();
    String readString(int index);
    String readString(String name);
    String readRawString(int index);
    String readRawString(String name);
    boolean readBoolean(int index);
    boolean readBoolean(String name);
    boolean readBoolean(int index, String trueValue);
    boolean readBoolean(String name, String trueValue);
    char readChar(int index);
    char readChar(String name);
    byte readByte(int index);
    byte readByte(String name);
    short readShort(int index);
    short readShort(String name);
    int readInt(int index);
    int readInt(String name);
    int readInt(int index, int defaultValue);
    int readInt(String name, int defaultValue);
    long readLong(int index);
    long readLong(String name);
    long readLong(int index, long defaultValue);
    long readLong(String name, long defaultValue);
    float readFloat(int index);
    float readFloat(String name);
    double readDouble(int index);
    double readDouble(String name);
    BigDecimal readBigDecimal(int index);
    BigDecimal readBigDecimal(String name);
    BigDecimal readBigDecimal(int index, BigDecimal defaultValue);
    BigDecimal readBigDecimal(String name, BigDecimal defaultValue);
    Date readDate(int index);
    Date readDate(String name);
    Date readDate(int index, Date defaultValue);
    Date readDate(String name, Date defaultValue);
    Date readDate(int index, String pattern);
    Date readDate(String name, String pattern);
    Date readDate(int index, String pattern, Date defaultValue);
    Date readDate(String name, String pattern, Date defaultValue);
    int getFieldCount();
    Properties getProperties();
}
