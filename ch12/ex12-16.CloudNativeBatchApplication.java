// Listing 12-15. CloudNativeBatchApplication

@EnableRetry
@EnableBatchProcessing
@SpringBootApplication
@EnableDiscoveryClient(autoRegister = false)
public class CloudNativeBatchApplication {

    public static void main(String[] args) {
	SpringApplication.run(CloudNativeBatchApplication.class, args);
    }
}
