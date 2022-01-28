package service.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Currency;
import java.util.Date;
import java.util.List;

import database.DatabaseManager;
import entity.Balance;
import entity.Fee;
import entity.Interest;
import entity.PurchasedStock;
import entity.Stock;
import entity.account.AccountCollection;
import entity.account.AccountFactory;
import entity.account.IAccount;
import entity.account.security.SecurityAccount;
import entity.transaction.Deposit;
import entity.transaction.ITransaction;
import entity.transaction.Transfer;
import entity.transaction.Withdraw;
import entity.user.Customer;
import serverUtil.AccountType;
import serverUtil.TransactionType;

public class DatabaseService implements IDatabaseService
{
    private static DatabaseService  service = null;
    private        DatabaseManager  dbManager;
    private        SimpleDateFormat dateFormat;
    private        SimpleDateFormat dateTimeFormat;
    private DatabaseService() 
    {
        this.setDbManager(DatabaseManager.getInstance());
        this.setDateFormat(new SimpleDateFormat("YYYY-MM-DD"));
        this.setDateTimeFormat(new SimpleDateFormat("YYYY-MM-DD HH:MM:SS.SSS"));
    }
    public static IDatabaseService getInstance()
    {
        if(service == null)
        {
            service = new DatabaseService();
        }
        return service;
    }

