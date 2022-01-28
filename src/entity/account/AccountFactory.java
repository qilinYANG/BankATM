package entity.account;

import entity.account.deposit.CheckingAccount;
import entity.account.deposit.SavingAccount;
import entity.account.loan.LoanAccount;
import entity.account.security.SecurityAccount;
import serverUtil.AccountType;

/**
 * account factory
 */
public class AccountFactory
{
    public static IAccount createAccount(AccountType type, String ownerID)
    {
        IAccount account = null;
        switch(type) {
        case CHECKING:
            account = new CheckingAccount(ownerID);
            break;
        case SAVING:
            account = new SavingAccount(ownerID);
            break;
        case LOAN:
            account = new LoanAccount(ownerID);
            break;
        case SECURITY:
            account = new SecurityAccount(ownerID);
            break;
        default: 
            break;
        }
        return account;
    }
}
