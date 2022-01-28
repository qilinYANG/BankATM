package entity;

import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class StockCollection
{
    private Map<Stock, PriorityQueue<PurchasedStock>> stockCollection;
    
    public StockCollection()
    {
        this.setStockCollection(new HashMap<Stock, PriorityQueue<PurchasedStock>>());
    }
    
    public PurchasedStock addStock(Stock stock, double purchasePrice, int purchaseCnt)
    {
        PurchasedStock ps = new PurchasedStock(stock, new Date(), purchasePrice, purchaseCnt);

        if(getStockCollection().containsKey(stock))
        {
            PriorityQueue<PurchasedStock> psList = this.getStockCollection().get(stock);
            psList.offer(ps);
        }
        else
        {
            PriorityQueue<PurchasedStock> pq = new PriorityQueue<PurchasedStock>(new Comparator<PurchasedStock>() 
            {
                @Override
                public int compare(PurchasedStock p1, PurchasedStock p2)
                {
                   return p1.getPurchaseDate().compareTo(p2.getPurchaseDate());
                }
            });
            pq.offer(ps);
            this.getStockCollection().put(stock, pq);
        }
        return ps;
    }
    
    public List<PurchasedStock> removeStock(Stock stock, int cnt)
    {
        List<PurchasedStock> changedPurchasedStock = new LinkedList<PurchasedStock>();
        if(getStockCollection().containsKey(stock))
        {
            PriorityQueue<PurchasedStock> pq = this.getStockCollection().get(stock);
            PurchasedStock ps = pq.peek();
            while(ps.getPurchaseCnt() <= cnt)
            {
                int purchaseCnt = ps.getPurchaseCnt();
                ps.sellPartialStockCnt(purchaseCnt - cnt);
                cnt -= purchaseCnt;
                changedPurchasedStock.add(ps);
                pq.poll();
                ps = pq.peek();
            }
            if(cnt != 0)
            {
                int purchaseCnt = ps.getPurchaseCnt();
                ps.sellPartialStockCnt(purchaseCnt - cnt);
                changedPurchasedStock.add(ps);
            }
        }
        return changedPurchasedStock;
    }
    
    public Collection<Stock> getPurchasedStock()
    {
        return this.getStockCollection().keySet(); 
    }
    
    public void addPurchasedStock(PurchasedStock ps)
    {
        if(this.getStockCollection().containsKey(ps.getStock()))
        {
            this.getStockCollection().get(ps.getStock()).offer(ps);
        }
        else
        {
            PriorityQueue<PurchasedStock> pq = new PriorityQueue<PurchasedStock>(new Comparator<PurchasedStock>() 
            {
                @Override
                public int compare(PurchasedStock p1, PurchasedStock p2)
                {
                   return p1.getPurchaseDate().compareTo(p2.getPurchaseDate());
                }
            });
            pq.offer(ps);
            this.getStockCollection().put(ps.getStock(), pq);
        }
    }
    
    public PriorityQueue<PurchasedStock> getPurchaseDetails(Stock stock)
    {
        if(this.getStockCollection().containsKey(stock))
        {
            return this.getStockCollection().get(stock);
        }
        else
        {
            return new PriorityQueue<PurchasedStock>();
        }
    }
    
    public Collection<PriorityQueue<PurchasedStock>> getAllPurchaseDetails()
    {
        return this.getStockCollection().values();
    }
    
    public double getAvgPurchasePrice(Stock stock)
    {
        return getPurchaseCostForSingleStock(stock) / getStockCount(stock);
    }
    
    public double getPurchaseCostForSingleStock(Stock stock)
    {
        double sum = 0.0;
        if(this.getStockCollection().containsKey(stock))
        {
            PriorityQueue<PurchasedStock> pq = this.getStockCollection().get(stock);
            for(PurchasedStock ps : pq)
            {
                sum += ps.getPurchasePrice();
            }
        }
        return sum;
    }
    
    public double getTotalValueForSingleStock(Stock stock)
    {
        return stock.getPrice() * this.getStockCount(stock);
    }
    
    public double getTotalValue()
    {
        double sum = 0.0;
        for(Stock stock : this.getStockCollection().keySet())
        {
            sum += getTotalValueForSingleStock(stock);
        }
        return sum;
    }
    
    public int getStockCount(Stock stock)
    {
        int cnt = 0;
        if(this.getStockCollection().containsKey(stock))
        {
            PriorityQueue<PurchasedStock> pq = this.getStockCollection().get(stock);
            for(PurchasedStock ps : pq)
            {
                cnt += ps.getPurchaseCnt();
            }
        }
        return cnt;
    }
    
    public double getTotalProfitForSingleStock(Stock stock)
    {
        return  getTotalValueForSingleStock(stock) - this.getPurchaseCostForSingleStock(stock);
    }
    
    public double getTotalPurchaseCost()
    {
        double sum = 0.0;
        for(Stock stock : this.getStockCollection().keySet())
        {
            sum += getPurchaseCostForSingleStock(stock);
        }
        return sum;
    }
    
    public double getTotalProfit()
    {
        double totalProfit = 0.0;
        for(Stock stock : this.getStockCollection().keySet())
        {
            totalProfit += this.getTotalProfitForSingleStock(stock);
        }
        return totalProfit;
    }

    public Map<Stock, PriorityQueue<PurchasedStock>> getStockCollection()
    {
        return stockCollection;
    }

    public void setStockCollection(Map<Stock, PriorityQueue<PurchasedStock>> stockCollection)
    {
        this.stockCollection = stockCollection;
    }
}
