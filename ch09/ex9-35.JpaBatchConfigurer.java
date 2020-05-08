// Listing 9-35. JpaBatchConfigurer.java

...
@Component
public class JpaBatchConfigurer implements BatchConfigurer {

    private DataSource dataSource;
    private EntityManagerFactory entityManagerFactory;
    private JobRepository jobRepository;
    private PlatformTransactionManager transactionManager;
    private JobLauncher jobLauncher;
    private JobExplorer jobExplorer;

    public JpaBatchConfigurer(DataSource dataSource,
        EntityManagerFactory entityManagerFactory) {
	this.dataSource = dataSource;
	this.entityManagerFactory = entityManagerFactory;
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
	return this.jobLauncher;
    }

    @Override
    public JobExplorer getJobExplorer() throws Exception {
	return this.jobExplorer;
    }

    @PostConstruct
    public void initialize() {

	try {
	    JpaTransactionManager transactionManager =
		    new JpaTransactionManager(entityManagerFactory);
	    transactionManager.afterPropertiesSet();

	    this.transactionManager = transactionManager;

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
	JobExplorerFactoryBean jobExplorerFactoryBean =
	        new JobExplorerFactoryBean();

	jobExplorerFactoryBean.setDataSource(this.dataSource);
	jobExplorerFactoryBean.afterPropertiesSet();

	return jobExplorerFactoryBean.getObject();
    }

    private JobRepository createJobRepository() throws Exception {
	JobRepositoryFactoryBean jobRepositoryFactoryBean =
	        new JobRepositoryFactoryBean();

	jobRepositoryFactoryBean.setDataSource(this.dataSource);
	jobRepositoryFactoryBean.setTransactionManager(this.transactionManager);
	jobRepositoryFactoryBean.afterPropertiesSet();

	return jobRepositoryFactoryBean.getObject();
    }
}
