package service.user;

import java.util.Collection;
import java.util.Date;

import entity.user.Customer;
import service.IService;

public interface IUserService extends IService
{
    public String  createUser(String userName, String password,String email, Date dob, String firstName, String lastName, int creditScore);
    public boolean checkUserNameAvailable(String userName);
    public boolean checkIsAdmin(String userName);
    public boolean logIn(String userName, String password);
    public boolean logOut(String userName);
    public Collection<Customer> getAllUsers();
}
