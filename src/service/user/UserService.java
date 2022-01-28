package service.user;

import java.util.Collection;
import java.util.Date;

import entity.user.Customer;
import service.ServiceManager;
import service.account.IAccountService;

public class UserService implements IUserService
{
    private static UserService userService = null;
    private        Customer    costomer;
    
    private UserService(){}
    
    public static synchronized UserService getInstance()
    {
        if(userService == null)
        {
            userService = new UserService();
        }
        return userService;
    }
    
    @Override
    public String createUser(String userName, String password, String email, Date dob, String firstName, String lastName, int creditScore)
    {
        if(!checkUserNameAvailable(userName))
        {
            return null;
        }
        Customer customer = new Customer(userName, firstName, lastName, dob, email, creditScore, password);
        String   userid   = ServiceManager.getDatabaseService().create(customer);
        customer.setUserID(userid);
        if(userid != null)
        {
            this.logIn(userName, password);
        }
        return userid;
    }
    
    @Override
    public Collection<Customer> getAllUsers()
    {
        return ServiceManager.getDatabaseService().getAllCustomer();
    }

    @Override
    public boolean checkUserNameAvailable(String userName)
    {
        return !ServiceManager.getDatabaseService().checkUserNameExist(userName);
    }
    
    @Override
    public boolean checkIsAdmin(String userName)
    {
        return ServiceManager.getDatabaseService().checkIsAdmin(userName);
    }

    @Override
    public boolean logIn(String userName, String password)
    {
        Customer customer = ServiceManager.getDatabaseService().getCustomer(userName, password);
        if(customer == null)
        {
            return false;
        }
        this.setCostomer(customer);
        IAccountService accountService = ServiceManager.getAccountService();
        accountService.setAccountCollection(customer.getAccountCollection());
        return true;
    }

    @Override
    public boolean logOut(String userName)
    {
        this.setCostomer(null);
        ServiceManager.getAccountService().reset();
        return true;
    }

    public Customer getCostomer()
    {
        return costomer;
    }

    public void setCostomer(Customer costomer)
    {
        this.costomer = costomer;
    }

    @Override
    public boolean isServiceAvailable()
    {
        return this.getCostomer() != null;
    }
}
