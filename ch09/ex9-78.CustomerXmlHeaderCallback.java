// Listing 9-78. CustomerXmlHeaderCallback

...
@Component
public class CustomerXmlHeaderCallback implements StaxWriterCallback {

    @Override
    public void write(XMLEventWriter writer) throws IOException {
	XMLEventFactory factory = XMLEventFactory.newInstance();

	try {
	    writer.add(factory.createStartElement("", "", "identification"));
	    writer.add(factory.createStartElement("", "", "author"));
	    writer.add(factory.createAttribute("name", "Michael Minella"));
	    writer.add(factory.createEndElement("", "", "author"));
	    writer.add(factory.createEndElement("", "", "identification"));
	}
	catch (XMLStreamException xmlse) {
	    System.err.println("An error occured: " + xmlse.getMessage());
	    xmlse.printStackTrace(System.err);
	}
    }
}
