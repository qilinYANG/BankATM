package entity.transaction;

import java.util.Date;

import entity.Balance;
import serverUtil.TransactionType;

public interface ITransaction
{
    public Balance getBalance();
    public String getId();
    public void setId(String id);
    public Date getDate();
    public TransactionType getTransactionType();
    
}
