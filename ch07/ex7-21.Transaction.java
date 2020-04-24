// Listing 7-21. Transaction Domain Object Code

...
public class Transaction {

    private String accountNumber;
    private Date transactionDate;
    private Double amount;

    private DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

    // Getters and setters are omitted
    @Override
    public String toString() {
	return "Transaction{" +
	             "accountNumber='" + accountNumber + '\'' +
	             ", transactionDate=" + transactionDate + '\'' +
	             ", amount=" + amount +
	             '}';
    }
}
