package entity.account.deposit;

import entity.Balance;
import entity.account.IAccount;

/**
 * interface class that have function for deposit
 */
public interface IDepositAccount extends IAccount
{
    public void     deposit(Balance balance);
    public boolean  withdraw(Balance balance);
}
