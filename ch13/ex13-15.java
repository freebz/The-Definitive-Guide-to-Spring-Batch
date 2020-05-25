// Listing 13-15. FlatFileItemReaderTests# testTypeConversion

...
@Test
public void testTypeConversion() throws Exception {
    this.customerUpdateItemReader.open(new ExecutionContext());

    assertTrue(this.customerUpdateItemReader.read() instanceof CustomerAddressUpdate);
    assertTrue(this.customerUpdateItemReader.read() instanceof CustomerContactUpdate);
    assertTrue(this.customerUpdateItemReader.read() instanceof CustomerNameUpdate);
}
...
