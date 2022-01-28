package service;

import service.account.AccountService;
import service.account.IAccountService;
import service.database.DatabaseService;
import service.database.IDatabaseService;
import service.manager.IManagerReviewService;
import service.manager.ManagerReviewService;
import service.transaction.ITransactionService;
import service.transaction.TransactionService;
import service.user.IUserService;
import service.user.UserService;

public class ServiceManager
{
    public static IDatabaseService getDatabaseService()
    {
        return DatabaseService.getInstance();
    }

    public static IAccountService getAccountService()
    {
        return AccountService.getInstance();
    }
    
    public static IUserService getUserService()
    {
        return UserService.getInstance();
    }
    
    public static IManagerReviewService getManagerReviewService() 
    {
        return ManagerReviewService.getInstance();
    }
    
    public static ITransactionService getTransactionService()
    {
        return TransactionService.getInstance();
    }
}
