// Listing 8-24. EvenFilteringItemProcessor

...
public class EvenFilteringItemProcessor implements ItemProcessor<Customer, Customer> {

    @Override
    public Customer process(Customer item) {
	return Integer.parseInt(item.getZip()) % 2 == 0 ? null: item;
    }
}
