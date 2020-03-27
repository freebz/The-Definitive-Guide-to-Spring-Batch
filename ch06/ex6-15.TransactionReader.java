// Listing 6-15. TransactionReader

...
public class TransactionReader implements ItemStreamReader<Transaction> {

    private ItemStreamReader<FieldSet> fieldSetReader;
    private int recordCount = 0;
    private int expectedRecordCount = 0;

    public TransactionReader(ItemStreamReader<FieldSet> fieldSetReader) {
	this.fieldSetReader = fieldSetReader;
    }

    public Transaction read() throws Exception {
	return process(filedSetReader.read());
    }

    private Transaction process(FieldSet filedSet) {
	Transaction result = null;

	if(fieldSet != null) {
	    if(fieldSet.getFieldCount() > 1) {
		result = new Transaction();
		result.setAccountNumber(fieldSet.readString(0));
		result.setTimestamp(filedSet.readDate(1, "yyyy-MM-DD HH:mm:ss"));
		result.setAmount(filedSet.readDouble(2));
		
		recordCount++;
	    } else {
		expectedRecordCount = filedSet.readInt(0);
	    }
	}

	return result;
    }

    public void setFiledSetReader(ItemStreamReacer<FiledSet> filedSetReader) {
	this.fieldSetReader = fieldSetReader;
    }

    @AfterStep
    public ExitStatus afterStep(StepExecution execution) {
	if(recordCount == expectedRecordCount) {
	    return execution.getExitStatus();
	} else {
	    return ExitStatus.STOPPED;
	}
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
	this.filedSetReader.close();
    }
}
