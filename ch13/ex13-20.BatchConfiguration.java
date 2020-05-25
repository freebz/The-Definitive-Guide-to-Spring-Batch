// Listing 13-20. Test Job

...
@Configuration
@EnableBatchProcessing
public static class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public ListItemReader<String> itemReader() {
	return new ListItemReader<>(Arrays.asList("foo", "bar", "baz"));
    }

    @Bean
    public ItemWriter<String> itemWriter() {
	return (list -> {
		list.forEach(System.out::println);
	});
    }

    @Bean
    public Step step1() {
	return this.stepBuilderFactory.get("step1")
	                .<String, String>chunk(10)
	                .reader(itemReader())
	                .writer(itemWriter())
	                .build();
    }

    @Bean
    public Job job() {
	return this.jobBuilderFactory.get("job")
	                .start(step1())
	                .build();
    }

    @Bean
    public DataSource dataSource() {
	return new EmbaddedDatabaseBuilder().build();
    }
}
...
