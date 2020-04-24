// Listing 7-38. Jaxb2Marshaller Configuration

...
@Bean
public Jaxb2Marshaller customerMarshaller() {
    Jaxb2Marshaller jaxb2Marshaller = new Jaxb2marshaller();

    jaxb2Marshaller.setClassesToBeBound(Customer.class,
		  Transaction.class);

    return jaxb2Marshaller;
}
...
