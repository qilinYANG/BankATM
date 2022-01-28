package entity.user;

import java.util.Date;
import java.util.List;

import entity.account.*;
public class Customer extends AbsATMUser
{
    public Customer(String userName, String firstName, String lastName, Date dob, String email, int creditScore, String password)
    {
        super(userName, firstName, lastName, dob, email, password, creditScore, false);
    }
}
