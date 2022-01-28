package entity.transaction;

import java.util.Date;

import entity.Balance;
import serverUtil.TransactionType;

public class Withdraw extends Transaction
{
    private static final TransactionType TYPE    = TransactionType.WITHDRAW;
    private              String          account;
    public Withdraw(String account, Balance balance, Date date)
    {
        super(TYPE, balance, date);
        this.setAccount(account);
    }
    public String getAccount()
    {
        return account;
    }
    public void setAccount(String account)
    {
        this.account = account;
    }
    @Override
    public String toString() {
        
        return TYPE.toString()+ " amount: "+ getBalance().toString();
    }
}
