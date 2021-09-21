package com.userdao;

import java.sql.*;

import org.postgresql.util.PSQLException;

import com.models.User;

public class UserDAO {
	private static final String AWS = "jdbc:postgresql://bank.cap91ijbfbvc.us-east-2.rds.amazonaws.com/postgres";
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "Lr0694275";

	private static final String INSERT_REGISTRATION = "INSERT INTO PendingRegistrations "
			+ "(username, passcode, accountTypeSelect) VALUES (?,?,?)";

	private static final String EMPLOYEE_LOGIN = "SELECT passcode FROM Employees WHERE username = ? LIMIT 1";

	public void registration(String username, String password, String accountType) throws SQLException {
		try (Connection conn = DriverManager.getConnection(AWS, USERNAME, PASSWORD);

				PreparedStatement preparedStatement = conn.prepareStatement(INSERT_REGISTRATION)) {

			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, accountType);

			preparedStatement.executeUpdate();

			System.out.println("Thank you for registering. Your application will be reviewed soon.");

		} catch (PSQLException e) {
			System.out.println("You've already registered.");
			System.out.println("Please wait for an employee to approve your account.");
		}
	}

	public String employeeLogin(String username) throws SQLException {

		try {
			
			Connection conn = DriverManager.getConnection(AWS, USERNAME, PASSWORD);
			
			PreparedStatement ps = conn.prepareStatement(EMPLOYEE_LOGIN);
			
			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();
			
			String correctPassword = null;
			
			while(rs.next()) {
				correctPassword = rs.getString("passcode");
				return correctPassword;
			}
			
			System.out.println("Employee username does not exist.");
			System.out.println("Please try again.");
			System.exit(0);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return username;
		
	}

//
//	ConnectionEstablisher connectionEstablisher = new ConnectionEstablisher();
//
//	public void test() {
//		Connection connection = null;
//		Statement stmt = null;
//		try {
//			connection = DriverManager.getConnection(AWS, USERNAME, PASSWORD);
//
//			stmt = connection.createStatement();
//			stmt.execute("INSERT INTO PendingRegistrations (username,accountTypeSelect) " + "VALUES ('bob,'aaaaaaa')");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public User selectUserByUsername(String username) {
//
//		String sql = "SELECT * FROM Customers WHERE username = ?";
//
//		PreparedStatement ps;
//
//		User u = new User();
//
//		try {
//
//			Connection connection = connectionEstablisher.connect();
//
//			ps = connection.prepareStatement(sql);
//
//			ps.setString(1, username);
//
//			ResultSet rs = ps.executeQuery();
//
//			while (rs.next()) {
//				u.setUsername(rs.getString("username"));
//				u.setPassword(rs.getString("passcode"));
//			}
//
//			connection.close();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return u;
//	}

}
