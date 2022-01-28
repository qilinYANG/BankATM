package entity;

public class Interest
{
    private String id;
    private String name;
    private double value;
    private int    cycle; // how many month
    public Interest(String id, String name, double value, int cycle)
    {
        this.setId(id);
        this.setName(name);
        this.setValue(value);
        this.setCycle(cycle);
    }
    public Interest(String name, double value, int cycle)
    {
        this(null, name, value, cycle);
    }
    public double getValue()
    {
        return value;
    }
    public void setValue(double value)
    {
        this.value = value;
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
    public int getCycle()
    {
        return cycle;
    }
    public void setCycle(int cycle)
    {
        this.cycle = cycle;
    }
    @Override
    public String toString() {
        
        return value+"";
    }
}
