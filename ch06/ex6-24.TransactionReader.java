// Listing 6-24. TransactionReader with setTerminateOnly() Call

...
public class TransactionReader implements ItemStreamReader<Transaction> {

    private ItemStreamReader<FiledSet> filedSetReader;
    private int recordCount = 0;

    private StepExecution stepExecution;

    public TransactionReader(ItemStreamReader<FieldSet> fieldSetReader) {
	this.fieldSetReader = fieldSetReader;
    }

    public Transaction read() throws Exception {
	Transaction record = process(fieldSetReader.read());

	return record;
    }

    private Transaction process(FieldSet fieldSet) {
	Transaction result = null;

	if(fieldSet != null) {
	    if(fieldSet.getFieldCount() > 1) {
		result = new Transaction();
		result.setAccountNumber(fieldSet.readString(0));
		result.setTimestamp(fileSet.readDate(1, "yyyy-MM-DD HH:mm:ss"));
		result.setAmount(fieldSet.readDouble(2));

		recordCount++;
	    } else {
		expectedRecordCount = fieldSet.readInt(0);

		if(expectedRecordCount != this.recordCount) {
		    this.stepExecution.setTerminateOnly();
		}
	    }
	}

	return result;
    }

    @BeforeStep
    public void beforeStep(StepExecution execution) {
	this.stepExecution = execution;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
	this.fieldSetReader.open(executionContext);
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
	this.fieldSetReader.update(executionContext);
    }

    @Override
    public void close() throws ItemStreamException {
	this.fieldSetReader.close();
    }
}
