// Listing 10-17. JaxbDateSerializer

...
public class JaxbDateSerializer extends XmlAdapter<String, Date> {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Override
    public String marshal(Date date) throws Exception {
	return dateFormat.format(date);
    }

    @Override
    public Date unmarshal(String date) throws Exception {
	return dateFormat.parse(date);
    }
}
