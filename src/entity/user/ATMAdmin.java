package entity.user;

import java.util.Date;

public class ATMAdmin extends AbsATMUser
{   
    public ATMAdmin(String userName, String firstName, String lastName, Date dob, String email, int creditScore, String password) {
        super(userName, firstName, lastName, dob, email, password, creditScore, true);
    }
}
