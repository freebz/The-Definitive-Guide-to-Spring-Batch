// Listing 6-14. Domain Objects

...
public class Transaction {

    private String accountNumber;

    private Date timestamp;

    public String getAccountNumber() {
	return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
	this.accountNumber = accountNumber;
    }

    public Date getTimestamp() {
	return timestamp;
    }

    public void setTimestamp(Date timestamp) {
	this.timestamp = timestamp;
    }

    public double getAmount() {
	return amount;
    }

    public void setAmount(double amount) {
	this.amount = amount;
    }
}

...
public class AccountSummary {

    private int id;

    private String accountNumber;

    private Double currentBalance;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getAccountNumber() {
	return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
	this.accountNumber = accountNumber;
    }

    public Double getCurrentBalance() {
	return currentBalance;
    }

    public void setCurrnetBalance(Double currentBalance) {
	this.currentBalance = currentBalance;
    }
}
