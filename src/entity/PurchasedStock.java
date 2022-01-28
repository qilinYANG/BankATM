package entity;

import java.util.Date;

public class PurchasedStock
{
    private String id;
    private Date   purchaseDate;
    private Stock  stock;
    private double purchasePrice;
    private int    purchaseCnt;
    
    public PurchasedStock(Stock stock, Date purchaseDate, double purchasePrice, int purchaseCnt)
    {
        this.setId("");
        this.setStock(stock);
        this.setPurchaseDate(purchaseDate);
        this.setPurchaseCnt(purchaseCnt);
        this.setPurchaseCnt(purchaseCnt);  
    }
    public PurchasedStock(Stock stock, double purchasePrice, int purchaseCnt)
    {
        this(stock, new Date(), purchasePrice, purchaseCnt);
    }
    public Date getPurchaseDate()
    {
        return purchaseDate;
    }
    public void setPurchaseDate(Date purchaseDate)
    {
        this.purchaseDate = purchaseDate;
    }

    public Stock getStock()
    {
        return stock;
    }
    public void setStock(Stock stock)
    {
        this.stock = stock;
    }
    public double getPurchasePrice()
    {
        return purchasePrice;
    }
    public void setPurchasePrice(double purchasePrice)
    {
        this.purchasePrice = purchasePrice;
    }
    public int getPurchaseCnt()
    {
        return purchaseCnt;
    }
    public boolean sellPartialStockCnt(int sellCnt)
    {
        if(this.getPurchaseCnt() >= sellCnt)
        {
            this.setPurchaseCnt(this.getPurchaseCnt() - sellCnt);
            return true;
        }
        return false;
    }
    public void setPurchaseCnt(int purchaseCnt)
    {
        this.purchaseCnt = purchaseCnt;
    }
    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
}
