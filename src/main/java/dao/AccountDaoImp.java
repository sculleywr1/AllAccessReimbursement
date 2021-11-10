package dao;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.common.hash.Hashing;

import models.Account;

public class AccountDaoImp implements AccountDao {

	@Override
	public Account CreateAccount(String userName, String password, String firstName, String lastName, String email,
			int roleId) {
		// TODO Auto-generated method stub
		Account A = new Account();
		String sha256hex = Hashing.sha256()
				  .hashString(password, StandardCharsets.UTF_8)
				  .toString();
		try (Connection conn = CustomClassFactory.getConnection()) {

			String sql = "INSERT INTO users (username, pass, firstname, lastname, email, roleid) VALUES (?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, sha256hex);
			ps.setString(3, firstName);
			ps.setString(4, lastName);
			ps.setString(5, email);
			ps.setInt(6, roleId);

			System.out.println(ps.toString());
			int i = ps.executeUpdate(); // <---update not query. this line is what sends the information to the DB
			if (i == 0) {
				System.out.println("Sorry, database was not updated. Returning to menu");
				return null;
			}

		} catch (SQLException e) {
			System.out.println("Sorry, database was not contacted. Bring your developer coffee. In the Insert Statement");
			e.printStackTrace();
			return null;
		}
		
		try (Connection conn = CustomClassFactory.getConnection()) {

			String sql = "SELECT userid, username, roleid FROM users WHERE username = ? AND pass = ?;";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, sha256hex);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				A = new Account(rs.getString(1), rs.getString(2), rs.getInt(3));
			}

		} catch (SQLException e) {
			System.out.println("Sorry, database was not contacted. Bring your developer coffee. In the return request from the Create Account function in the DAO");
			return null;
		}

		return A;
	}

	@Override
	public Account Login(String userName, String password) {
		Account A = new Account();
		String sha256hex = Hashing.sha256()
				  .hashString(password, StandardCharsets.UTF_8)
				  .toString();
		
		try (Connection conn = CustomClassFactory.getConnection()) {

			String sql = "SELECT userid, username, roleid FROM users WHERE username = ? AND pass = ?;";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, sha256hex);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				A = new Account(rs.getString(1), rs.getString(2), rs.getInt(3));
			}

		} catch (SQLException e) {
			System.out.println("Sorry, database was not contacted. Bring your developer coffee. In the return request from the Create Account function in the DAO");
			e.printStackTrace();
			return null;
			
		}

		return A;
	}

	@Override
	public Account GetAccountById(String userId) {
		Account A = new Account();
		
		try (Connection conn = CustomClassFactory.getConnection()) {

			String sql = "SELECT userid, username, roleid FROM users WHERE userid = ?;";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				A = new Account(rs.getString(1), rs.getString(2), rs.getInt(3));
			}

		} catch (SQLException e) {
			System.out.println("Sorry, database was not contacted. Bring your developer coffee. In the return request from the login function in the DAO");
			return null;
		}

		return A;
	}

	@Override
	public List<Account> GetAllAccounts() {
		List<Account> A = new ArrayList<Account>();
		
		try (Connection conn = CustomClassFactory.getConnection()) {

			String sql = "SELECT userid, username, roleid FROM users;";

			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				A.add(new Account(rs.getString(1), rs.getString(2), rs.getInt(3)));
			}

		} catch (SQLException e) {
			System.out.println("Sorry, database was not contacted. Bring your developer coffee. In the return request from the login function in the DAO");
			return null;
		}

		return A;
		
	}

}
