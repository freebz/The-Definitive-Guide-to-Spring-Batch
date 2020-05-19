// Listing 11-20. workerStep Configuration

...
public Step workerStep() {
    return this.workerStepBuilderFactory.get("workerStep")
	            .inputChannel(requests())
	            .outputChannel(replies())
	            .<Transaction, Transaction>chunk(100)
	            .reader(fileTransactionReader(null))
	            .writer(writer(null))
	            .build();
}
...
