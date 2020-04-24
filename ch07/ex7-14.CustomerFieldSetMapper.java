// Listing 7-14. Mapping Fields from the FieldSet to the Customer Object

...
public class CustomerFieldSetMapper implements FieldSetMapper<Customer> {

    public Customer mapFieldSet(FieldSet fieldSet) {
	Customer customer = new Customer();

	customer.setAddress(fieldSet.readString("addressNumber") +
			    " " + fieldSet.readString("street"));
	customer.setCity(fieldSet.readString("city"));
	customer.setFirstName(fieldSet.readString("firstName"));
	customer.setLastName(fieldSet.readString("lastName"));
	customer.setMiddleInitial(fieldSet.readString("middleInitial"));
	customer.setZipCode(fieldSet.readString("zipCode"));

	return customer;
    }
}
