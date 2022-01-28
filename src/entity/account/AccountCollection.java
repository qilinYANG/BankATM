package entity.account;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import serverUtil.AccountType;

/**
 * Account collection with iteration ability
 */
public class AccountCollection implements Iterable<IAccount>
{
    private Map<AccountType, List<IAccount>> accountCollection;
    
    public AccountCollection()
    {
        this.setAccountCollection(new LinkedHashMap<AccountType, List<IAccount>>());
    }
    
    public void addAccount(IAccount account)
    {
        if(!this.getAccountCollection().containsKey(account.getAccountType()))
        {
            this.getAccountCollection().put(account.getAccountType(), new LinkedList<IAccount>());
        }
        this.getAccountCollection().get(account.getAccountType()).add(account);
    }
    
    public IAccount getAccount(AccountType type)
    {
    	IAccount acc;
    	if(!containsAccountType(type))
    	{
    		acc = null;
    	}
    	List<IAccount> accs = this.getAccountCollection().get(type);
    	if(accs.isEmpty())
    	{
    		acc = null;
    	}
    	else
    	{
    		acc = accs.get(0);
    	}
    	return acc;
    }
    
    public IAccount getAccount(String accountNumber)
    {
        IAccount acc = null;
        for(List<IAccount> list : accountCollection.values())
        {
            IAccount temp = list.get(0);
            if(temp.getAccountNumber().equals(accountNumber))
            {
                acc = temp;
            }
        }
        return acc;
    }
    
    public IAccount getCheckingAccount()
    {
    	return getAccount(AccountType.CHECKING);
    }
    
    public IAccount getSavingAccount()
    {
    	return getAccount(AccountType.SAVING);
    }
    
    public IAccount getLoanAccount()
    {
    	return getAccount(AccountType.LOAN);
    }
    
    public IAccount getSecurityAccount()
    {
    	return getAccount(AccountType.SECURITY);
    }
    
    public boolean containsAccountType(AccountType type)
    {
        return this.getAccountCollection().containsKey(type);
    }
    
    public boolean containsCheckingAccount()
    {
        return this.containsAccountType(AccountType.CHECKING);
    }
    
    public boolean containsSavingAccount()
    {
        return this.containsAccountType(AccountType.SAVING);
    }
    
    public boolean containsLoanAccount()
    {
        return this.containsAccountType(AccountType.LOAN);
    }
    
    public boolean containsSecurityAccount()
    {
        return this.containsAccountType(AccountType.SECURITY);
    }

    public Map<AccountType, List<IAccount>> getAccountCollection()
    {
        return accountCollection;
    }
    
    public List<IAccount> getAllAccounts()
    {
        LinkedList<IAccount>  res = new LinkedList<IAccount>();
        for(List<IAccount> l : this.getAccountCollection().values())
        {
            res.add(l.get(0));
        }
        return res;
    }

    public void setAccountCollection(Map<AccountType, List<IAccount>> accountCollection)
    {
        this.accountCollection = accountCollection;
    }

	@Override
	public Iterator<IAccount> iterator() {
	    Iterator<IAccount> ite = new Iterator<IAccount>()
	    {
	        Iterator<List<IAccount>> accountsListIte = getAccountCollection().values().iterator();
            @Override
            public boolean hasNext()
            {
                return accountsListIte.hasNext();
            }

            @Override
            public IAccount next()
            {
                return accountsListIte.next().get(0);
            }
	    };
		return ite;
	}

    
}
