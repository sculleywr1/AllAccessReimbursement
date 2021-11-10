package services;

import java.util.List;

import dao.AccountDao;
import dao.AccountDaoImp;
import models.Account;

public class AccountServiceImp implements AccountService {

	AccountDao dao = new AccountDaoImp();
	@Override
	public Account CreateAccount(String userName, String password, String firstName, String lastName, String email,
			int roleId) {
		// TODO Auto-generated method stub
		return dao.CreateAccount(userName, password, firstName, lastName, email, roleId);
	}

	@Override
	public Account Login(String userName, String password) {
		// TODO Auto-generated method stub
		return dao.Login(userName, password);
	}

	@Override
	public Account GetAccountById(String userId) {
		// TODO Auto-generated method stub
		return dao.GetAccountById(userId);
	}

	@Override
	public List<Account> GetAllAccounts() {
		// TODO Auto-generated method stub
		return dao.GetAllAccounts();
	}

	

}
