// Listing 5-3. Customizing the TransactionManager

...
public class CustomBatchConfigurer extends DefaultBatchConfigurer {

    @Autowired
    @Qualifier("batchTransactionManager")
    private PlatformTransactionManager transactionManager;

    @Override
    public PlatformTransactionManager getTransactionManager() {
	return this.transactionManager;
    }
}
