package entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class StockTable
{
    private static ConcurrentMap<String, Stock> map      = new ConcurrentHashMap<>(3); 
    private static StockTable                   instance = null;
    private StockTable(Collection<Stock> stocks)
    {
        loadStock(stocks);
    }
    
    public static StockTable initInstance(Collection<Stock> stocks)
    {
        if(instance == null)
        {
            instance = new StockTable(stocks);
        }
        return instance;
    }
    
    public static StockTable getInstance()
    {
        return instance;
    }
    
    public Stock getStock(String symbol)
    {
        if(map.containsKey(symbol))
        {
            return map.get(symbol);
        }
        else
        {
            return null;
        }
    }
    
    public Collection<Stock> getAllStock()
    {
        return new ArrayList<Stock>(map.values());
    }
    
    private void loadStock(Collection<Stock> stocks)
    {
        for(Stock stock : stocks)
        {
            map.put(stock.getSymbol(), stock);
        }
    }
}
