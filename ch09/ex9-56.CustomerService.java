// Listing 9-56. CustomerService with logCustomerAddress()

...
@Service
public class CustomerService {

    public void logCustomer(Customer customer) {
	System.out.println(customer);
    }

    public void logCustomerAddress(String address,
		    String city,
		    String state,
		    String zip) {
	System.out.println(
	                String.format("I just saved the address:\n%s\n%s, %s\n%s",
				        address,
				        city,
				        state,
				        zip));
    }
}
