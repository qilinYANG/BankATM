package service.account;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Currency;
import java.util.HashSet;
import java.util.PriorityQueue;

import entity.Balance;
import entity.Interest;
import entity.PurchasedStock;
import entity.Stock;
import entity.account.AccountCollection;
import entity.account.AccountFactory;
import entity.account.IAccount;
import entity.account.deposit.CheckingAccount;
import entity.account.deposit.SavingAccount;
import entity.account.loan.LoanAccount;
import entity.account.security.ISecurityAccount;
import entity.account.security.SecurityAccount;
import serverUtil.AccountType;
import service.ServiceManager;

/**
 * account service 
 */
public class AccountService implements IAccountService
{
    private static AccountService accountService = null;
    
    private AccountCollection accountCollection;
    
    private static final int RICHLINE = 5000; // USD
    
    private AccountService() {
    	this.setAccountCollection(new AccountCollection());
    }
    
    public static synchronized AccountService getInstance()
    {
        if(accountService == null)
        {
            accountService = new AccountService();
        }
        return accountService;
    }
    
    public boolean isServiceAvailable()
    {
        return getInstance().getAccountCollection() != null;
    }
    
    @Override
    public Collection<AccountType> getCreatableAccountType(String userID)
    {
        if(!isServiceAvailable())
        {
            return null;
        }
        
        Collection<AccountType> availableAccountType = new HashSet<AccountType>(Arrays.asList(AccountType.values()));
        
        for(IAccount acc : accountCollection)
        {
            AccountType type = acc.getAccountType();
            if(availableAccountType.contains(type))
            {
                availableAccountType.remove(type);
            }
        }
        
        // check if qualified create Security Account
        if(!this.getAccountCollection().containsSavingAccount() || 
           this.getAccountCollection().getSavingAccount().getBalance(Currency.getInstance("USD")).getValue() < RICHLINE)
        {
            availableAccountType.remove(AccountType.SECURITY);
        }
        return availableAccountType;
    }

    @Override
    public Collection<IAccount> getAllAccounts(String userID)
    {
        if(!isServiceAvailable())
        {
            return null;
        }
        
        return this.getAccountCollection().getAllAccounts();
    }
    
    @Override
    public IAccount getAccount(String accountNum)
    {
        return this.getAccountCollection().getAccount(accountNum);
    }

    @Override
    public String openCheckingAccount(String userID, double deposit)
    {
        if(!isServiceAvailable() || this.getAccountCollection().containsCheckingAccount())
        {
            return null;
        }
        
        CheckingAccount checking  = (CheckingAccount)AccountFactory.createAccount(AccountType.CHECKING, userID);
        String          accountID = ServiceManager.getDatabaseService().create(checking);
        checking.setAccountNumber(accountID);
        this.getAccountCollection().addAccount(checking);
        return checking.getAccountNumber();
    }

    @Override
    public String openSavingAccount(String userID, double deposit)
    {
        if(!isServiceAvailable() || this.getAccountCollection().containsSavingAccount())
        {
            return null;
        }
        
        SavingAccount saving    = (SavingAccount)AccountFactory.createAccount(AccountType.SAVING, userID);
        String        accountID = ServiceManager.getDatabaseService().create(saving);
        saving.setAccountNumber(accountID);
        this.getAccountCollection().addAccount(saving);
        return saving.getAccountNumber();
    }

    @Override
    public String openLoanAccount(String userID, double loanValue)
    {
        if(!isServiceAvailable()  || this.getAccountCollection().containsLoanAccount())
        {
            return null;
        }
        
        LoanAccount loan      = (LoanAccount)AccountFactory.createAccount(AccountType.LOAN, userID);
        String      accountID = ServiceManager.getDatabaseService().create(loan);
        loan.setAccountNumber(accountID);
        this.getAccountCollection().addAccount(loan);
        return loan.getAccountNumber();
    }

    @Override
    public String openSecurityAccount(String userID)
    {
        if(!isServiceAvailable()  || this.getAccountCollection().containsSecurityAccount())
        {
            return null;
        }
        
        SecurityAccount security  = (SecurityAccount)AccountFactory.createAccount(AccountType.SECURITY, userID);
        String          accountID = ServiceManager.getDatabaseService().create(security);
        security.setAccountNumber(accountID);
        this.getAccountCollection().addAccount(security);
        return security.getAccountNumber();
    }

    @Override
    public Collection<Balance> getDepositBalances(String accountNumber)
    {
        if(!isServiceAvailable())
        {
            return null;
        }
        IAccount account = this.getAccountCollection().getAccount(accountNumber);
        Collection<Balance> balances = null;
        if(AccountType.isDepositAccount(account))
        {
            balances = account.getAllBalance();
        }
        return balances;
    }
    
    @Override
    public Balance getLoanBalance(String accountNumber)
    {
        IAccount account = this.getAccountCollection().getAccount(accountNumber);
        Balance  balance = null;
        if(account.getAccountType() == AccountType.LOAN)
        {
            balance = (account).getBalance(Currency.getInstance("USD"));
        }
        return balance;
    }

    @Override
    public Collection<Stock> getHoldingStocks(String accountNumber)
    {
        IAccount          account = this.getAccountCollection().getAccount(accountNumber);
        Collection<Stock> stocks  = new ArrayList<Stock>();

        if(account.getAccountType() == AccountType.SECURITY)
        {
            stocks = new ArrayList<Stock>(((ISecurityAccount)account).getHoldingStocks());
        }
        return stocks;
    }
    
    @Override
    public Collection<PriorityQueue<PurchasedStock>> getPurchasedSotcks(String accountNumber)
    {
        IAccount          account = this.getAccountCollection().getAccount(accountNumber);
        Collection<PriorityQueue<PurchasedStock>> stocks  = new ArrayList<>();

        if(account.getAccountType() == AccountType.SECURITY)
        {
            stocks = new ArrayList<PriorityQueue<PurchasedStock>>(((ISecurityAccount)account).getPurchasedStocks());
        }
        return stocks;
    }

    @Override
    public Balance getProtfolio(String accountNumber)
    {
        IAccount account = this.getAccountCollection().getAccount(accountNumber);
        Balance  protfolio = null;
        if(account.getAccountType() == AccountType.SECURITY)
        {
            protfolio = ((ISecurityAccount)account).getProtfolio();
        }
        return protfolio;
    }

    @Override
    public Balance getRealizedProfit(String accountNumber)
    {
        IAccount account = this.getAccountCollection().getAccount(accountNumber);
        Balance  rp      = null;
        if(account.getAccountType() == AccountType.SECURITY)
        {
            rp = ((ISecurityAccount)account).getRealizedProfit();
        }
        return rp;
    }

    @Override
    public Balance getUnrealizedProfit(String accountNumber)
    {
        IAccount account = this.getAccountCollection().getAccount(accountNumber);
        Balance  up      = null;
        if(account.getAccountType() == AccountType.SECURITY)
        {
            up = ((ISecurityAccount)account).getUnrealizedProfit();
        }
        return up;
    }

    @Override
    public boolean closeAccount(String accountNumber)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Interest getInterest(String accountNumber)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
	public AccountCollection getAccountCollection() {
		return this.accountCollection;
	}
	
	public void setAccountCollection(AccountCollection accountCollection) {
		this.accountCollection = accountCollection;
	}

    @Override
    public void reset()
    {
        this.accountCollection = null;
    }
}
