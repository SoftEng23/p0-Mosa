package com.revature.services;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.revature.beans.User;
import com.revature.beans.User.UserType;
import com.revature.utils.ConnectionUtil;

public class UserServiceImpl implements UsersService {

	private static Connection conn;
	private static Statement stmt;
	private static PreparedStatement pstmt;
	private static ResultSet rs;

	public UserServiceImpl() {
		conn = ConnectionUtil.getConnection();
	}

	@Override
	public boolean login(String username, String password) {
		// TODO Auto-generated method stub
		String query = "select * from p0_user where username=? and password=?";
		boolean status = false;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next())
				status = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return status;
	}

	@Override
	public boolean register(User user) {
		// TODO Auto-generated method stub
		String query = "insert into p0_user () values (?,?,?,?,?)";
		boolean status = false;
		int insertStatus = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getFirstName());
			pstmt.setString(2, user.getLastName());
			pstmt.setString(3, user.getUsername());
			pstmt.setString(4, user.getPassword());
			pstmt.setObject(5, UserType.CUSTOMER);
			insertStatus = pstmt.executeUpdate();
			if (insertStatus > 0)
				status = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return status;
	}

	@Override
	public boolean logout() {
		// TODO Auto-generated method stub
		return false;
	}
	public static void closeResource() {

		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}