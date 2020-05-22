// Listing 12-14. Adding @EnableDiscoveryClient

...
@EnableDiscoveryClient
@SpringBootApplication
public class RestServiceApplication {

    public static void main(String[] args) {
	SpringApplication.run(RestServiceApplication.class, args);
    }
}
