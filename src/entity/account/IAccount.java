package entity.account;

import java.util.Collection;
import java.util.Currency;
import java.util.Date;

import entity.Balance;
import serverUtil.AccountType;

/**
 * Account interface
 */
public interface IAccount
{
    public String              getAccountNumber();
    public void                setAccountNumber(String accountId);
    public AccountType         getAccountType();
    public String              getAccountOwner();
    public Collection<Balance> getAllBalance();
    public Balance             getBalance(Currency currency);
    public void                addBalance(Balance bal);
    public boolean             subtractBalance(Balance bal);
    public Date                getCreateDate();
    public void                setCreateDate(Date createDate);
    public boolean             isActive();
    public void                setActive(boolean isActive);
}
