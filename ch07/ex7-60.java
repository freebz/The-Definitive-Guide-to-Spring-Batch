// Listing 7-60. MongoItemReader

...
@Bean
@StepScope
public MongoItemReader<Map> tweetsItemReader(MongoOperations mongoTemplate,
	     @Value("#{jobParameters['hashTag']}") String hashtag) {

    return new MongoItemReaderBuilder<Map>()
	          .name("tweetsItemReader")
	          .targetType(Map.class)
	          .jsonQuery("{ \"entities.hashtags.text\": { $eq: ?0 }}")
	          .collection("tweets_collection")
	          .parameterValues(Collections.singletonList(hashtag))
	          .pageSize(10)
	          .sorts(Collections.singletonMap("created_at", Sort.Direction.ASC))
	          .template(mongoTemplate)
	          .build()
}

@Bean
public Step copyFileStep() {
    return this.stepBuilderFactory.get("copyFileStep")
	          .<Map, Map>chunk(10)
	          .reader(tweetsItemReader(null, null))
	          .writer(itemWriter())
	          .build();
}
...
