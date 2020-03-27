// Listing 6-17. TransactionApplierProcessor

...
public class TransactionApplierProcessor implements
            ItemProcessor<AccountSummary, AccountSummary> {

    private TransactionDao transactionDao;

    public TransactionApplierProcessor(TransactionDao transactionDao) {
	this.transactionDao = transactionDao;
    }

    public AccountSummary process(AccountSummary summary) throws Exception {
	List<Transaction> transactions = transactionDao
	             .getTransactionsByAccountNumber(summary.getAccountNumber());

	for (Transaction transaction : transactions) {
	    summary.setCurrentBalance(summary.getCurrentBalance()
			+ transaction.getAmount());
	}
	return summary;
    }
}
