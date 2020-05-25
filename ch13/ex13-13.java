// Listing 13-13. FlatFileItemReaderTests Infrastructure

...
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ImportJobConfiguration.class,
				CustomerItemValidator.class,
				AccountItemProcessor.class})
@JdbcTest
@EnableBatchProcessing
@SpringBatchTest
public class FlatFileItemReaderTests {

    @Autowired
    private FlatFileItemReader<CustomerUpdate> customerUpdateItemReader;
...
}
