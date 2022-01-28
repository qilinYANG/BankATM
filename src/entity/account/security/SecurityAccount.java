package entity.account.security;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import entity.Balance;
import entity.PurchasedStock;
import entity.Stock;
import entity.StockCollection;
import entity.account.AbsSingleCurrencyAccount;
import serverUtil.AccountType;

public class SecurityAccount extends AbsSingleCurrencyAccount implements ISecurityAccount
{
    private static final AccountType     ACCOUNTTYPE     = AccountType.SECURITY;
    private              StockCollection stockCollection;

    public SecurityAccount(String accountId, String accountOwnerID) {
        super(accountId, ACCOUNTTYPE, accountOwnerID);
        this.setStockCollection(new StockCollection());
    }

    public SecurityAccount(String accountOwnerID) {
        this(null, accountOwnerID);
    }

    @Override
    public PurchasedStock purchaseStock(Stock stock, double purchasePrice, int purchaseCnt)
    {
        if(purchasePrice * purchaseCnt > getBalance().getValue())
        {
            return null;
        }
        else
        {
            PurchasedStock ps = this.getStockCollection().addStock(stock, purchasePrice, purchaseCnt);
            this.getBalance().subTrackValue(purchasePrice * purchaseCnt);
            return ps;
        }
    }
    
    public List<PurchasedStock> sellStock(Stock stock, double sellPrice, int sellCnt)
    {
        List<PurchasedStock> updatedStock = this.getStockCollection().removeStock(stock, sellCnt);
        if(updatedStock != null && !updatedStock.isEmpty())
        {
            this.getBalance().addValue(sellPrice * sellCnt);

        }
        return updatedStock;
    }

    @Override
    public Collection<Stock> getHoldingStocks()
    {
        return this.getStockCollection().getPurchasedStock();
    }

    @Override
    public Balance getStocksValue()
    {
        double val = this.getStockCollection().getTotalValue();
        return new Balance(val);
    }

    @Override
    public Balance getProtfolio()
    {
        return Balance.addUp(getUnrealizedProfit(), getRealizedProfit());
    }

    @Override
    public Balance getRealizedProfit()
    {
        return getBalance();
    }

    @Override
    public Balance getUnrealizedProfit()
    {
        double val = this.getStockCollection().getTotalProfit();
        return new Balance(val);
    }
    
    // getters and setters
    public StockCollection getStockCollection()
    {
        return stockCollection;
    }

    public void setStockCollection(StockCollection stockCollection)
    {
        this.stockCollection = stockCollection;
    }

    @Override
    public Collection<PriorityQueue<PurchasedStock>> getPurchasedStocks()
    {
        return this.getStockCollection().getAllPurchaseDetails();
    }
}
