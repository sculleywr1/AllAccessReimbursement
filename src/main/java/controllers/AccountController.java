package controllers;

import java.util.List;
import java.util.Objects;

import io.javalin.http.Handler;
import models.Account;
import services.AccountService;
import services.AccountServiceImp;

public class AccountController {
	static AccountService service = new AccountServiceImp();

	public AccountController() {
		// TODO Auto-generated constructor stub
	}
	
	public static Handler login = ctx -> {
		
		System.out.println(ctx.toString());
		Account fromFrontEnd = ctx.bodyAsClass(Account.class);
		String userName = fromFrontEnd.getUserName();
		String password = fromFrontEnd.getPassword();
		
		System.out.println(userName + password);
		
		
		
		boolean successfulLogin = false;
		
		Account temp = service.Login(userName, password);
		
		String tempName = temp.getUserName();
		
		System.out.println(temp.toString());
		System.out.println(tempName);
		
		if (userName.equals(tempName) )
		{
			System.out.println("Somehow in the successful logic");
			successfulLogin = true;
			ctx.sessionAttribute("currentUserId", temp.getUserId());
			ctx.sessionAttribute("currentUserName", temp.getUserName());
			ctx.sessionAttribute("currentUserRole", temp.getRoleId());
		}
		
		
		if (successfulLogin)
		{
			ctx.header("Access-Control-Allow-Origin");
			ctx.status(200);
		}
		else
		{
			ctx.status(403);
		}
	};
	
	public static Handler logout = ctx -> {
		
		System.out.println("logged out");
		ctx.consumeSessionAttribute("currentUserId");
		ctx.consumeSessionAttribute("currentUserName");
		ctx.consumeSessionAttribute("currentUserRole");
	};
	
	public static Handler getAllUsers = ctx -> {
		
		
		if (ctx.sessionAttributeMap().toString().equals("{}"))
		{
			ctx.status(403);
		}
		else if (ctx.sessionAttribute("currentUserRole").equals(1))
		{
			List<Account> allUsers = service.GetAllAccounts();
			ctx.json(allUsers);
		}
		else
		{
			ctx.status(403);
		}
	};
	
	
	public static Handler getUserById = ctx -> {
		String id = Objects.requireNonNull(ctx.queryParam("id"));
		Account user = service.GetAccountById(id);
		if (user == null)
		{
			ctx.html("Not Found");
		}
		else
		{
			ctx.json(user);
		}
	};
}
