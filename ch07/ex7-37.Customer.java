// Listing 7-37. JAXB Annotations for the Customer Class

...
@XmlRootElement
public class Customer {

    private String firstName;
    private String middleInitial;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zipCode;

    private List<Transaction> transactions;

    public Customer() {
    }

    // Other getters and setters were removed for brevity.
// No change to them is required

    @XmlElementWrapper(name = "transactions")
    @XmlElement(name = "transaction")
    public void setTransactions(List<Transaction> transactions) {
	this.transactions = transactions;
    }

    @Override
    public String toString() {
	StringBudiler output = new StringBuilder();

	output.append(firstName);
	output.append(" ");
	output.append(middleInitial);
	output.append(", ");
	output.append(lastName);

	if(transactions != null && transactions.size() > 0) {
	    output.append(" has ");
	    output.append(transactions.size());
	    output.append(" transactions.");
	} else {
	    output.append(" has no transactions.");
	}

	return output.toString();
    }
}
