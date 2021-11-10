package testing;

import dao.AccountDaoImp;
import dao.ReimbursementDaoImp;
import models.Account;
import models.Reimbursement;

public class Tester {
	
	static AccountDaoImp dao = new AccountDaoImp();
	static ReimbursementDaoImp dao2 = new ReimbursementDaoImp();
	
	public static void main(String[] args) {
		String consoleMessage = dao.CreateAccount("Mia", "Baswell", "Malia", "Burns", "maliaburns@example.com",  1).toString();
		System.out.println(consoleMessage);
		
//		String consoleMessage = dao.Login("John", "Jacob").toString();
//		System.out.println(consoleMessage);
		
//		Reimbursement testReimbursement = new Reimbursement(2500, "Purchase of a new server after company credit card failed", "C73DWTaSodNyPnlB2kJUmnjkzlEmlqHYhNgDzOsENLdkI5rgTG", 3, 4);
//		boolean consoleMessage = dao2.CreateReimbursement(testReimbursement);
//		System.out.println(consoleMessage);
		
//		String consoleMessage = dao2.GetAllReimbursements().toString();
//		System.out.println(consoleMessage);
		
//		String consoleMessage = dao2.GetAllReimbursementsByUserId("C73DWTaSodNyPnlB2kJUmnjkzlEmlqHYhNgDzOsENLdkI5rgTG").toString();
//		System.out.println(consoleMessage);
		
//		String consoleMessage = dao2.GetReimbursementById(2).toString();
//		System.out.println(consoleMessage);
		
//		boolean consoleMessage = dao2.ResolveReimbursement(2, 1, "C73DWTaSodNyPnlB2kJUmnjkzlEmlqHYhNgDzOsENLdkI5rgTG");
//		System.out.println(consoleMessage);
	}
	
}
