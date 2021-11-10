package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Reimbursement;

public class ReimbursementDaoImp implements ReimbursementDao {

	@Override
	public boolean CreateReimbursement(Reimbursement reimb) {
		try (Connection conn = CustomClassFactory.getConnection()) {

			
			String sql = "INSERT INTO reimbursements (amount, description, author, statusid, typeid) VALUES (?, ?, ?, ?, ?);";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, reimb.getAmount());
			ps.setString(2, reimb.getDescription());
			ps.setString(3, reimb.getAuthorId());
			ps.setInt(4, reimb.getStatusId());
			ps.setInt(5, reimb.getTypeId());

			int rs = ps.executeUpdate();

			if ( rs == 0 ) {
				System.out.println("Sorry. No updates were made in the system. Please check that you have the right information");
				return false;
			}

		} catch (SQLException e) {
			System.out.println("Sorry, database was not contacted. Bring your developer coffee. In the return request from the login function in the DAO");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<Reimbursement> GetAllReimbursements() {
		List<Reimbursement> R = new ArrayList<Reimbursement>();
		
		try (Connection conn = CustomClassFactory.getConnection()) {

			String sql = "SELECT reimbid, amount, submitted, resolved, description, author, resolver, statusid, typeid FROM reimbursements;";

			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				R.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), 				rs.getInt(9)));
			}

		} catch (SQLException e) {
			System.out.println("Sorry, database was not contacted. Bring your developer coffee. In the return request from the login function in the DAO");
			e.printStackTrace();
			return null;
		}

		return R;
	}

	@Override
	public List<Reimbursement> GetAllReimbursementsByUserId(String userId) {
		List<Reimbursement> R = new ArrayList<Reimbursement>();
		
		try (Connection conn = CustomClassFactory.getConnection()) {

			String sql = "SELECT reimbid, amount, submitted, resolved, description, author, resolver, statusid, typeid FROM reimbursements WHERE author = ?;";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				R.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), 				rs.getInt(9)));
			}

		} catch (SQLException e) {
			System.out.println("Sorry, database was not contacted. Bring your developer coffee. In the return request from the login function in the DAO");
			return null;
		}

		return R;
	}

	@Override
	public Reimbursement GetReimbursementById(int reimbId) {
		Reimbursement R = new Reimbursement();
		
		try (Connection conn = CustomClassFactory.getConnection()) {

			String sql = "SELECT reimbid, amount, submitted, resolved, description, author, resolver, statusid, typeid FROM reimbursements WHERE reimbid = ?;";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, reimbId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				R = new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), 				rs.getInt(9));
			}

		} catch (SQLException e) {
			System.out.println("Sorry, database was not contacted. Bring your developer coffee. In the return request from the login function in the DAO");
			return null;
		}

		return R;
	}

	@Override
	public boolean ResolveReimbursement(int reimbId, int statusCode, String resolverid) {
		
		try (Connection conn = CustomClassFactory.getConnection()) {

			String sql = "UPDATE reimbursements SET statusid = ?, resolver = ?, resolved = NOW() WHERE reimbid = ?;";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, statusCode);
			ps.setString(2, resolverid);
			ps.setInt(3, reimbId);

			int rs = ps.executeUpdate();

			if ( rs == 0 ) {
				System.out.println("Sorry. No updates were made in the system. Please check that you have the right information");
				return false;
			}

		} catch (SQLException e) {
			System.out.println("Sorry, database was not contacted. Bring your developer coffee. In the return request from the login function in the DAO");
			e.printStackTrace();
			return false;
		}
		return true;
		
	}


}
