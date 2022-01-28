package entity;

import java.util.Currency;

public class Balance
{
    private String id;
    private Currency currency;
    // value always positive
    private double   value;
    public Balance(Currency currency, double value)
    {
        this.setCurrency(currency);
        this.setValue(value);
    }
    
    // default new Currency("USD", "$")
    public Balance(double value)
    {
        this.setCurrency(Currency.getInstance("USD"));
        this.setValue(value);
    }
    
    public Currency getCurrency()
    {
        return currency;
    }
    public void setCurrency(Currency currency)
    {
        this.currency = currency;
    }
    public double addValue(double val)
    {
        this.setValue(this.getValue() + val);
        return this.getValue();
    }
    public double subTrackValue(double val)
    {
        this.setValue(this.getValue() - val);
        return this.getValue();
    }
    public double getValue()
    {
        return value;
    }
    public void setValue(double value)
    {
        this.value = value;
    }
    
    public static Balance addUp(Balance b1, Balance b2)
    {
        if(!b1.getCurrency().equals(b2.getCurrency()))
        {
            return null;
        }
        else
        {
            return new Balance(b1.getCurrency(), b1.getValue() + b2.getValue());
        }
    }
    
    @Override
    public String toString() {
        return " Balence: "+currency.toString()+value;
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
