package service.account;

import java.util.Collection;
import java.util.PriorityQueue;

import entity.Balance;
import entity.Interest;
import entity.PurchasedStock;
import entity.Stock;
import entity.account.AccountCollection;
import entity.account.IAccount;
import serverUtil.AccountType;
import service.IService;

public interface IAccountService extends IService
{
    public Collection<AccountType> getCreatableAccountType(String userName);
    public Collection<IAccount>    getAllAccounts(String userName);
    public IAccount                getAccount(String accountNum);
    public void                    setAccountCollection(AccountCollection accountCollection);

    // create related 
    public String                  openCheckingAccount(String userName, double deposit);
    public String                  openSavingAccount(String userName, double deposit);
    public String                  openLoanAccount(String userName, double loanValue);
    public String                  openSecurityAccount(String userName);
    
    // retrieve & update related 
    // Checking & Saving
    public Collection<Balance>     getDepositBalances(String accountNumber);
    
    // Loan
    public Balance                 getLoanBalance(String accountNumber);
    
    // Security
    /** 
     *  return purchased stock in this account
     * @param accountNumber
     * @return Collection<Stock> 
     */
    public Collection<Stock>       getHoldingStocks(String accountNumber);
    
    /** 
     *  return purchased stock in this account
     * @return Collection<PriorityQueue<PurchasedStock>>
     */
    public Collection<PriorityQueue<PurchasedStock>>       getPurchasedSotcks(String accountNumber);
    
    /** 
     *  return current account value, cash + stock value
     * @param accountNumber
     * @return Double
     */
    public Balance                 getProtfolio(String accountNumber);
    
    /**
     *  Note: A realized profit or loss occurs when an investment is actually 
     *        sold for a higher or lower price than where it was purchased.
     *        
     *  return value in cash that is earned through selling stock 
     *  
     * @param accountNumber
     * @return Double
     */
    public Balance                 getRealizedProfit(String accountNumber);
    
    /**
     *  Note:An unrealized, or "paper" gain or loss is a theoretical profit 
     *       or deficit that exists on balance, resulting from an investment 
     *       that has not yet been sold for cash.
     *       
     *  return value that present how much stocks value increased/decreased
     *  
     * @param accountNumber
     * @return
     */
    public Balance                 getUnrealizedProfit(String accountNumber);

    // delete related
    public boolean                 closeAccount(String accountNumber);
    
    public Interest                getInterest(String accountNumber);
    
    public void                    reset();
    
}
