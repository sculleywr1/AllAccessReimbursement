package controllers;

import java.util.List;

import io.javalin.http.Handler;
import models.Reimbursement;
import services.ReimbursementService;
import services.ReimbursementServiceImp;


public class ReimbursementController {
	static ReimbursementService service = new ReimbursementServiceImp();
	
	public static Handler createReimb = ctx -> {
		
		
		Reimbursement temp = ctx.bodyAsClass(Reimbursement.class);
		Double amount = temp.getAmount();
		String description = temp.getDescription();
		String authorId = ctx.sessionAttribute("currentUserId");
		int status = temp.getStatusId();
		int type = temp.getTypeId();
		
		Reimbursement finalReimb = new Reimbursement(amount, description, authorId, status, type);
		
		System.out.println(ctx.sessionAttributeMap().toString());
		if (ctx.sessionAttributeMap().toString().equals("{}"))
		{
			ctx.status(403);
		}
		else if (ctx.sessionAttribute("currentUserRole").equals(1) || ctx.sessionAttribute("currentUserRole").equals(2))
		{
			boolean didItPost = service.CreateReimbursement(finalReimb);
			if(didItPost)
			{
				ctx.status(201);
			}
			else
			{
				ctx.status(400);
			}
		}
		else
		{
			ctx.status(403);
		}
	};
	
	public static Handler getAllReimb = ctx -> {
		if (ctx.sessionAttributeMap().toString().equals("{}"))
		{
			ctx.status(403);
		}
		else if (ctx.sessionAttribute("currentUserRole").equals(1))
		{
			List<Reimbursement> allReimbursements = service.GetAllReimbursements();
			ctx.json(allReimbursements);
		}
		else
		{
			ctx.status(403);
		}
		
		
	};
	
	public static Handler getreimbById = ctx -> {
		System.out.println("we made it!");
		int id = Integer.parseInt(ctx.pathParam("id"));
		Reimbursement reimb = service.GetReimbursementById(id);
		
		if (ctx.sessionAttributeMap().toString().equals("{}"))
		{
			ctx.status(403);
		}
		else if (ctx.sessionAttribute("currentUserRole").equals(1) || ctx.sessionAttribute("currentUserId").equals(reimb.getAuthorId()))
		{
			
			ctx.json(reimb);
		}
		else
		{
			ctx.status(403);
		}
		
		
	};
	
	public static Handler getReimbByUser = ctx -> {
		String id = ctx.formParam("id");
		
		if (ctx.sessionAttributeMap().toString().equals("{}"))
		{
			ctx.status(403);
		}
		else if (ctx.sessionAttribute("currentUserRole").equals(1))
		{
			List<Reimbursement> reimb = service.GetAllReimbursementsByUserId(id);
			ctx.json(reimb);
		}
		else
		{
			ctx.status(403);
		}
		
		
	};
	
	public static Handler getMyReimbs = ctx -> {
		
		System.out.println("in My reimbursements");
		String id = ctx.sessionAttribute("currentUserId");
		String cookie = ctx.header("Cookie");
		System.out.println(id + " " + cookie);
		if (ctx.sessionAttributeMap().toString().equals("{}"))
		{
			ctx.status(403);
		}
		else if (ctx.sessionAttribute("currentUserId").equals(id))
		{
			List<Reimbursement> reimb = service.GetAllReimbursementsByUserId(id);
			ctx.json(reimb);
		}
		else
		{
			ctx.status(403);
		}
	};
	
	public static Handler resolveReimb = ctx -> {
		Reimbursement reimb = ctx.bodyAsClass(Reimbursement.class);
		int reimbid = reimb.getReimbId();
		int statusCode = reimb.getStatusId();
		String userId = ctx.formParam("userId");
		
		if (ctx.sessionAttributeMap().toString().equals("{}"))
		{
			ctx.status(403);
		}
		else if (ctx.sessionAttribute("currentUserRole").equals(1))
		{
			boolean resolved = service.ResolveReimbursement(reimbid, statusCode, userId);
			if ( resolved )
			{
				ctx.status(200);
			}
			else
			{
				ctx.status(400);
			}
		}
		else
		{
			ctx.status(403);
		}
	};
	
}
