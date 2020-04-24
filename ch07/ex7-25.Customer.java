// Listing 7-25. Updated Customer Object

...
public class Customer {

    private String firstName;
    private String middleInitial;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zipCode;

    List<Transaction> transactions;

    public Customer() {
    }

    // Getters and setters removed for brevity
    ...
}
