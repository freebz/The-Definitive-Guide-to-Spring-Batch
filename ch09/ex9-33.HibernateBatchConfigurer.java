// Listing 9-33. HibernateBatchConfigurer.java

...
@Component
public class HibernateBatchConfigurer implements BatchConfigurer {

    private DataSource dataSource;
    private SessionFactory sessionFactory;
    private JobRepository jobRepository;
    private PlatformTransactionManager transactionManager;
    private JobLauncher jobLauncher;
    private JobExplorer jobExplorer;

    public HibernateBatchConfigurer(DataSource dataSource,
        EntityManagerFactory entityManagerFactory) {

	this.dataSource = dataSource;
	this.sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
    }

    @Override
    public JobRepository getJobRepository() throws Exception {
	return this.jobRepository;
    }

    @Override
    public PlatformTransactionManager getTransactionManager() throws Exception {
	return this.transactionManager;
    }

    @Override
    public JobLauncher getJobLauncher() throws Exception {
	return this.joblauncher;
    }

    @Override
    public JobExplorer getJobExplorer() throws Exception {
	return this.jobExplorer;
    }

    @PostConstruct
    public void initialize() {

	try {
	    HibernateTransactionManager transactionManager =
	    new HibernateTransactionManager(sessionFactory);
	    transactionManager.afterPropertiesSet();

	    this.jobRepository = createJobRepository();
	    this.jobExplorer = createJobExplorer();
	    this.jobLauncher = createJobLauncher();

	}
	catch (Exception e) {
	    throw new BatchConfigurationException(e);
	}
    }

    private JobLauncher createJobLauncher() throws Exception {
	SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
	jobLauncher.setJobRepository(this.jobRepository);
	jobLauncher.afterPropertiesSet();

	return jobLauncher;
    }

    private JobExplorer createJobExplorer() throws Exception {
	JobExplorerFactoryBean jobExplorerFactoryBean = new JobExplorerFactoryBean();

	jobExplorerFactoryBean.setDataSource(this.dataSource);
	jobExplorerFactoryBean.afterPropertiesSet();

	return jobExplorerFactoryBean.getObject();
    }

    private JobRepository createJobRepository() throws Exception {
	JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositpryFactoryBean();

	jobRepositoryFactoryBean.setDataSource(this.dataSource);
	jobRepositoryFactoryBean.setTransactionManager(this.transactionManager);
	jobRepositoryFactoryBean.afterPropertiesSet();

	return jobRepositoryFactoryBean.getObject();
    }
}
