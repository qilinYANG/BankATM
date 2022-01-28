package entity.user;

import java.util.Date;
import java.util.List;

import entity.account.AccountCollection;
import entity.account.IAccount;

public abstract class AbsATMUser implements IUser
{
    private String            userID, userName, password, firstName, lastName, email;
    private Date              birthDay, creationTime;
    private boolean           isAdmin;
    private int               creditScore;
    private AccountCollection accountCollection;

    public AbsATMUser(String userName, String firstName, String lastName, Date dob, String email, String password, int creditScore, boolean isAdmin)
    {
        this.setUsername(userName);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setBirthDay(dob);
        this.setEmail(email);
        this.setPassword(password);
        this.setAdmin(isAdmin);
        this.setCreationTime(new Date());
        this.setCreditScore(creditScore);
        this.setAccountCollection(new AccountCollection());
    }
    
    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getUserID()
    {
        return this.userID;
    }
    
    public void setUserID(String id)
    {
        this.userID = id;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
    
    public boolean isAdmin()
    {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin)
    {
        this.isAdmin = isAdmin;
    }

    public AccountCollection getAccountCollection() {
        return accountCollection;
    }
    
    public List<IAccount> getAccountList() {
        return accountCollection.getAllAccounts();
    }

    public void setAccountCollection(AccountCollection accountList) {
        this.accountCollection = accountList;
    }

    public Date getCreationTime()
    {
        return creationTime;
    }

    public void setCreationTime(Date creationTime)
    {
        this.creationTime = creationTime;
    }

    public int getCreditScore()
    {
        return creditScore;
    }

    public void setCreditScore(int creditScore)
    {
        this.creditScore = creditScore;
    }
}
