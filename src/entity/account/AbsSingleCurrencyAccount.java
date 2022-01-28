package entity.account;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Currency;

import entity.Balance;
import serverUtil.AccountType;

/**
 * database that has only one Currency 
 */
public abstract class AbsSingleCurrencyAccount extends AbsAccount implements ISingleCurrencyAccount
{
    private Balance balance;
    
    public AbsSingleCurrencyAccount(String accountId, AccountType accountType, String accountOwnerID) {
        super(accountId, accountType, accountOwnerID);
    }
    
    public Collection<Balance> getAllBalance()
    {
        Collection<Balance> res = new ArrayList<Balance>();
        res.add(this.getBalance());
        return res;
    }
    public Balance getBalance(Currency currency)
    {
        if(!currency.equals(this.getBalance().getCurrency()))
        {
            return null;
        }
        else
        {
            return this.getBalance();
        }
    }
    
    public void addBalance(Balance bal)
    {
        if(this.getBalance() == null)
        {
            this.setBalance(new Balance(bal.getCurrency(), bal.getValue()));
        }
        else
        {
            if(this.getBalance().getCurrency().equals(bal.getCurrency()))
            {
                this.getBalance().setValue(balance.getValue() + bal.getValue());
            }
            else
            {
                System.out.println("balance that being added cannot match local balance's currency");
                return;
            }
        }
    }
    
    public boolean subtractBalance(Balance bal)
    {
        if(this.getBalance() == null)
        {
            return false;
        }
        else
        {
            if(balance.getCurrency().equals(bal.getCurrency()))
            {
                if(balance.getValue() < bal.getValue())
                {
                    return false;
                }
                balance.setValue(balance.getValue() - bal.getValue());
                return true;
            }
            else
            {
                return false;
            }
        }
    }
    
    public Balance getBalance()
    {
        return balance;
    }
    public void setBalance(Balance balance)
    {
        this.balance = balance;
    }
}
