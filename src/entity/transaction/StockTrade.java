package entity.transaction;

import java.util.Date;

import entity.Balance;
import entity.PurchasedStock;
import serverUtil.TransactionType;

public class StockTrade implements ITransaction
{
    private String          id;
    private PurchasedStock  stock;
    private Date            date;
    private TransactionType type;
    private String          accountNum;
    
    public StockTrade(String accountNum, TransactionType type, PurchasedStock stock, Date date)
    {
        this.setAccountNum(accountNum);
        this.setTransactionType(type);
        this.setDate(date);
        this.setStock(stock);
    }
    
    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public TransactionType getTransactionType()
    {
        return type;
    }

    public void setTransactionType(TransactionType type)
    {
        this.type = type;
    }

    public PurchasedStock getStock()
    {
        return stock;
    }

    public void setStock(PurchasedStock stock)
    {
        this.stock = stock;
    }

    public String getAccountNum()
    {
        return accountNum;
    }

    public void setAccountNum(String accountNum)
    {
        this.accountNum = accountNum;
    }

    @Override
    public Balance getBalance()
    {
        return null;
    }

    @Override
    public String getId()
    {
        return this.id;
    }

    @Override
    public void setId(String id)
    {
        this.id = id;
    }
}
