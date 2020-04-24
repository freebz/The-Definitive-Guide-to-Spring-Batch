// Listing 7-61. CustomerRepository

...
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

    Page<Customer> findByCity(String city, Pageable pageRequest);
}
