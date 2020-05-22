// Listing 12-1. CloudNativeBatchApplication.java

...
@EnableBatchProcessing
@SpringBootApplication
public class CloudNativeBatchApplication {

    public static void main(String[] args) {
	SpringApplication.run(CloudNativeBatchApplication.class, args);
    }
}
