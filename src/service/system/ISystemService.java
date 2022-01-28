package service.system;

import service.IService;

public interface ISystemService extends IService
{
    double distributeInterestToAccounts();
    double collectFeesFromAccounts();
}
