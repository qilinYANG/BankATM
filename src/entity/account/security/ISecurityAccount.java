package entity.account.security;

import java.util.Collection;
import java.util.List;
import java.util.PriorityQueue;

import entity.Balance;
import entity.PurchasedStock;
import entity.Stock;

public interface ISecurityAccount
{
    public PurchasedStock                            purchaseStock(Stock stock, double purchasePrice, int purchaseCnt);
    public List<PurchasedStock>                      sellStock(Stock stock, double sellPrice, int sellCnt);
    public Collection<Stock>                         getHoldingStocks();
    public Collection<PriorityQueue<PurchasedStock>> getPurchasedStocks();
    public Balance                                   getBalance();
    public Balance                                   getStocksValue();
    public Balance                                   getProtfolio(); // cash + stock value
    public Balance                                   getRealizedProfit();
    public Balance                                   getUnrealizedProfit();
}
