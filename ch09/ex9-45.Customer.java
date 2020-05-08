// Listing 9-45. Customer.java for Pivotal Gemfire

...
@Region(value = "Customers")
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private String firstName;
    private String middleInitial;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zip;

    // Accessors removed for brevity

    @Override
    public String toString() {
	return "Customer{" +
	                "id=" + id +
	                ", firstName='" + firstName + '\'' +
	                ", middleInitial='" + middleInitial + '\'' +
	                ", lastName='" + lastName + '\'' +
	                ", address='" + address + '\'' +
	                ", city='" + city + '\'' +
	                ", state='" + state + '\'' +
	                ", zip='" + zip + '\'' +
	                '}';
    }
}