    @Override
    public String create(IAccount account)
    {
        Connection connection = this.getDbManager().getConnection();
        String            sql = "INSERT INTO Account(isActive,AccountType,UserID,CreationTime) VALUES(?,?,?,?)"; 
        String            id  = null;
        try(PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {
            pstmt.setBoolean(1, true);
            pstmt.setString(2, account.getAccountType().toString());
            pstmt.setInt(3, Integer.valueOf(account.getAccountOwner()));
            pstmt.setString(4, this.getDateTimeFormat().format(account.getCreateDate()));
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                int newId = rs.getInt(1);
                id = String.valueOf(newId);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    @Override
    public String create(Customer cust)
    {
        Connection connection = this.getDbManager().getConnection();
        String     sql        = "INSERT INTO User(Username, Email, Password, Firstname, Lastname, DOB, CreditScore, CreationTime, isAdmin) VALUES(?,?,?,?,?,?,?,?,?)"; 
        String     id         = null;
        try(PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {
            pstmt.setString(1, cust.getUsername());
            pstmt.setString(2, cust.getEmail());
            pstmt.setString(3, cust.getPassword());
            pstmt.setString(4, cust.getFirstName());
            pstmt.setString(5, cust.getLastName());
            pstmt.setString(6, this.getDateFormat().format(cust.getBirthDay()));
            pstmt.setInt(7, cust.getCreditScore());
            pstmt.setString(8, this.getDateTimeFormat().format(cust.getCreationTime()));
            pstmt.setBoolean(9, cust.isAdmin());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                int newId = rs.getInt(1);
                id = String.valueOf(newId);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    @Override
    public String create(ITransaction transaction)
    {
        Connection connection = this.getDbManager().getConnection();
        String     sql        = "INSERT INTO TransactionRecord(TransactionType, Currency, Value, AccountIDFrom, AccountIDTo, Time) VALUES(?,?,?,?,?,?)";
        String     id         = null;
        try(PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {
            pstmt.setString(1, transaction.getTransactionType().toString());
            pstmt.setString(2, transaction.getBalance().getCurrency().toString());
            pstmt.setDouble(3, transaction.getBalance().getValue());
            if(transaction instanceof Transfer)
            {
                pstmt.setString(4, ((Transfer)transaction).getOriginAccountNum());
                pstmt.setString(5, ((Transfer)transaction).getDestAccountNum());
            }
            if(transaction instanceof Withdraw)
            {
                pstmt.setString(4, ((Withdraw)transaction).getAccount());
            }
            if(transaction instanceof Deposit)
            {
                pstmt.setString(5, ((Deposit)transaction).getAccount());
            }
            pstmt.setString(6, this.getDateTimeFormat().format(transaction.getDate()));

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                int newId = rs.getInt(1);
                id = String.valueOf(newId);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    @Override
    public String create(String accountNumber, Balance balance)
    {
        Connection connection = this.getDbManager().getConnection();
        String     sql        = "INSERT INTO Balance(AccountID,Currency,Value) VALUES(?,?,?)"; 
        String     id         = null;
        try(PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {
            pstmt.setString(1, accountNumber);
            pstmt.setString(2, balance.getCurrency().toString());
            pstmt.setDouble(3, balance.getValue());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                int newId = rs.getInt(1);
                id = String.valueOf(newId);
            }
        }
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return id;        
    }

    @Override
    public String create(String accountNumber, PurchasedStock ps)
    {
        Connection connection = this.getDbManager().getConnection();
        String     sql        = "INSERT INTO Trading(StockID, PurchasePrice, PurchaseCnt, Time, AccountID) VALUES(?,?,?,?,?,?)"; 
        String     id         = null;
        try(PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {
            pstmt.setString(1, ps.getStock().getId());
            pstmt.setDouble(2, ps.getPurchasePrice());
            pstmt.setInt(3, ps.getPurchaseCnt());
            pstmt.setString(4, this.getDateTimeFormat().format(ps.getPurchaseDate()));
            pstmt.setString(5, accountNumber);

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                int newId = rs.getInt(1);
                id = String.valueOf(newId);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    @Override
    public Interest getInterestRate()
    {
        Connection connection = this.getDbManager().getConnection();
        String     sql        = "SELECT * FROM Interest WHERE Name = ?";
        Interest   interest   = null;
        try(PreparedStatement pstmt  = connection.prepareStatement(sql))
        {
            pstmt.setString(1, "general");
            ResultSet res  = pstmt.executeQuery();
            while(res.next())
            {
                String id = String.valueOf(res.getInt("id"));
                String name = res.getString("Name");
                double value = res.getDouble("Value");
                int cycle = res.getInt("Cycle");
                interest = new Interest(name, value, cycle);
                interest.setId(id);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return interest;
    }
    
    @Override
    public Fee getFee(String name)
    {
        Connection connection = this.getDbManager().getConnection();
        String     sql        = "SELECT * FROM Fee WHERE Name = ?";
        Fee        fee        = null;
        try(PreparedStatement pstmt  = connection.prepareStatement(sql))
        {
            pstmt.setString(1, name);
            ResultSet res  = pstmt.executeQuery();
            while(res.next())
            {
                String id = String.valueOf(res.getInt("id"));
                String feeName = res.getString("Name");
                double value = res.getDouble("Value");
                fee = new Fee(feeName, value);
                fee.setId(id);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return fee;
    }
    
    @Override
    public String getManagerAccount()
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Collection<IAccount> getUserAccounts(String userID)
    {
        Connection connection = this.getDbManager().getConnection();
        String     sql        = "SELECT id FROM Account WHERE UserID = ?";
        List<IAccount> accounts = new ArrayList<IAccount>();
        try(PreparedStatement pstmt  = connection.prepareStatement(sql))
        {        
            pstmt.setString(1, userID);
            ResultSet res  = pstmt.executeQuery();
            while(res.next())
            {
                String accountNumber = String.valueOf(res.getInt("id"));
                IAccount acc = getUserAccount(accountNumber);
                accounts.add(acc);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return accounts;
    }

    @Override
    public IAccount getUserAccount(String accountNumber)
    {
        Connection connection = this.getDbManager().getConnection();
        String     sql        = "SELECT * FROM Account WHERE id = ?";
        IAccount   account    = null;
        try(PreparedStatement pstmt  = connection.prepareStatement(sql))
        {
            pstmt.setString(1, accountNumber);
            ResultSet res  = pstmt.executeQuery();
            while(res.next())
            {
                String id = String.valueOf(res.getInt("id"));
                boolean isActive = res.getBoolean("IsActive");
                AccountType accountType = AccountType.toAccountType(res.getString("AccountType"));
                String userID = String.valueOf(res.getInt("UserID"));
                Date creationTime = new Date(res.getDate("CreationTime").getTime());
                account = AccountFactory.createAccount(accountType, userID);
                account.setAccountNumber(id);
                account.setCreateDate(creationTime);
                account.setActive(isActive);
                Collection<Balance> balances = getAccountBalances(accountNumber);
                for(Balance bal : balances)
                {
                    account.addBalance(bal);
                }
                if(accountType == AccountType.SECURITY)
                {
                    Collection<PurchasedStock> psCollection = getPurchasedStock(accountNumber);
                    for(PurchasedStock ps : psCollection)
                    {
                        ((SecurityAccount)account).getStockCollection().addPurchasedStock(ps);
                    }
                }
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return account;
    } 
    
    @Override
    public Collection<PurchasedStock> getPurchasedStock(String accountNumber)
    {
        Connection connection = this.getDbManager().getConnection();
        String     sql        = "SELECT * FROM Trading WHERE AccountID = ?";
        List<PurchasedStock> ps = new ArrayList<>();
        try(PreparedStatement pstmt  = connection.prepareStatement(sql))
        {
            pstmt.setString(1, accountNumber);
            ResultSet res  = pstmt.executeQuery();
            while(res.next())
            {
                String id = String.valueOf(res.getInt("id"));
                String stockid = String.valueOf(res.getInt("StockID"));
                double purchasePrice = res.getDouble("PurchasePrice");
                int purchaseCnt = res.getInt("PurchaseCnt");
                Date time = new Date(res.getDate("Time").getTime());;

                String accountId =  String.valueOf(res.getInt("AccountID"));
                String currency = res.getString("Currency");
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return ps;
    }


    @Override
    public Collection<Balance> getAccountBalances(String accountNumber)
    {
        Connection connection = this.getDbManager().getConnection();
        String     sql        = "SELECT * FROM BALANCE WHERE AccountID = ?";
        List<Balance> balances = new ArrayList<Balance>();
        try(PreparedStatement pstmt  = connection.prepareStatement(sql))
        {
            pstmt.setString(1, accountNumber);
            ResultSet res  = pstmt.executeQuery();
            while(res.next())
            {
                String id = String.valueOf(res.getInt("id"));
                String accountId =  String.valueOf(res.getInt("AccountID"));
                String currency = res.getString("Currency");
                double value = res.getDouble("Value");
                Balance bal = new Balance(Currency.getInstance(currency), value);
                bal.setId(id);
                balances.add(bal);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return balances;
    }

    @Override
    public Customer getCustomer(String userName, String password)
    {
        Connection connection = this.getDbManager().getConnection();
        String     sql        = "SELECT * FROM User WHERE  Username = ? AND Password = ?";
        Customer   customer   = null;
        try(PreparedStatement pstmt  = connection.prepareStatement(sql))
        {
            pstmt.setString(1, userName);
            pstmt.setString(2, password);
            ResultSet res  = pstmt.executeQuery();
            if(res.next())
            {
                String  id       = String.valueOf(res.getInt("id"));
                String  username = res.getString("Username");
                String  email    = res.getString("Email");
                String  pwd      = res.getString("Password");
                String  first    = res.getString("Firstname");
                String  last     = res.getString("Lastname");
                Date    dob      = this.getDateFormat().parse(res.getString("DOB"));
                int     creditScore = res.getInt("CreditScore");
                Date    regTime  = new Date(res.getDate("RegTime").getTime());;
                boolean isAdmin  = res.getBoolean("IsAdmin");
                customer = new Customer(username, first, last, dob, email, creditScore, pwd);
                customer.setCreationTime(regTime);
                customer.setUserID(id);
                customer.setAdmin(isAdmin);
                AccountCollection accountCollection = new AccountCollection();
                Collection<IAccount> accounts = getUserAccounts(customer.getUserID());
                for(IAccount account : accounts)
                {
                    accountCollection.addAccount(account);
                }
                customer.setAccountCollection(accountCollection);
            }
        }
        catch (SQLException | ParseException e) {
            System.out.println(e.getMessage());
        }

        return customer;
    }
    
    @Override
    public Collection<Customer> getAllCustomer()
    {
        Connection connection = this.getDbManager().getConnection();
        String     sql        = "SELECT * FROM User";
        Collection<Customer> customers = new ArrayList<Customer>();
        try(PreparedStatement pstmt  = connection.prepareStatement(sql))
        {
            ResultSet res  = pstmt.executeQuery();
            while(res.next())
            {
                Customer customer;
                String  id       = String.valueOf(res.getInt("id"));
                String  username = res.getString("Username");
                String  email    = res.getString("Email");
                String  pwd      = res.getString("Password");
                String  first    = res.getString("Firstname");
                String  last     = res.getString("Lastname");
                Date    dob      = this.getDateFormat().parse(res.getString("DOB"));
                int     creditScore = res.getInt("CreditScore");
                Date    regTime  = new Date(res.getDate("RegTime").getTime());;
                boolean isAdmin  = res.getBoolean("IsAdmin");
                customer = new Customer(username, first, last, dob, email, creditScore, pwd);
                customer.setCreationTime(regTime);
                customer.setUserID(id);
                customer.setAdmin(isAdmin);
                AccountCollection accountCollection = new AccountCollection();
                Collection<IAccount> accounts = getUserAccounts(customer.getUserID());
                for(IAccount account : accounts)
                {
                    accountCollection.addAccount(account);
                }
                customer.setAccountCollection(accountCollection);
                
                customers.add(customer);
            }
        }
        catch (SQLException | ParseException e) {
            System.out.println(e.getMessage());
        }

        return customers;
    }

    @Override
    public boolean checkUserNameExist(String userName)
    {
        Connection connection = this.getDbManager().getConnection();
        String     sql        = "SELECT * FROM User WHERE Username = ?";
        boolean    isExist    = false;
        try(PreparedStatement pstmt  = connection.prepareStatement(sql))
        {
            pstmt.setString(1, userName);
            ResultSet res  = pstmt.executeQuery();
            if(res.next())
            {
                isExist = true;
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return isExist;
    }
    
    @Override
    public boolean checkIsAdmin(String userName)
    {
        Connection connection = this.getDbManager().getConnection();
        String     sql        = "SELECT * FROM User WHERE Username = ?";
        boolean    isAdmin    = false;
        try(PreparedStatement pstmt  = connection.prepareStatement(sql))
        {
            pstmt.setString(1, userName);
            ResultSet res  = pstmt.executeQuery();
            if(res.next())
            {
                isAdmin  = res.getBoolean("IsAdmin");
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return isAdmin;
    }

    @Override
    public Collection<Stock> getMarketStocks()
    {
        Connection  connection = this.getDbManager().getConnection();
        String      sql        = "SELECT * FROM Stock";
        List<Stock> stocks     = new ArrayList<Stock>();
        try(PreparedStatement pstmt  = connection.prepareStatement(sql))
        {
            ResultSet res  = pstmt.executeQuery();
            while(res.next())
            {
                String id = String.valueOf(res.getInt("id"));
                String name = res.getString("Name");
                String symbol = res.getString("Symbol");
                double price = res.getDouble("Price");
                Stock s = new Stock(id, name, symbol, price);
                stocks.add(s);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return stocks;
    }

    @Override
    public Collection<ITransaction> getTransactionHistory(int cnt)
    {
        Connection connection = this.getDbManager().getConnection();
        String     sql        = "SELECT * FROM TransactionRecord";
        List<ITransaction> transactionList = new ArrayList<>();
        try(PreparedStatement pstmt  = connection.prepareStatement(sql))
        {
            ResultSet res  = pstmt.executeQuery();
            while(res.next() && cnt-- > 0)
            {
                String id = String.valueOf(res.getInt("id"));
                TransactionType type = TransactionType.toTransactionType(res.getString("TransactionType"));
                Currency currency = Currency.getInstance(res.getString("Currency"));
                double value = res.getDouble("Value");
                Balance bal = new Balance(currency, value);
                String from = res.getString("AccountIDFrom");
                String to = res.getString("AccountIDTo");
                Date time = new Date(res.getDate("Time").getTime());;
                ITransaction transaction = null;
                switch(type)
                {
                case TRANSFER:
                    transaction = new Transfer(from, to, bal, time);
                    break;
                case DEPOSIT:
                    transaction = new Deposit(to, bal, time);
                    break;
                case WITHDRAW:
                    transaction = new Withdraw(from, bal, time);
                    break;
                }
                transactionList.add(transaction);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Collection<ITransaction> getTransactionHistory(String accountNumber, int record)
    {
        Connection connection = this.getDbManager().getConnection();
        String     sql        = "SELECT * FROM TransactionRecord WHERE AccountIDFrom = ? OR AccountIDTo = ?";
        List<ITransaction> transactionList = new ArrayList<>();
        try(PreparedStatement pstmt  = connection.prepareStatement(sql))
        {
            pstmt.setString(1, accountNumber);
            pstmt.setString(2, accountNumber);
            ResultSet res  = pstmt.executeQuery();
            while(res.next() && record-- > 0)
            {
                String id = String.valueOf(res.getInt("id"));
                TransactionType type = TransactionType.toTransactionType(res.getString("TransactionType"));
                Currency currency = Currency.getInstance(res.getString("Currency"));
                double value = res.getDouble("Value");
                Balance bal = new Balance(currency, value);
                String from = res.getString("AccountIDFrom");
                String to = res.getString("AccountIDTo");
                Date time = new Date(res.getDate("Time").getTime());;
                ITransaction transaction = null;
                switch(type)
                {
                case TRANSFER:
                    transaction = new Transfer(from, to, bal, time);
                    break;
                case DEPOSIT:
                    transaction = new Deposit(to, bal, time);
                    break;
                case WITHDRAW:
                    transaction = new Withdraw(from, bal, time);
                    break;
                }
                transactionList.add(transaction);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return transactionList;
    }

    @Override
    public Collection<Currency> getSupportedCurrencyTypes()
    {
        Connection connection = this.getDbManager().getConnection();
        String     sql        = "SELECT * FROM Currency";
        List<Currency> currencyList = new ArrayList<>();
        try(PreparedStatement pstmt  = connection.prepareStatement(sql))
        {
            ResultSet res  = pstmt.executeQuery();
            while(res.next())
            {
                String id = String.valueOf(res.getInt("id"));
                String name = res.getString("Name");
                String symbol = res.getString("Symbol");
                Currency currency = Currency.getInstance(name);
                currencyList.add(currency);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return currencyList;
    }

    @Override
    public boolean remove(String accountNumber, PurchasedStock ps)
    {
        Connection connection = this.getDbManager().getConnection();
        String     sql        = "DELETE FROM Trading WHERE id = ? AND AccountID = ?";
        int res = 0;
        try(PreparedStatement pstmt  = connection.prepareStatement(sql))
        {
            pstmt.setString(1, ps.getId());
            pstmt.setString(2, accountNumber);
            res  = pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return res != 0;
    }

    @Override
    public boolean updateAccountBalances(String accountNumber, Balance balance)
    {
        Connection connection = this.getDbManager().getConnection();
        String     sql        = "UPDATE Balance SET Value = ? WHERE id = ?";
        int        res        = 0;
        try(PreparedStatement pstmt  = connection.prepareStatement(sql))
        {
            pstmt.setDouble(1, balance.getValue());
            pstmt.setString(2, balance.getId());

            res = pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return res != 0;
    }

    @Override
    public boolean update(String accountNumber, PurchasedStock ps)
    {
        Connection connection = this.getDbManager().getConnection();
        String     sql        = "UPDATE Trading SET PurchaseCnt = ? WHERE id = ?";
        int        res        = 0;
        try(PreparedStatement pstmt  = connection.prepareStatement(sql))
        {
            pstmt.setInt(1, ps.getPurchaseCnt());
            pstmt.setString(2, ps.getId());

            res = pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return res != 0;
    }

    @Override
    public boolean updateInterestRate(double rate)
    {
        Connection connection = this.getDbManager().getConnection();
        String     sql        = "UPDATE Interest SET Value= ? WHERE Name = ?";
        int        res        = 0;
        try(PreparedStatement pstmt  = connection.prepareStatement(sql))
        {
            pstmt.setDouble(1, rate);
            pstmt.setString(2, "general");

            res = pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return res != 0;
    }

    @Override
    public boolean updateStockPrice(Stock stock, double price)
    {
        Connection connection = this.getDbManager().getConnection();
        String     sql        = "UPDATE Stock SET Price= ? WHERE Name = ?";
        int        res        = 0;
        try(PreparedStatement pstmt  = connection.prepareStatement(sql))
        {
            pstmt.setDouble(1, price);
            pstmt.setString(2, stock.getName());

            res = pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return res != 0;
    }

    @Override
    public boolean updateFee(double value)
    {
        Connection connection = this.getDbManager().getConnection();
        String     sql        = "UPDATE Fee SET Value = ? WHERE Name = ?";
        int        res        = 0;
        try(PreparedStatement pstmt  = connection.prepareStatement(sql))
        {
            pstmt.setDouble(1, value);
            pstmt.setString(2, "general");

            res = pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return res != 0;
    }

    public DatabaseManager getDbManager()
    {
        return dbManager;
    }
    public void setDbManager(DatabaseManager dbManager)
    {
        this.dbManager = dbManager;
    }
    public SimpleDateFormat getDateFormat()
    {
        return dateFormat;
    }
    public void setDateFormat(SimpleDateFormat dateFormat)
    {
        this.dateFormat = dateFormat;
    }
    public SimpleDateFormat getDateTimeFormat()
    {
        return dateTimeFormat;
    }
    public void setDateTimeFormat(SimpleDateFormat dateTimeFormat)
    {
        this.dateTimeFormat = dateTimeFormat;
    }

    @Override
    public boolean isServiceAvailable()
    {
        return true;
    }
}
