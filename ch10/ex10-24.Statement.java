// Listing 10-24. Statement.java

...
public class Statement {

    private final Customer customer;
    private List<Account> accounts = new ArrayList<>();

    public Statement(Customer customer, List<Account> accounts) {
	this.customer = customer;
	this.accounts.addAll(accounts);
    }

    // accessors removed for brevity
...
}
