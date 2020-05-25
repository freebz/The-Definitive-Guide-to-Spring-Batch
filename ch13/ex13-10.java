// Listing 13-10. CustomerItemValidatorIntegrationTests Tests

...
@Test
public void testNoCustomers() {
    CustomerUpdate customerUpdate = new CustomerUpdate(-5L);

    ValidationException exception =
	            assertThrows(ValidationException.class,
			    () -> this.customerItemValidator.validate(customerUpdate));

    assertEquals("Customer id -5 was not able to be found",
		    exception.getMessage());
}

@Test
public void testCustomers() {
    CustomerUpdate customerUpdate = new CustomerUpdate(5L);
    this.customerItemValidator.validate(customerUpdate);
}
...
