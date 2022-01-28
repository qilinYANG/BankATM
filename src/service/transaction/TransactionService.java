package service.transaction;

import java.util.Collection;
import java.util.Currency;
import java.util.Date;
import java.util.List;

import entity.Balance;
import entity.PurchasedStock;
import entity.Stock;
import entity.StockTable;
import entity.account.IAccount;
import entity.account.security.ISecurityAccount;
import entity.transaction.Deposit;
import entity.transaction.ITransaction;
import entity.transaction.StockBuy;
import entity.transaction.StockSell;
import entity.transaction.Transfer;
import entity.transaction.Withdraw;
import service.ServiceManager;
import service.account.AccountService;

/**
 * Transaction service impl
 */
public class TransactionService implements ITransactionService
{
    private static TransactionService transactionService = null;
    private TransactionService(){}
    
    public static synchronized TransactionService getInstance()
    {
        if(transactionService == null)
        {
            transactionService = new TransactionService();
        }
        return transactionService;
    }
    
    @Override
    public boolean logTransaction(ITransaction transaction)
    {
        return ServiceManager.getDatabaseService().create(transaction) != null;
    }

    @Override
    public boolean isServiceAvailable()
    {
        // check dependency service available
        return ServiceManager.getAccountService().isServiceAvailable();
    }

    @Override
    public boolean transfer(String originAccountNum, String destinationAccountNum, Balance balance)
    {
        boolean withDrawAction = this.withDraw(originAccountNum, balance);
        boolean depositAction  = this.deposit(destinationAccountNum, balance);
        boolean logAction      = this.logTransaction(new Transfer(originAccountNum, destinationAccountNum, balance, new Date()));
        return withDrawAction || depositAction || logAction;
    }
    
    @Override
    public boolean deposit(String accountNumber, Balance balance)
    {
        if(!isServiceAvailable())
        {
            return false;
        }
        IAccount account        = AccountService.getInstance().getAccount(accountNumber);
        boolean balanceExist = account.getBalance(balance.getCurrency()) != null;
        account.addBalance(balance);
        Balance  currentBalance = account.getBalance(balance.getCurrency());
        boolean logAction       = this.logTransaction(new Deposit(accountNumber, balance, new Date()));
        boolean dbAction        = true;
        if(!balanceExist)
        {
            String id = ServiceManager.getDatabaseService().create(accountNumber, balance);
            if(id != null)
            {
                currentBalance.setId(id);
                dbAction = true;
            }
            else
            {
                dbAction = false;
            }
        }
        else
        {
            dbAction = ServiceManager.getDatabaseService().updateAccountBalances(accountNumber, currentBalance);
        }
        return logAction && dbAction;
    }
    
    @Override
    public boolean withDraw(String accountNumber, Balance balance)
    {
        if(!isServiceAvailable())
        {
            return false;
        }
        IAccount account        = AccountService.getInstance().getAccount(accountNumber);
        boolean isSubtractable  = account.subtractBalance(balance);
        Balance  currentBalance = account.getBalance(balance.getCurrency());
        
        boolean logAction       = this.logTransaction(new Withdraw(accountNumber, balance, new Date()));
        boolean dbAction = false;
        if(isSubtractable)
        {
            dbAction = ServiceManager.getDatabaseService().updateAccountBalances(accountNumber, currentBalance);
        }
        return logAction && dbAction;
    }

    @Override
    public boolean addLoan(String accountNumber, Balance balance)
    {
        return withDraw(accountNumber, balance);
    }

    @Override
    public boolean payLoan(String accountNumber, Balance balance)
    {
        return deposit(accountNumber, balance);
    }

    @Override
    public boolean purchaseStock(String accountNumber, Stock stock, int cnt)
    {
        if(!isServiceAvailable())
        {
            return false;
        }
        ISecurityAccount account = (ISecurityAccount)AccountService.getInstance().getAccount(accountNumber);
        double stockPrice = stock.getPrice();
        PurchasedStock ps = account.purchaseStock(stock, stockPrice, cnt);
        if(ps == null)
        {
            return false;
        }
        boolean logAction  = this.logTransaction(new StockBuy(accountNumber, ps, ps.getPurchaseDate()));
        String  purchaseID = ServiceManager.getDatabaseService().create(accountNumber, ps);
        ps.setId(purchaseID);
        boolean dbActionUpdate = ServiceManager.getDatabaseService().updateAccountBalances(accountNumber, account.getBalance());
        return logAction && (purchaseID != null) && dbActionUpdate;
    }
    
    @Override
    public boolean sellStock(String accountNumber, Stock stock, int cnt)
    {
        if(!isServiceAvailable())
        {
            return false;
        }
        ISecurityAccount account = (ISecurityAccount)AccountService.getInstance().getAccount(accountNumber);
        double stockPrice = stock.getPrice();
        Date   date       = new Date();
        List<PurchasedStock> changedStock = account.sellStock(stock, stockPrice, cnt);
        if(changedStock == null || changedStock.isEmpty())
        {
            return false;
        }
        boolean logAction = this.logTransaction(new StockSell(accountNumber, new PurchasedStock(stock, date, stockPrice, cnt), date));
        boolean dbActionSave = true;
        for(PurchasedStock ps : changedStock)
        {
            boolean act = true;
            if(ps.getPurchaseCnt() == 0)
            {
                act = ServiceManager.getDatabaseService().remove(accountNumber, ps);
            }
            else
            {
                act = ServiceManager.getDatabaseService().update(accountNumber, ps);
            }
            dbActionSave = dbActionSave && act;
        }
        boolean dbActionUpdate = ServiceManager.getDatabaseService().updateAccountBalances(accountNumber, account.getBalance());
        return logAction && dbActionSave && dbActionUpdate;
    }
    
    public Collection<Stock> getMarketStocks()
    {
        return StockTable.getInstance().getAllStock();
    }

    @Override
    public Collection<Currency> getSupportedCurrencyTypes()
    {
        return ServiceManager.getDatabaseService().getSupportedCurrencyTypes();
    }

    @Override
    public Collection<ITransaction> getTransactionHistory(String accountNumber)
    {
        int cnt = 50;
        Collection<ITransaction> res = ServiceManager.getDatabaseService().getTransactionHistory(accountNumber, cnt);
        return res;
    }

    @Override
    public Collection<ITransaction> getTransactionHistory(int cnt)
    {
        Collection<ITransaction> res = ServiceManager.getDatabaseService().getTransactionHistory(cnt);
        return res;
    }

}
