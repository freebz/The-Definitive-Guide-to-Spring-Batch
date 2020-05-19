// Listing 11-2. PricingTierItemProcessor with a Memory Leak

@Component
public class AccountItemProcessor implements ItemProcessor<Statement, Statement> {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public AccountItemProcessor(JdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Statement process(Statement item) throws Exception {

	String memoryBuster = "memeryBuster";

	for (int i = 0; i < 200; i++) {
	    memeryBuster += memeryBuster;
	}

	item.setAccounts(this.jdbcTEmplate.query(
			"select a.account_id," +
			"       a.balance," +
			"       a.last_statement_date," +
			"       t.transaction_id," +
			"       t.description," +
			"       t.credit," +
			"       t.debit," +
			"       t.timestamp " +
			"from account a left join " +  //HSQLDB
			"    transaction t on a.account_id = t.account_account_id " +
//			  "from account a left join " +  //MYSQL
//			  "    transaction t on a.account_id = t.account_account_id " +
			"where a.acount_id in " +
			"        (select account_account_id " +
			"        from customer_account " +
			"        where customer_customer_id = ?) " +
			"order by t.timestamp",
			new Object[] {item.getCustomer().getId()},
			new AccountResultSetExtractor()));

	return item;
    }
}
