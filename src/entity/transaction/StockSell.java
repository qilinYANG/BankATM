package entity.transaction;

import java.util.Date;

import entity.PurchasedStock;
import serverUtil.TransactionType;

public class StockSell  extends StockTrade
{
    private static final TransactionType TYPE = TransactionType.SELL;
    public StockSell(String accountNum, PurchasedStock stock, Date date) {
        super(accountNum, TYPE, stock, date);
    }

}
