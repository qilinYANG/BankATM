package entity.transaction;

import java.util.Date;

import entity.PurchasedStock;
import serverUtil.TransactionType;

public class StockBuy extends StockTrade
{
    private static final TransactionType TYPE = TransactionType.BUY;
    public StockBuy(String accountNum, PurchasedStock stock, Date date) {
        super(accountNum, TYPE, stock, date);
    }

}
