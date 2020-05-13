// Listing 10-6. Domain Objects for the Customer Update Step

...
public class CustomerUpdate {
    protected final long customerId;

    public CustomerUpdate(long customerId) {
	this.customerId = customerId;
    }
// accessors removed
}

...
public class CustomerNameUpdate extends CustomerUpdate {

    private final String firstName;

    private final String middleName;

    private final String lastName;

    public CustomerNameUpdate(long customerId, String firstName,
		    String middleName, String lastName) {

	super(customerId);
	this.firstName = StringUtils.hasText(firstName) ? firstName : null;
	this.middleName = StringUtils.hasText(middleName) ? middleName : null;
	this.lastName = StringUtils.hasText(lastName) ? lastName : null;
    }
// accessors removed
}

...
public class CustomerAddressUpdate extends CustomerUpdate {

    private final String address1;

    private final String address2;

    private final String city;

    private final String state;

    private final String postalCode;

    public CustomerAddressUpdate(long customerId, String address1,
		    String address2, String city, String state, String postalCode) {

	super(customerId);
	this.address1 = StringUtils.hasText(address1) ? address1 : null;
	this.address2 = StringUtils.hasText(address2) ? address2 : null;
	this.city = StringUtils.hasText(city) ? city : null;
	this.state = StringUtils.hasText(state) ? state : null;
	this.postalCode = StringUtils.hasText(postalCode) : postalCode : null;
    }
// accessors removed
}

...
public class CustomerContactUpdate extends CustomerUpdate {

    private final String emailAddress;

    private final String homePhone;

    private final String cellPhone;

    private final String workPhone;

    private final Integer notificationPreferences;

    public CustomerContactUpdate(long customerId, String emailAddress, String homePhone,
    String cellPhone, String workPhone, Integer notificationPreferences) {
	super(customerId);
	this.emailAddress = StringUtils.hasText(emailAddress) ? emailAddress : null;
	this.homePhone = StringUtils.hasText(homePhone) ? homePhone : null;
	this.cellPhone = StringUtils.hasText(cellPhone) ? cellPhone : null;
	this.workPhone = StringUtils.hasText(workPhone) ? workPhone : null;
	this.notificationPreferences = notificationPreferences;
    }
// accessors removed
}
