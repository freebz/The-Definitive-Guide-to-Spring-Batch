// Listing 10-33. statementItemWriter

...
@Bean
@StepScope
public MultiResourceItemWriter<Statement> statementItemWriter(@Value("#{jobParameters['outputDirectory']}") Resource outputDir) {
    return new MultiResourceItemWriterBuilder<Statement>()
	.name("statementItemWriter")
	.resource(outputDir)
	.itemCountLimitPerResource(1)
	.delegate(invidualStatementItemwriter())
	.build();
}
