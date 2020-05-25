// Listing 13-1. A Basic JUnit Test Case

package com.apress.springbatch.chapter13;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringTest {

    @Test
    public void testStringEquals() {
	String michael = "Michael";
	String michael2 = michael;
	String michael3 = new String("Michael");
	String michael4 = "Michael";

	assertTrue(michael == michael2);
	assertFalse(michael == michael3);
	assertTrue(michael.equals(michael2));
	assertTrue(michael.equals(michael3));
	assertTrue(michael == michael4);
	assertTrue(michael4.equals(michael4));
    }
}
