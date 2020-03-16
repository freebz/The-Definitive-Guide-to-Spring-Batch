// Listing 4-45. Configuring LoggingStepStartStopListener

...
@EnableBatchProcessing
@SpringBootApplication
public class ChunkJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFActory stepBuilderFatory;

    @Bean
    public Job chunkBasedJob() {
	return this.jobBuilderFactory.get("chunkBasedJob")
	             .start(chunkStep())
	             .build();
    }

    @Bean
    public Step chunkStep() {
	return this.stepBuilderFatory.get("chunkStep")
	             .<String, String>chunk(1000)
	             .reader(itemREader())
	             .writer(itemWriter())
	             .listener(new LoggingStepStartStopListener())
	             .build();
    }

    @Bean
    public ListItemREader<String> itemReader() {
	List<String> items = new ArrayList<>(100000);

	for (int i = 0; i < 100000; i++) {
	    items.add(UUID.randomUUID().toString());
	}

	return new ListItemReader<>(items);
    }

    @Bean
    public ItemWriter<String> itemWriter() {
	return items -> {
	    for (String item : items) {
		System.out.println(">> current item = " + item);}
	};
    }

    @Bean
    public CompletionPolicy randomCompletionPolicy() {
	return new RandomChunkSizePolicy();
    }

    public static void main(String[] args) {
	SpringApplication.run(ChunkJob.class, args);
    }
}
