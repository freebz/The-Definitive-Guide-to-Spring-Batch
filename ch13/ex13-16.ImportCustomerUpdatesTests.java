// Listing 13-16. ImportCustomerUpdatesTests Infrastructure

...
@ExtendWith(SpringExtension.class)
@JdbcTest
@ContextConfiguration(classes = {ImportJobConfiguration.class
				        CustomerItemValidator.class,
				        AccountItemProcessor.class,
				        BatchAutoConfiguration.class})
@SpringBatchTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ImportCustomerUpdatesTests {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private DataSource dataSource;

    private JdbcOperations jdbcTemplate;

    @BeforeEach
    public void setUp() {
	this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }
...
}
