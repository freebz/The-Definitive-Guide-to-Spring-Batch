// Listing 7-43. Customer

...
public class Customer {

    private Long id;

    private String firstName;
    private String middleInitial;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zipCode;

    public Customer() {}

    // Getters and setters removed

    @Override
    public String toString() {
	return "Customer{" +
	            "id=" + id +
	            ", firstName='" + firstName + '\'' +
	            ", lastName='" + lastName + '\'' +
	            ", address='" + address + '\'' +
	            ", city='" + city + '\'' +
	            ", state='" + state + '\'' +
	            ", zipCode='" + zipCode + '\'' +
	            '}';
    }
}
