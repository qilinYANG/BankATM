package service.database;

import java.util.Collection;
import java.util.Currency;

import entity.Balance;
import entity.Fee;
import entity.Interest;
import entity.PurchasedStock;
import entity.Stock;
import entity.account.IAccount;
import entity.account.deposit.CheckingAccount;
import entity.account.deposit.SavingAccount;
import entity.account.loan.LoanAccount;
import entity.account.security.SecurityAccount;
import entity.transaction.ITransaction;
import entity.user.Customer;
import service.IService;

public interface IDatabaseService extends IService
{

    // account
    public String create(IAccount account);
    
    public IAccount getUserAccount(String accountNumber);
    
    public Collection<IAccount> getUserAccounts(String userID);
    
    // balance 
    public String create(String accountNumber, Balance balance);
    
    public Collection<Balance> getAccountBalances(String accountNumber);
    
    public boolean updateAccountBalances(String accountNumber, Balance balance);

    // customer
    public String create(Customer cust);
    
    public Customer getCustomer(String userName, String password);
    
    public boolean checkUserNameExist(String userName);
    
    // transaction
    public String create(ITransaction transaction);

    public Collection<Currency> getSupportedCurrencyTypes();

    public String create(String accountNumber, PurchasedStock ps);

    public boolean remove(String accountNumber, PurchasedStock ps);

    public boolean update(String accountNumber, PurchasedStock ps);

    public Collection<Stock> getMarketStocks();

    public Collection<ITransaction> getTransactionHistory(String accountNumber, int record);
    
    public Collection<ITransaction> getTransactionHistory(int cnt);
    
    // interest and fee
    public boolean updateInterestRate(double rate);

    public Interest getInterestRate();

    public boolean updateStockPrice(Stock stock, double price);
    
    public Collection<PurchasedStock> getPurchasedStock(String accountNumber);

    public Fee getFee(String name);

    public boolean updateFee(double value);

    public Collection<Customer> getAllCustomer();

    public String getManagerAccount();

    public boolean checkIsAdmin(String userName);
}
