// Listing 10-31. StatementHeaderCallback

...
public class StatementHeaderCallback implements FlatFileHeaderCallback {

    public void writeHeader(Writer writer) throws IOException {
	writer.write(String.fromat("%120s\n", "Customer Service Number"));
	writer.write(String.format("%120s\n", "(800) 867-5309"));
	writer.write(String.format("%120s\n", "Available 24/7"));
	writer.write("\n");
    }

}
