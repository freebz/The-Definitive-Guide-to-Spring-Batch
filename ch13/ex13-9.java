// Listing 13-9. CustomerItemValidatorIntegrationTests Setup

...
@BeforeEach
public void setUp() {
    NamedParameterJdbcTemplate template =
	    new NamedParameterJdbcTemplate(this.dataSource);
    this.customerItemValidator = new CustomerItemValidator(template);
}
...
