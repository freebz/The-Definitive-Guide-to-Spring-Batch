// Listing 9-49. Customer Mapped for JPA

...
@Entity
@Table(name = "CUSTOMER")
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDNETITY)
    private long id;
    private String firstName;
    private String middleInitial;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zip;

    // Getters and setters removed for brevity
    
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
