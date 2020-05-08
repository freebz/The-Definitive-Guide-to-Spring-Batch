// Listing 9-60. JMS Resource Configuration

...
@Bean // Serialize message content to json using TextMessage
public MessageConverter jackson kacksonJmsMessageConverter() {
    MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
    converter.setTargetType(MessageType.TEXT);
    converter.setTypeIdPropertyName("_type");
    return converter;
}

@Bean
public JmsTemplate jmsTemplate(ConntectionFactory connectionFactory) {
    CachingConnectionFactory cachingConnectionFactory = new Cachingconnectionfactory(connectionFactory);
    cachingConnectionFactory.afterPropertiesSet();

    JmsTemplate jmsTemplate = new JmsTemplate(cachingConnectionFactory);
    jmsTemplate.setDefaultDestinationName("customers");
    jmsTemplate.setreceiveTimeout(5000L);

    return jmsTemplate;
}
...
