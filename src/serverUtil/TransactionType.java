package serverUtil;

public enum TransactionType
{
    TRANSFER,
    DEPOSIT,
    WITHDRAW,
    SELL,
    BUY;

    public String toString()
    {
        return super.toString().toLowerCase();
    }
    
    public static TransactionType toTransactionType(String type)
    {
    	TransactionType res = null;
    	switch(type)
    	{
    	case "TRANSFER":
        case "transfer":
    		res = TRANSFER;
            break;
    	case "DEPOSIT":
        case "deposit":
    		res = DEPOSIT;
            break;
    	case "WITHDRAW":
        case "withdraw":
    		res = WITHDRAW;
            break;
    	case "SELL":
        case "sell":
    		res = SELL;
            break;
    	case "BUY":
        case "buy":
    		res = BUY;
            break;
    	}
    	return res;
    }
}
