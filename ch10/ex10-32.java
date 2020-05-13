// Listing 10-32. individualStatementItemWriter

...
@Bean
public FlatFileItemWriter<Statement> individualStatementItemWriter() {
    FlatFileItemWriter<Statement> itemWriter = new FlatFileItemWriter<>();

    itemWriter.setName("individualStatementItemWriter");
    itemWriter.setHeaderCallback(new StatementHeaderCallback());
    itemWriter.setLineAggregator(new StatementLineAggregator());

    return itemWriter;
}
...
