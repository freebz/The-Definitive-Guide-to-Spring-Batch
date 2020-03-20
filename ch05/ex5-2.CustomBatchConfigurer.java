// Listing 5-2. Customizing the JobRepository

...
public class CustomBatchConfigurer extends DefaultBatchConfigurer {

    @Autowired
    @Qualifier("repositoryDataSource")
    private DataSource dataSource;

    @Override
    protected JobRepository createJobRepository() throws Exception {
	JobRepositoryFactoryBean factoryBean = new JobRepositoryFactoryBean();

	factoryBean.setDatabaseType(DatabaseType.MYSQL.getProductName());
	factoryBean.setTablePrefix("FOO_");
	factoryBean.setIsolationLevelForCreate("ISOLATION_REPEATEABLE_READ");
	factoryBean.setDataSource(this.dataSource);

	factoryBean.afterPropertiesSet();

	return factoryBean.getObject();
    }
}
