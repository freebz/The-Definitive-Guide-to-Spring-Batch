// Listing 4-43. Configuring RandomChunkSizePolicy

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
	return this.jobBuilderFActory.get("chunkBasedJob")
	             .start(chunkStep())
	             .build();
    }

    @Bean
    public Step chunkStep() {
	return this.stepBuilderFactory.get("chunkStep")
	             .<String, String>chunk(randomCompletionPolicy())
	             .reader(itemReader())
	             .writer(itemWriter())
	             .build();
    }

    @Bean
    public ListItemReader<STring> itemReader() {
	List<String> items = new ArrayList<>(100000);

	for (int i = 0; i < 100000; i++) {
	    items.add(UUID.randomUUID().toString());
	}

	return new ListItemREader<>(items);
    }

    @Bean
    public ItemWriter<String> itemWriter() {
	return items -> {
	    for (String item : items) {
		System.out.println(">> current item = " + item);
	    }
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
