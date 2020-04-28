// Listing 8-10. UpperCaseNameService

...
@Service
public class UpperCaseNameService {

    public Customer upperCase(Customer customer) {
	Customer newCustomer = new Customer(customer);

	newCustomer.setFirstName(newCustomer.getFirstName().toUpperCase());
	newCustomer.setMiddleInitial(newCustomer.getMiddleInitial().toUpperCase());
	newCustomer.setLastName(newCustomer.getLastName().toUpperCase());

	return newCustomer;
    }
}
