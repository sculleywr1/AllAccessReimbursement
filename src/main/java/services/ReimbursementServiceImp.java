package services;

import java.util.List;

import dao.ReimbursementDao;
import dao.ReimbursementDaoImp;
import models.Reimbursement;

public class ReimbursementServiceImp implements ReimbursementService {

	ReimbursementDao dao = new ReimbursementDaoImp();
	@Override
	public boolean CreateReimbursement(Reimbursement reimb) {
		// TODO Auto-generated method stub
		return dao.CreateReimbursement(reimb);
	}

	@Override
	public List<Reimbursement> GetAllReimbursements() {
		// TODO Auto-generated method stub
		return dao.GetAllReimbursements();
	}

	@Override
	public List<Reimbursement> GetAllReimbursementsByUserId(String userId) {
		// TODO Auto-generated method stub
		return dao.GetAllReimbursementsByUserId(userId);
	}

	@Override
	public Reimbursement GetReimbursementById(int reimbId) {
		// TODO Auto-generated method stub
		return dao.GetReimbursementById(reimbId);
	}

	@Override
	public boolean ResolveReimbursement(int reimbId, int statusCode, String resolverID) {
		// TODO Auto-generated method stub
		return dao.ResolveReimbursement(reimbId, statusCode, resolverID);
	}

}
