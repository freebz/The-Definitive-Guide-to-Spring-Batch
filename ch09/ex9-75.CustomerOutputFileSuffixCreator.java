// Listing 9-75. CustomerOutputFileSuffixCreator

...
@Component
public class CustomerOutputFileSuffixCreator implements ResourceSuffixCreator {

    @Override
    public String getSuffix(int arg0) {
	return arg0 + ".xml";
    }
}
