// Listing 12-17. LoadBalanced RestTemplate

...
@Bean
@LoadBalanced
public RestTemplate restTemplate() {
    return new RestTemplate();
}
...
