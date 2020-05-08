// Listing 9-53. CustomerService.java

package com.apress.springbatch.chapter9;

@Service
public class CustomerService {

    public void logCustomer(Customer cust) {
	System.out.println("I just saved " + cust);
    }
}
