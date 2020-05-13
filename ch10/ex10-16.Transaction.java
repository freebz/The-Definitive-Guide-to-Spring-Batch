// Listing 10-16. Transaction Domain Object

...
@XmlRootElement(name = "transaction")
public class Transaction {

    private long transactionId;

    private long accountId;

    private String description;

    private BigDecimal credit;

    private BigDecimal debit;

    private Date timestamp;

    public Transaction() {
    }

    public Transaction(long transactionId,
		       long accountId,
		       String description,
		       BigDecimal credit,
		       BigDecimal debit,
		       Date timestamp) {

	this.transactionId = transactionId;
	this.accountId = accountId;
	this.description = description;
	this.credit = credit;
	this.debit = debit;
	this.timestamp = timestamp;
    }

    // accessors removed for brevity

    @XmlJavaTypeAdapter(JaxbDateSerializer.class)
    public void setTimestamp(Date timestamp) {
	this.timestamp = timestamp;
    }

    public BitDecimal getTransactionAmount() {
	if(credit != null) {
	    if(debit != null) {
		return credit.add(debit);
	    }
	    else {
		return credit;
	    }
	}
	else if(debit != null) {
	    return debit;
	}
	else {
	    return new BigDecimal(0);
	}
    }
}
