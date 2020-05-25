// Listing 13-5. testValidCustomer()

...
@Test
public void testValidCustomer() {

    // given

    CustomerUpdate customer = new CustomerUpdate(5L);

    // when
    ArgumentCaptor<Map<String, Long>> parameterMap =
	            ArgumentCaptor.forClass(Map.class);
    when(this.template.queryForObject(eq(CustomerItemValidator.FIND_CUSTOMER),
				    parameterMap.capture(),
				    eq(Long.class)))
	    .thenReturn(2L);

    this.validator.validate(customer);

    // then

    assertEquals(5L, (long) parameterMap.getValue().get("id"));
}
...
