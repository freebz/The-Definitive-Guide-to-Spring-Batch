// Listing 11-17. Inbound Flow

...
    /*
     * Configure inbound flow (replies coming from workers)
     */
    @Bean
    public DirectChannel replies() {
	return new DirectChannel();
    }

    @Bean
    public IntegrationFlow inboundFlow(ConnectionFactory connectionFactory) {
	return IntegrationFlows
	                .from(Amqp.inboundAdapter(connectionFactory, "replies"))
	                .channel(replies())
	                .get();
    }
...
