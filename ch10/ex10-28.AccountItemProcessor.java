// Listing 10-28. AccountItemProcessor

...
@Component
public class AccountItemProcessor implements ItemProcessor<Statement, Statement> {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public AccountItemProcessor(JdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Statement process(Statement item) throws Exception {

	item.setAccounts(this.jdbcTemplate.query("select a .account_id," +
			"       a.balance," +
			"       a.last_statement_date," +
			"       t.transaction_id," +
			"       t.description," +
			"       t.credit," +
			"       t.debit," +
			"       t.timestamp " +
			"from account a left join " + //HSQLDB
			"    transaction t on a.account_id = t.account_account_id " +
			"where a.account_id in " +
			"        (select account_account_id " +
			"        from customer_account " +
			"        where customer_customer_id = ?) " +
			"order by t.timestamp",
			new Object[] {item.getCustomer().getId()},
			new AccountResultSetExtractor()));
	return item;
    }
}
