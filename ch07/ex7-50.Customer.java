// Listing 7-50. Customer Object Mapped to the Customer Table via Hibernate Annotations

...
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    private Long id;

    @Column(name = "firstName")
    private String firstName;
    @Column(name = "middleInitial")
    private String middleInitial;
    @Column(name = "lastName")
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zipCode;

    public Customer() {
    }

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
	              ", zipCode='" + zipCode + '\'' +
	              '}';
    }
}
