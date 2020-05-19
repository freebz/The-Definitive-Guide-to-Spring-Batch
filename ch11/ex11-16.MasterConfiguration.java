// Listing 11-16. MasterConfiguration and Outbound Flow

...
@Configuration
@Profile("master")
@EnableBatchIntegration
public class MasterConfiguration {

    private final JobBuilderFactory jobBuilderFactory;

    private final RemotePartitioningMasterStepBuilderFactory masterStepBuilderFactory;

    public MasterConfiguration(JobBuilderFactory jobBuilderFactory,
		    RemotePartitioningMasterStepBuilderFactory masterStepBuilderFactory) {

	this.jobBuilderFactory = jobBuilderFactory;
	this.masterStepBuilderFactory = masterStepBuilderFactory;
    }

    /*
     * Configure outbound flow (requests going to workers)
     */
    @Bean
    public DirectChannel requests() {
	return new DirectChannel();
    }

    @Bean
    public IntegrationFlow outboundFlow(AmqpTemplate amqpTemplate) {
	return IntegrationFlows.from(requests())
	                .handle(Amqp.outboundAdapter(amqpTemplate)
				        .routingKey("requests"))
	                .get();
    }
...
