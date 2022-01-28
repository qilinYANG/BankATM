package entity.transaction;

import java.util.Date;

import entity.Balance;
import serverUtil.TransactionType;

public abstract class Transaction implements ITransaction{
    private String          id;
    private Balance         balance;
    private Date            date;
    private TransactionType type;
    public Transaction(TransactionType type, Balance balance, Date date)
    {
        this.setTransactionType(type);
        this.setDate(date);
        this.setBalance(balance);
    }

    public Balance getBalance()
    {
        return balance;
    }

    public void setBalance(Balance balance)
    {
        this.balance = balance;
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

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }
    @Override
    public String toString() {
        
        return super.toString();
    }
}
