package entity.account;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Currency;

import entity.Balance;
import serverUtil.AccountType;

/**
 * account that has multiple currency support
 */
public abstract class AbsMultipleCurrencyAccount extends AbsAccount implements IMultipleCurrencyAccount
{
    private Collection<Balance> balanceCollection;

    public AbsMultipleCurrencyAccount(String accountId, AccountType accountType, String accountOwnerID) {
        super(accountId, accountType, accountOwnerID);
        this.setBalanceCollection(new ArrayList<Balance>());
    }

    public Collection<Balance> getAllBalance()
    {
        return balanceCollection;
    }
    
    public Balance getBalance(Currency currency)
    {
        for(Balance balance : balanceCollection)
        {
            if(balance.getCurrency().equals(currency))
            {
                return balance;
            }
        }
        return null;
    }
    
    public void addBalance(Balance bal)
    {
        boolean isBalanceExist = false;
        for(Balance balance : balanceCollection)
        {
            if(balance.getCurrency().equals(bal.getCurrency()))
            {
                balance.setValue(balance.getValue() + bal.getValue());
                isBalanceExist = true;
            }
        }
        
        if(!isBalanceExist) {
            balanceCollection.add(bal);
        }
    }
    
    public boolean subtractBalance(Balance bal)
    {
        boolean isBalanceExist = false;
        for(Balance balance : balanceCollection)
        {
            if(balance.getCurrency().equals(bal.getCurrency()))
            {
                if(balance.getValue() < bal.getValue())
                {
                    break;
                }
                balance.setValue(balance.getValue() - bal.getValue());
                isBalanceExist = true;
            }
        }

        return isBalanceExist;
    }

    public void setBalanceCollection(Collection<Balance> balanceCollection)
    {
        this.balanceCollection = balanceCollection;
    }

}
