// Listing 7-18. CustomerFileLineTokenizer

...
public class CustomerFileLineTokenizer implements LineTokenizer {

    private String delimiter = ",";
    private String[] names = new String[] {"firstName",
		  "middleInitial",
		  "lastName",
		  "address",
		  "city",
		  "state",
		  "zipCode"};

    private FieldSetFactory fieldSetFactory = new DefaultFieldSetFactory();

    public FieldSet tokenize(String record) {

	String[] fields = record.split(delimiter);

	List<String> parsedFields = new ArrayList<>();

	for (int i = 0; i < fields.length; i++) {
	    if (i == 4) {
		parsedFields.set(i - 1,
			     parsedFields.get(i - 1) + " " + fields[i]);
	    } else {
		parsedFields.add(fields[i]);
	    }
	}

	return fieldSetFactory.create(parsedFields.toArray(new String [0]),
				     name);
    }
}
