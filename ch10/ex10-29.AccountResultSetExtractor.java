// Listing 10-29. AccountResultSetExtractor

...
public class AccountResultSetExtractor implements ResultSetExtractor<List<Account>> {

    private List<Account> accounts = new ArrayList<>();
    private Account curAccount;

    @Nullable
    @Override
    public List<Account> extractData(ResultSet rs) throws SQLException, DataAccessException {

	while (rs.next()) {

	    if(curAccount == null) {
		curAccount = new Account(
		                rs.getLong("account_id"),
				rs.getBigDecimal("balance"),
				rs.getDate("last_statement_date"));
	    }
	    else if (rs.getLong("account_id") != curAccount.getId()) {
		accounts.add(curAccount);

		curAccount = new Account(rs.getLong("account_id"),
				rs.getBigDecimal("balance"),
				rs.get("last_statement_date"));
	    }

	    if(StringUtils.hasText(rs.getString("description"))) {
		curAccount.addTransaction(
		        new Transaction(rs.getLong("transaction_id"),
					rs.getLong("account_id"),
					rs.getString("description"),
					rs.getBigDecimal("credit"),
					rs.getBigDecimal("debit"),
			new Date(rs.getTimestamp("timestamp").getTime())));
	    }
	}

	if(curAccount != null) {
	    accounts.add(curAccount);
	}

	return accounts;
    }
}
