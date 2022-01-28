package serverUtil;

import entity.account.IAccount;

public enum AccountType
{
    CHECKING,
    SAVING,
    LOAN,
    SECURITY;

    public String toString()
    {
        return super.toString().toLowerCase();
    }
    
    public static AccountType toAccountType(String type)
    {
        AccountType res = null;
        switch(type)
        {
        case "CHECKING":
        case "checking":
            res = CHECKING;
            break;
        case "SAVING":
        case "saving":
            res = SAVING;
            break;
        case "LOAN":
        case "loan":
            res = LOAN;
            break;
        case "SECURITY":
        case "security":
            res = SECURITY;
            break;
        }
        return res;
    }
    
    public static boolean isDepositAccount(IAccount account)
    {
        return account.getAccountType().equals(CHECKING) || account.getAccountType().equals(SAVING);
    }
}
