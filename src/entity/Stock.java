package entity;

public class Stock
{
    private String id;
    private String name;
    private String symbol;
    private double price;
    public Stock(String id, String name, String symbol, double price)
    {
        this.setId(id);
        this.setName(name);
        this.setSymbol(symbol);
        this.setPrice(price);
    }
    
    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getSymbol()
    {
        return symbol;
    }
    public void setSymbol(String symbol)
    {
        this.symbol = symbol;
    }
    public double getPrice()
    {
        return price;
    }
    public void setPrice(double price)
    {
        this.price = price;
    }
    
    public boolean equals(Object obj)
    {
        if(obj instanceof Stock)
        {
            if(((Stock)obj).getName() == this.getName())
            {
                return true;
            } 
        }
        return false;
    }
    
    public int hashCode()
    {
        return this.getId().hashCode() + this.getName().hashCode() + this.getSymbol().hashCode();
    }

    public String toString(){


        return name + ": $" + price;

    }
}
