// Listing 9-41. Customer Mapped for Noe4j

...
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = UuidStrategy.class)
    private UUID id;
    private String firstName;
    private String middleInitial;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zip;

    // Getters and setters removed
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
