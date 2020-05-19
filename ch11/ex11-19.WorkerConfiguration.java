// Listing 11-19. WorkerConfiguration and inboundFlow

...
@Configuration
@Profile("!master")
@EnableBatchIntegration
public class WorkerConfiguration {

    private final RemotePartitioningMasterStepBuilderFactory
	    workerStepBuilderFactory;

    public WorkerConfiguration(
	RemotePartitioningMasterStepBuilderFactory workerStepBuilderFactory) {

	this.workerStepBuilderFactory = workerStepBuilderFactory;
    }

    /*
     * Configure inbound flow (requests coming from the master)
     */
    @Bean
    public DirectChannel requests() {
	return new DirectChannel();
    }

    @Bean
    public IntegrationFlow inboundFlow(ConnectionFactory connectionFactory) {
	return IntegrationFlows
	                .from(Amqp.inboundAdapter(connectionFactory, "requests"))
	                .channel(requests())
	                .get();
    }

    @Bean
    public DirectChannel replies() {
	return new DirectChannel();
    }
...
