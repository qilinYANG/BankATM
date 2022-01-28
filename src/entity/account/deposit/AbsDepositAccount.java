package entity.account.deposit;

import entity.Balance;
import entity.account.AbsMultipleCurrencyAccount;
import serverUtil.AccountType;

/**
 * abstract class that have function for deposit
 */
public abstract class AbsDepositAccount extends AbsMultipleCurrencyAccount implements IDepositAccount
{

    public AbsDepositAccount(String accountId, AccountType accountType, String accountOwnerID) {
        super(accountId, accountType, accountOwnerID);
    }
    
    public void deposit(Balance balance)
    {
        Balance existingBalance = this.getBalance(balance.getCurrency());
        if(existingBalance != null)
        {
            existingBalance.setValue(existingBalance.getValue() + balance.getValue());
        }
        else
        {
            this.addBalance(balance);
        }
    }
    public boolean withdraw(Balance balance)
    {
        Balance existingBalance = this.getBalance(balance.getCurrency());
        if(existingBalance != null)
        {
            existingBalance.setValue(existingBalance.getValue() - balance.getValue());
            return true;
        }
        else {
            return false;
        }
    }
}
