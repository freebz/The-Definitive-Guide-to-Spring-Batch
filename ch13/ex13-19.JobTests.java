// Listing 13-19. JobTests Infrastructure

...
@ExtendWith(SpringExtension.class)
@SpringBatchTest
@ContextConfiguration(classes = {JobTests.BatchConfiguration.class,
BatchAutoConfiguration.class})
public class JobTests {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;
...
