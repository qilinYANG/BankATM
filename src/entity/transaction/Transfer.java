package entity.transaction;

import java.util.Date;

import entity.Balance;
import serverUtil.TransactionType;

public class Transfer extends Transaction
{
    private static final TransactionType TYPE             = TransactionType.TRANSFER;
    private              String          originAccountNum;
    private              String          destAccountNum;
    
    public Transfer(String originAccountNum, String destAccountNum, Balance balance, Date date)
    {
        super(TYPE, balance, date);
        this.setOriginAccountNum(originAccountNum);
        this.setDestAccountNum(destAccountNum);
    }

    public String getOriginAccountNum()
    {
        return originAccountNum;
    }

    public void setOriginAccountNum(String originAccountNum)
    {
        this.originAccountNum = originAccountNum;
    }

    public String getDestAccountNum()
    {
        return destAccountNum;
    }

    public void setDestAccountNum(String destAccountNum)
    {
        this.destAccountNum = destAccountNum;
    }

    @Override
    public String toString() {
        
        return TYPE.toString()+" from: " + originAccountNum+ " to: " + destAccountNum + " amount: "+ getBalance().toString();
    }
}
