package entity.account.loan;

import entity.account.AbsSingleCurrencyAccount;
import serverUtil.AccountType;

/**
 * loan account entity
 */
public class LoanAccount extends AbsSingleCurrencyAccount implements ILoanAccount
{
    private static final AccountType ACCOUNTTYPE = AccountType.LOAN;
    public LoanAccount(String accountId, String accountOwnerID) {
        super(accountId, ACCOUNTTYPE, accountOwnerID);
    }
    public LoanAccount(String accountOwnerID) {
        this(null, accountOwnerID);
    }
}
