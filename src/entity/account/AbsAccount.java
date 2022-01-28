package entity.account;

import java.util.Date;

import serverUtil.AccountType;

/**
 * root class for accounts
 */
public abstract class AbsAccount implements IAccount
{
    private String      accountId;
    private AccountType accountType;
    private String      accountOwnerID;
    private Date        createDate;
    private boolean     isActive;
    public AbsAccount(String accountId, AccountType accountType, String accountOwnerID)
    {
        this.setAccountNumber(accountId);
        this.accountType = accountType;
        this.accountOwnerID = accountOwnerID;
        this.setCreateDate(new Date());
    }
    public AbsAccount(AccountType accountType, String accountOwnerID)
    {
        this(null, accountType, accountOwnerID);
    }
    
    public void setAccountNumber(String accountId)
    {
        this.accountId = accountId;
    }


    @Override
    public String getAccountNumber()
    {
        return accountId;
    }
    @Override
    public AccountType getAccountType()
    {
        return this.accountType;
    }
    @Override
    public String getAccountOwner()
    {
        return this.accountOwnerID;
    }
    public Date getCreateDate()
    {
        return createDate;
    }
    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }
    @Override
    public String toString() {
        return accountType + " account: " + accountId;
    }
    
    public int hashCode()
    {
        return this.getAccountNumber().hashCode() + this.getAccountType().hashCode();
    }
    public boolean isActive()
    {
        return isActive;
    }
    public void setActive(boolean isActive)
    {
        this.isActive = isActive;
    }
}
