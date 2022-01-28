//package service.database;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Currency;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.LinkedList;
//import java.util.Set;
//
//import entity.Balance;
//import entity.PurchasedStock;
//import entity.Stock;
//import entity.account.IAccount;
//import entity.account.deposit.CheckingAccount;
//import entity.account.deposit.SavingAccount;
//import entity.account.loan.LoanAccount;
//import entity.account.security.SecurityAccount;
//import entity.transaction.ITransaction;
//import entity.user.Customer;
//import serverUtil.AccountType;
//
//public class DummyDatabaseService
//{
//    private static DummyDatabaseService service = null;
//    
//    private Customer          customer = null;
//    private Set<Customer>     customerSet;
//    
//    private CheckingAccount   checking;
//    private Balance           checkingBalance;
//    
//    private SavingAccount     saving;
//    private Balance           savingBalance;
//    
//    private LoanAccount       loan;
//    private Balance           loanBalance;
//    
//    private SecurityAccount   security;
//    
//    private DummyDatabaseService() 
//    {
//        customer = new Customer("mababa", "Yun", "Ma", new Date(), "mayun@ali.com", "mababa", false);
//        customer.setID("001");
//        customerSet = new HashSet<Customer>();
//        customerSet.add(customer);
//        
//        checking = new CheckingAccount(customer.getID());
//        checkingBalance = new Balance(100);
//        checking.setAccountNumber("001");
//        checking.deposit(checkingBalance);
//        
//        saving = new SavingAccount(customer.getID());
//        saving.setAccountNumber("002");
//        savingBalance = new Balance(200);
//        saving.deposit(savingBalance);
//        
//        loan = new LoanAccount(customer.getID());
//        loan.setAccountNumber("003");
//        loanBalance = new Balance(300);
//        
//        security = new SecurityAccount(customer.getID());
//        security.setAccountNumber("004");
//        
//        customer.getAccountCollection().addAccount(checking);
//        customer.getAccountCollection().addAccount(saving);
//        customer.getAccountCollection().addAccount(loan);
//        customer.getAccountCollection().addAccount(security);
//    };
//
//    public static IDatabaseService getInstance()
//    {
//        if(service == null)
//        {
//            service = new DummyDatabaseService();
//        }
//        return service;
//    }
//    @Override
//    public boolean isServiceAvailable()
//    {
//        return true;
//    }
//    
//    @Override
//    public Collection<Balance> getAccountBalances(String accountNumber)
//    {
//        IAccount account = this.customer.getAccountCollection().getAccount(accountNumber);
//        if(AccountType.isDepositAccount(account))
//        {
//            return (account).getAllBalance();
//        }
//        else
//        {
//            Collection<Balance> balanceCollection = new LinkedList<Balance>();
//            balanceCollection.add(account.getBalance(Currency.getInstance("USD")));
//            return balanceCollection;
//        }
//    }
//    @Override
//    public boolean updateAccountBalances(String accountNumber, Balance balance)
//    {
//        Balance existingRecord = this.customer.getAccountCollection().getAccount(accountNumber).getBalance(balance.getCurrency());
//        existingRecord.setValue(balance.getValue());
//        return true;
//    }
//    @Override
//    public String save(Customer cust)
//    {
//        this.customerSet.add(cust);
//        this.customer = cust;
//        return "XXX";
//    }
//    @Override
//    public Customer getCustomer(String userName, String password)
//    {
//        return this.customer;
//    }
//    @Override
//    public boolean checkUserNameExist(String userName)
//    {
//        for(Customer cust : customerSet)
//        {
//            if(cust.getUsername().equals(userName))
//            {
//                return true;
//            }
//        }
//        return false;
//    }
//    @Override
//    public Collection<IAccount> getUserAccounts(String userID)
//    {
//        return this.customer.getAccountList();
//    }
//    
//    @Override
//    public String save(ITransaction transaction)
//    {
//        return null;
//    }
//
//    @Override
//    public Collection<Currency> getSupportedCurrencyTypes()
//    {
//        Collection<Currency> res = new LinkedList<Currency>();
//        res.add(Currency.getInstance("USD"));
//        res.add(Currency.getInstance("CNY"));
//        res.add(Currency.getInstance("EUR"));
//        return res;
//    }
//
//    @Override
//    public boolean save(String accountNumber, PurchasedStock ps)
//    {
//        // TODO Auto-generated method stub
//        return false;
//    }
//
//    @Override
//    public boolean remove(String accountNumber, PurchasedStock ps)
//    {
//        // TODO Auto-generated method stub
//        return false;
//    }
//
//    @Override
//    public boolean update(String accountNumber, PurchasedStock ps)
//    {
//        // TODO Auto-generated method stub
//        return false;
//    }
//
//    @Override
//    public Collection<Stock> getMarketStocks()
//    {
//        Collection<Stock> res = new ArrayList<Stock>();
//        res.add(new Stock("XX", "ALIBABA", "BABA", 1000.00));
//        return null;
//    }
//
//    @Override
//    public Collection<ITransaction> getTransactionHistory(String accountNumber, int record)
//    {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public String save(IAccount account)
//    {
//        // TODO Auto-generated method stub
//        return null;
//    }
//}
