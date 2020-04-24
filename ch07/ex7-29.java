// Listing 7-29. Customer's toString() Method

...
    @Override
    public String toString() {
	StringBuilder output = new StringBuilder();

	output.append(firstName);
	output.append(" ");
	output.append(middleInitial);
	output.append(". ");
	output.append(lastName);

	if(transactions != null && transactions.size() > 0) {
	    output.append(" has ");
	    output.append(transactions.size());
	    output.append(" transactions.");
	} else {
	    output.append(" has no transactions.");
	}

	return output.toString();
    }
...
