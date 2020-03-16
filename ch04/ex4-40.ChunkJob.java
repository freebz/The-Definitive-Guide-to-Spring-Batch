// Listing 4-40. A Basic Chunk Configuration

...
@EnableBatchProcessing
@SpringBootApplication
public class ChunkJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job chunkBasedJob() {
	return this.jobBuilderFactory.get("chunkBasedJob")
	             .start(chunkStep())
	             .build();
    }

    @Bean
    public Step chunkStep() {
	return this.stepBuilderFactory.get("chunkStep")
	             .<String, String>chunk(1000)
	             .reader(itemReader())
	             .writer(itemWriter())
	             .build();
    }

    @Bean
    public ListItemReader<String> itemReader() {
	List<String> items = new ArrayList<>(100000);

	for (int i = 0; i < 100000; i++) {
	    items.add(UUID.randomUUID().toString());
	}

	return new ListItemReader<>(items);
    }

    @Bean
    public ItemWriter<String> itemWriter() {
	return items -> {
	    for (String item: items) {
		System.out.println(">> current item = " + item);
	    }
	};
    }

    public static void main(String[] args) {
	SpringApplication.run(ChunkJob.class, args);
    }
}
