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
import service.ServiceManager;

/**
 * proxy impl for charging fees the call real method in real impl
 */
public class ProxyAccountService implements IAccountService
{

    @Override
    public boolean isServiceAvailable()
    {
        return ServiceManager.getAccountService().isServiceAvailable();
    }

    @Override
    public Collection<AccountType> getCreatableAccountType(String userName)
    {
        return ServiceManager.getAccountService().getCreatableAccountType(userName);
    }

    @Override
    public Collection<IAccount> getAllAccounts(String userName)
    {
        return ServiceManager.getAccountService().getAllAccounts(userName);
    }

    @Override
    public IAccount getAccount(String accountNum)
    {
        return ServiceManager.getAccountService().getAccount(accountNum);
    }

    @Override
    public void setAccountCollection(AccountCollection accountCollection)
    {
        ServiceManager.getAccountService().setAccountCollection(accountCollection);
    }

    @Override
    public String openCheckingAccount(String userName, double deposit)
    {
        String id = ServiceManager.getAccountService().openCheckingAccount(userName, deposit);
        ServiceManager.getManagerReviewService().chargeFee(id);
        return id;
    }

    @Override
    public String openSavingAccount(String userName, double deposit)
    {
        String id = ServiceManager.getAccountService().openSavingAccount(userName, deposit);
        ServiceManager.getManagerReviewService().chargeFee(id);
        return id;
    }

    @Override
    public String openLoanAccount(String userName, double loanValue)
    {
        String id = ServiceManager.getAccountService().openLoanAccount(userName, loanValue);
        ServiceManager.getManagerReviewService().chargeFee(id);
        return id;
    }

    @Override
    public String openSecurityAccount(String userName)
    {
        String id = ServiceManager.getAccountService().openSecurityAccount(userName);
        ServiceManager.getManagerReviewService().chargeFee(id);
        return id;
    }

    @Override
    public Collection<Balance> getDepositBalances(String accountNumber)
    {
        return  ServiceManager.getAccountService().getDepositBalances(accountNumber);
    }

    @Override
    public Balance getLoanBalance(String accountNumber)
    {
        return  ServiceManager.getAccountService().getLoanBalance(accountNumber);
    }

    @Override
    public Collection<Stock> getHoldingStocks(String accountNumber)
    {
        return  ServiceManager.getAccountService().getHoldingStocks(accountNumber);
    }

    @Override
    public Collection<PriorityQueue<PurchasedStock>> getPurchasedSotcks(String accountNumber)
    {
        return  ServiceManager.getAccountService().getPurchasedSotcks(accountNumber);
    }

    @Override
    public Balance getProtfolio(String accountNumber)
    {
        return  ServiceManager.getAccountService().getProtfolio(accountNumber);
    }

    @Override
    public Balance getRealizedProfit(String accountNumber)
    {
        return  ServiceManager.getAccountService().getRealizedProfit(accountNumber);
    }

    @Override
    public Balance getUnrealizedProfit(String accountNumber)
    {
        return  ServiceManager.getAccountService().getUnrealizedProfit(accountNumber);
    }

    @Override
    public boolean closeAccount(String accountNumber)
    {
        return  ServiceManager.getAccountService().closeAccount(accountNumber);
    }

    @Override
    public Interest getInterest(String accountNumber)
    {
        return  ServiceManager.getAccountService().getInterest(accountNumber);
    }

    @Override
    public void reset()
    {
        ServiceManager.getAccountService().reset();
    }

}
