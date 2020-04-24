// Listing 7-51. HIbernateBatchConfigurer

...
@Component
public class HibernateBatchConfigurer extends DefaultBatchConfigurer {

    private DataSource dataSource;
    private SessionFactory sessionFactory;
    private PlatformTransactionManager transactionManager;

    public HibernateBatchConfigurer(DataSource dataSource,
		  EntityManagerFactory entityManagerFactory) {

	super(dataSource);
	this.dataSource = dataSource;
	this.sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
	this.transactionManager = new HibernateTransactionManager(this.sessionFactory);
    }

    @Override
    public PlatformTransactionManager getTransactionManager() {
	return this.transactionManager;
    }
}
