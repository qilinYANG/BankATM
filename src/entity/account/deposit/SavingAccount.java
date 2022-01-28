package entity.account.deposit;

import serverUtil.AccountType;

/**
 * saving account entity
 */
public class SavingAccount extends AbsDepositAccount implements IDepositAccount
{
    private static final AccountType ACCOUNTTYPE = AccountType.SAVING;
    public SavingAccount(String accountId, String accountOwnerID) {
        super(accountId, ACCOUNTTYPE, accountOwnerID);
    }

    public SavingAccount(String accountOwnerID)
    {
        this(null, accountOwnerID);
    }
}
