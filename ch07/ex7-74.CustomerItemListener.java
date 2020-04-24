// Listing 7-74. CustomerItemListener

...
public class CustomerItemListener {

    private static final Log logger = LogFactory.getLog(CustomerItemListener.class);

    @OnReadError
    public void onReadError(Exception e) {
	if(e instanceof FlatFileParseException) {
	    FlatFileParseException ffpe = (FlatFileParseException) e;

	    StringBuilder errorMessage = new StringBuilder();
	    errorMessage.append("An error occured while processing the " +
				ffpe.getLineNumber() +
				" line of the file. Below was the faulty " +
				"input.\n");
	    errorMessage.append(ffpe.getInput() + "\n");

	    logger.error(errorMessage.toString(), ffpe);
	} else {
	    logger.error("An error has occurred", e);
	}
    }
}
