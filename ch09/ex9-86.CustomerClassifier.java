// Listing 9-86. CustomerClassifier

...
public class CustomerClassifier implements
	        Classifier<Customer, ItemWriter<? super Customer>> {

    private ItemWriter<Customer> fileItemWriter;
    private ItemWriter<Customer> jdbcItemWriter;

    public CustomerClassifier(StaxEventItemWriter<Customer> fileItemWriter,
			      JdbcBatchItemWriter<Customer> jdbcItemWriter) {
	this.fileItemWriter = fileItemWriter;
	this.jdbcItemWriter = jdbcItemWriter;
    }

    @Override
    public ItemWriter<Customer> classify(Customer customer) {
	if(customer.getState().matches("^[A-M].*")) {
	    return fileItemWriter;
	} else {
	    return jdbcItemWriter;
	}
    }
}
