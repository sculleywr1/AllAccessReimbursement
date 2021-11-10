package services;

import java.util.List;

import models.Account;

public interface AccountService {
	public Account CreateAccount(String userName, String password, String firstName, String lastName, String email, int roleId);
	public Account Login(String userName, String password);
	public Account GetAccountById(String userId);
	public List<Account> GetAllAccounts();
}
