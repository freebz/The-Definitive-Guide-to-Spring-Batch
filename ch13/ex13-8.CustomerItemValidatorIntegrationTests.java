// Listing 13-8. CustomerItemValidatorIntegrationTests

@ExtendWith(SpringExtension.class)
@JdbcTest
public class CustomerItemValidatorIntegrationTests {

    @Autowired
    private DataSource dataSource;

    private CustomerItemValidator customerItemValidator;
...
}
