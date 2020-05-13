// Listing 10-25. Customer.java and Account.java

...
public class Customer {

    private final long id;
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String address1;
    private final String address2;
    private final String city;
    private final String state;
    private final String postalCode;
    private final String ssn;
    private final String emailAddress;
    private final String homePhone;
    private final String cellPhone;
    private final String workPhone;
    private final int notificationPreferences;

    public Customer(long id, String firstName, String middleName, String lastName,
		    String address1, String address2, String city, String state,
		    String postalCode, String ssn, String emailAddress, String homePhone,
		    String cellPhone, String workPhone, int notificationPreferences) {

	this.id = id;
	this.firstName = firstName;
	this.middleName = middleName;
	this.lastName = lastName;
	this.address1 = address1;
	this.address2 = address2;
	this.city = city;
	this.state = state;
	this.postalCode = postalCode;
	this.ssn = ssn;
	this.emailAddress = emailAddress;
	this.homePhone = homePhone;
	this.cellPhone = cellPhone;
	this.workPhone = workPhone;
	this.notificationPreferences = notificationPreferences;
    }

    // accessors removed

    ...
}

...
public class Account {

    private final long id;
    private final BigDecimal balance;
    private final Date lastStatementDate;
    private final List<Transaction> transactions = new ArrayList<>();

    public Account(long id, BigDecimal balance, Date lastStatementDate) {
	this.id = id;
	this.balance = balance;
	this.lastStatementDate = lastStatementDate;
    }

    // accessors removed

    ...
}
