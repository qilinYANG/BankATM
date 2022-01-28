package Controller;

import entity.StockTable;
import service.ServiceManager;
import view.Welcome;

/**
 * Controller that instancate service and GUI
 */
public class ATMController
{
    private static ATMController instance = null;
    private ATMController(){}
    
    public static ATMController getInstance()
    {
        if(instance == null)
        {
            instance = new ATMController();
        }
        return instance;
    }
    
    public void run()
    {
        Welcome start = new Welcome();
        StockTable.initInstance(ServiceManager.getDatabaseService().getMarketStocks());
    }
}
