package service.transaction;

import java.util.Collection;
import java.util.Currency;

import entity.Balance;
import entity.PurchasedStock;
import entity.Stock;
import entity.transaction.ITransaction;
import service.ServiceManager;
/**
 * proxy impl for charging fees the call real method in real impl
 */
public class ProxyTransactionService implements ITransactionService
{

    @Override
    public boolean isServiceAvailable()
    {
        return ServiceManager.getTransactionService().isServiceAvailable();
    }

    @Override
    public Collection<Currency> getSupportedCurrencyTypes()
    {
        return ServiceManager.getTransactionService().getSupportedCurrencyTypes();
    }

    @Override
    public boolean transfer(String originAccountNum, String destinationAccountNum, Balance balance)
    {
        boolean res = ServiceManager.getTransactionService().transfer(originAccountNum, destinationAccountNum, balance);
        ServiceManager.getManagerReviewService().chargeFee(destinationAccountNum);
        return res;
    }

    @Override
    public boolean deposit(String accountNumber, Balance balance)
    {
        boolean res = ServiceManager.getTransactionService().deposit(accountNumber, balance);
        ServiceManager.getManagerReviewService().chargeFee(accountNumber);
        return res;
    }

    @Override
    public boolean withDraw(String accountNumber, Balance balance)
    {
        boolean res = ServiceManager.getTransactionService().withDraw(accountNumber, balance);
        ServiceManager.getManagerReviewService().chargeFee(accountNumber);
        return res;
    }

    @Override
    public boolean addLoan(String accountNumber, Balance balance)
    {
        boolean res = ServiceManager.getTransactionService().addLoan(accountNumber, balance);
        ServiceManager.getManagerReviewService().chargeFee(accountNumber);
        return res;
    }

    @Override
    public boolean payLoan(String accountNumber, Balance balance)
    {
        return ServiceManager.getTransactionService().payLoan(accountNumber, balance);
    }

    @Override
    public boolean purchaseStock(String accountNumber, Stock stock, int cnt)
    {
        boolean res = ServiceManager.getTransactionService().purchaseStock(accountNumber, stock, cnt);
        return res;
    }

    @Override
    public boolean sellStock(String accountNumber, Stock stock, int cnt)
    {
        boolean res = ServiceManager.getTransactionService().sellStock(accountNumber, stock, cnt);
        return res;
    }

    @Override
    public boolean logTransaction(ITransaction transaction)
    {
        return ServiceManager.getTransactionService().logTransaction(transaction);
    }

    @Override
    public Collection<Stock> getMarketStocks()
    {
        return ServiceManager.getTransactionService().getMarketStocks();
    }

    @Override
    public Collection<ITransaction> getTransactionHistory(String accountNumber)
    {
        return ServiceManager.getTransactionService().getTransactionHistory(accountNumber);
    }

    @Override
    public Collection<ITransaction> getTransactionHistory(int cnt)
    {
        return ServiceManager.getTransactionService().getTransactionHistory(cnt);
    }

}
