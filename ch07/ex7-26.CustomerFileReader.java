// Listing 7-26. CustomerFileReader

public class CustomerFileReader implements ItemStreamReader<Customer> {

    private Object curItem = null;

    private ItemStreamReader<Object> delegate;

    public CustomerFileReader(ItemStreamReader<Object> delegate) {
	this.delegate = delegate;
    }

    public Customer read() throws Exception {
	if(curItem == null) {
	    curItem = delegate.read();
	}

	Customer item = (Customer) curItem;
	curItem = null;

	if(item != null) {
	    item.setTransactions(new ArrayList<>());

	    while(peek() instanceof Transaction) {
		item.getTransactions().add((Transaction) curItem);
		curItem = null;
	    }
	}

	return item;
    }

    private Object peek() throws Exception {
	if (curItem == null) {
	    curItem = delegate.read();
	}
	return curItem;
    }

    public void close() throws ItemStreamException {
	delegate.close();
    }

    public void open(ExecutionContext arg0) throws ItemStreamException {
	delegate.open(arg0);
    }

    public void update(ExecutionContext arg0) throws ItemStreamException {
	delegate.update(arg0);
    }
}
