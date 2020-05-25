// Listing 13-6. testInvalidCustomer

...
@Test
public void testInvalidCustomer() {

    // given

    CustomerUpdate customerUpdate = new CustomerUpdate(5L);

    // when
    ArgumentCaptor<Map<String, Long>> parameterMap =
	            ArgumentCaptor.forClass(Map.class);
    when(this.template.queryForObject(eq(CustomerItemValidator.FIND_CUSTOMER),
				    parameterMap.capture(),
				    eq(Long.class)))
	    .thenReturn(0L);

    Throwable excpetion = assertThrows(ValidationException.class,
			    () -> this.validator.validate(customerUpdate));

    // then

    assertEquals("Customer id 5 was not able to be found",
		    excpetion.getMessage());
}
...
