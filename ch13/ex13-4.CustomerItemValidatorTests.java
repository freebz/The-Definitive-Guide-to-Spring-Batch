// Listing 13-4. CustomerStatementReaderTest

...
public class CustomerItemValidatorTests {

    @Mock
    private NamedParameterJdbcTemplate template;

    private CustomerItemValidator validator;

    @BeforeEach
    public void setUp() {
	MockitoAnnotations.initMocks(this);
	this.validator = new CustomerItemValidator(this.template);
    }
...
}
