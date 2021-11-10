import controllers.AccountController;
import controllers.ReimbursementController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class MainDriver {

	public static void main(String[] args) {
		
		Javalin app = Javalin.create(config -> {
			config.addStaticFiles(staticFiles -> {
				staticFiles.directory = "/resources";
				staticFiles.hostedPath = "/";
				staticFiles.location = Location.CLASSPATH;
			});
		}).start(9003);
		app.get("/redirectDemo", (ctx) -> {
			ctx.redirect("/index.html");
		});
		
		//routes for users 
		app.get("/api/users", AccountController.getAllUsers);
		app.get("/api/user/id?{id}", AccountController.getUserById);
		app.post("/api/login", AccountController.login);
		app.get("/api/logout", AccountController.logout);
		
		//routes for reimbursements
		app.post("/api/reimbursements", ReimbursementController.createReimb);
		app.get("/api/reimbursements", ReimbursementController.getAllReimb);
		app.get("/api/Reimbursement/user?{userId}", ReimbursementController.getReimbByUser);
		app.get("/api/Reimbursement/{id}", ReimbursementController.getreimbById);
		app.put("/api/reimbursement/", ReimbursementController.resolveReimb);
		app.get("/api/reimbursement/mine", ReimbursementController.getMyReimbs);
		
		

	}

}
