// Listing 6-16 TransactionDaoSupport

...
public class TransactionDaoSupport extends JdbcTemplate implements TransactionDao {

    public TransactionDaoSupport(DataSource dataSource) {
	super(dataSource);
    }

    @SuppressWarnings("unchecked")
    public List<Transaction> getTransactionsByAccountNumber(String accountNumber) {
	return query(
	             "select t.id, t.timestamp, t.amount " +
		                  "from transaction t inner join account_summary a on " +
		                  "a.id = t.account_summary_id " +
		                  "where a.account_number = ?",
		     new Object[] { accountNumber },
		     (rs, rowNum) -> {
			 Transaction trans = new Transaction();
			 trans.setAmount(rs.getDouble("amount"));
			 trans.setTimestamp(rs.getDate("timestamp"));
			 return trans;
		     }
 	);
    }
}
