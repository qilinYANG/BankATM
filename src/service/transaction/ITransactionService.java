package service.transaction;

import java.util.Collection;
import java.util.Currency;

import entity.Balance;
import entity.PurchasedStock;
import entity.Stock;
import entity.transaction.ITransaction;
import service.IService;

/**
 * interface for Transaction related function
 */
public interface ITransactionService extends IService
{
    public Collection<Currency>     getSupportedCurrencyTypes();
    public boolean                  transfer(String originAccountNum, String destinationAccountNum, Balance balance);
    public boolean                  deposit(String accountNumber, Balance balance);
    public boolean                  withDraw(String accountNumber, Balance balance);
    
    public boolean                  addLoan(String accountNumber, Balance balance);
    public boolean                  payLoan(String accountNumber, Balance balance);
    
    public boolean                  purchaseStock(String accountNumber, Stock stock, int cnt);
    public boolean                  sellStock(String accountNumber, Stock stock, int cnt);
    public boolean                  logTransaction(ITransaction transaction);
    public Collection<Stock>        getMarketStocks();
    public Collection<ITransaction> getTransactionHistory(String accountNumber);
    public Collection<ITransaction> getTransactionHistory(int cnt);
}
