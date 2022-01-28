package entity.account.deposit;

import serverUtil.AccountType;

/**
 * checking account entity
 */
public class CheckingAccount extends AbsDepositAccount implements IDepositAccount
{
    private static final AccountType ACCOUNTTYPE = AccountType.CHECKING;
    public CheckingAccount(String accountId, String accountOwnerID) {
        super(accountId, ACCOUNTTYPE, accountOwnerID);
    }
    public CheckingAccount(String accountOwnerID) {
        this(null, accountOwnerID);
    }


}
