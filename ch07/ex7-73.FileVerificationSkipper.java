// Listing 7-73. FileVerificationSkipper

...
public class FileVerificationSkipper implements SkipPolicy {

    public boolean shouldSkip(Throwable exception, int skipCount)
	throws SkipLimitExceededException {

	if(exception instanceof FileNotFoundException) {
	    return false;
	} else if(exception instanceof ParseException && skipCount <= 10) {
	    return true;
	} else {
	    return false;
	}
    }
}
