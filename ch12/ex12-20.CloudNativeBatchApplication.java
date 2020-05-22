// listing 12-20. Spring Cloud Task's @EnableTask annotation

@EnableTask
@EnableRetry
@EnableBatchProcessing
@EnableDiscoveryClient(autoRegister = false)
public class CloudNativeBatchApplication {

    public static void main(String[] args) {
	SpringApplication.run(CloudNativeBatchApplication.class, args);
    }
}
