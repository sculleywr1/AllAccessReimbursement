package dao;

import java.util.List;

import models.Reimbursement;

public interface ReimbursementDao {
	public boolean CreateReimbursement(Reimbursement reimb);
	public List<Reimbursement> GetAllReimbursements();
	public List<Reimbursement> GetAllReimbursementsByUserId(String userId);
	public Reimbursement GetReimbursementById(int reimbId);
	public boolean ResolveReimbursement(int reimbId, int statusCode, String resolverID);
}
