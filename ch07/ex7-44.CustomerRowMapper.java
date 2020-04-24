// Listing 7-44. CustomerRowMapper

...
public class CustomerRowMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet resultSet, int rowNumber) throws
	          SQLException {
	Customer customer = new Customer();

	customer.setId(resultSet.getLong("id"));
	customer.setAddress(resultSet.getString("address"));
	customer.setCity(resultSet.getString("city"));
	customer.setFirstName(resultSet.getString("firstName"));
	customer.setLastName(resultSet.getString("lastName"));
	customer.setMiddleInitial(resultSet.getString("middleInitial"));
	customer.setState(resultSet.getString("state"));
	customer.setZipCode(resultSet.getString("zipCode"));

	return customer;
    }
}
