// Listing 5-4. Customizing the JobExplorer

...
public class CustomBatchConfigurer extends DefaultBatchConfigurer {

    @Autowired
    @Qualifier("batchTransactionManager")
    private DataSource dataSource;

    @Override
    protected JobExplorer createJobExplorer() throws Exception {
	JobExplorerFactoryBean factoryBean = new JobExplorerFactoryBean();

	factoryBean.setDataSource(this.dataSource);
	factoryBean.setTablePrefix("FOO_");

	factoryBean.afterPropertiesSet();

	return factoryBean.getObject();
    }
}
