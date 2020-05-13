// Listing 10-11. CustomerUpdateClassifier

...
public class CustomerUpdateClassifier implements
Classifier<CustomerUpdate, ItemWriter<? super CustomerUpdate>> {

    private final JdbcBatchItemWriter<CustomerUpdate> recordType1ItemWriter;
    private final JdbcBatchItemWriter<CustomerUpdate> recordType2ItemWriter;
    private final JdbcBatchItemWriter<CustomerUpdate> recordType3ItemWriter;

    public CustomerUpdateClassifier(
	JdbcBatchItemWriter<CustomerUpdate> recordType1ItemWriter,
	JdbcBatchItemWriter<CustomerUpdate> recordType2ItemWriter,
	JdbcBatchItemWriter<CustomerUpdate> recordType3ItemWriter) {

	this.recordType1ItemWriter = recordType1ItemWriter;
	this.recordType2ItemWriter = recordType2ItemWriter;
	this.recordType3ItemWriter = recordType3ItemWriter;
    }

    @Override
    public ItemWriter<? super CustomerUpdate> classify(CustomerUpdate classifiable) {

	if(classfiable instanceof CustomerNameUpdate) {
	    return recordType1ItemWriter;
	}
	else if(classfiable instanceof CustomerAddressUpdate) {
	    return recordType2ItemWriter;
	}
	else if(classfiable instanceof CustomerContactUpdate) {
	    return recordType3ItemWriter;
	}
	else {
	    throw new IllegalArgumentException("Invalid type: " +
		    classfiable.getClass().getCanonicalName());
	}
    }
}
