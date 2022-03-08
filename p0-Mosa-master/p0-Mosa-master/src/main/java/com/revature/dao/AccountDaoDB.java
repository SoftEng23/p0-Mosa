package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.revature.beans.Account;
import com.revature.beans.Transaction;
import com.revature.beans.Account.AccountType;
import com.revature.beans.Transaction.TransactionType;
import com.revature.beans.User;
import com.revature.utils.ConnectionUtil;

/**
 * Implementation of AccountDAO which reads/writes to a database
 */

public class AccountDaoDB implements AccountDao {
	private static Connection conn;
	private static Statement stmt;
	private static PreparedStatement pstmt;
	private static ResultSet rs;
	
	public AccountDaoDB() {
		conn = ConnectionUtil.getConnection();
	}
	

	public Account addAccount(Account a) {
		// TODO Auto-generated method stub
		String query = "insert into account (owner_id,balance,account_type,approved)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, a.getOwnerId());
			pstmt.setDouble(2, a.getBalance());
			pstmt.setObject(3, a.getType());
			pstmt.setBoolean(4, a.isApproved());
			pstmt.executeUpdate();

		} catch (SQLException e){
			e.printStackTrace();
		}
		return a;
	}

	public Account getAccount(Integer actId) {
		// TODO Auto-generated method stub
		String query = "select * from account where id="+actId.intValue();
		Account a = new Account();	
		try {
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery(query);
			if(rs.next()) {
				a.setId(rs.getInt("id"));
				a.setOwnerId(rs.getInt("owner_id"));
				a.setBalance(rs.getDouble("balance"));
				a.setType((AccountType) rs.getObject("account_type"));
				a.setApproved(rs.getBoolean("approved"));
			}
			
		} catch (SQLException e){
			e.printStackTrace();
		}
		return a;
	}

	public List<Account> getAccounts() {
		// TODO Auto-generated method stub
		String query = "select * from account";
		Account a = new Account();
		List<Account> accountList = new ArrayList<Account>();
			try {
				stmt = conn.prepareStatement(query);
				rs = stmt.executeQuery(query);
				if(rs.next()) {
					a.setId(rs.getInt("id"));
					a.setOwnerId(rs.getInt("owner_id"));
					a.setBalance(rs.getDouble("balance"));
					a.setType((AccountType) rs.getObject("account_type"));
					a.setApproved(rs.getBoolean("approved"));
					accountList.add(a);
				}
				
			} catch (SQLException e){
				e.printStackTrace();
			}
			return accountList;
	}

	public List<Account> getAccountsByUser(User u) {
		// TODO Auto-generated method stub
		String query = "select * from account where owner_id=" + u.getId();
		List<Account> accountList = new ArrayList<Account>();
			try {
				stmt = conn.prepareStatement(query);
				rs = stmt.executeQuery(query);
				if(rs.next()) {
					Account a = new Account();
					a.setId(rs.getInt("id"));
					a.setOwnerId(rs.getInt("owner_id"));
					a.setBalance(rs.getDouble("balance"));
					a.setType((AccountType) rs.getObject("account_type"));
					a.setApproved(rs.getBoolean("approved"));
					accountList.add(a);
				}
				
			} catch (SQLException e){
				e.printStackTrace();
			}
			return accountList;
	}

	public Transaction addTransaction(Transaction t) {
		String query = "insert into transactions (accountId, recipientid, amount, type, timestamp) values (?, ?, ?, ?, ?)";
		Transaction tran1 = new Transaction();
		Transaction tran2 = new Transaction();
		Transaction tran3 = new Transaction();
		tran1.setType(TransactionType.DEPOSIT);
		tran2.setType(TransactionType.WITHDRAWAL);
		tran3.setType(TransactionType.TRANSFER);
		String type = null;
		if(t.getType().equals(tran1.getType())) {
			type = "DEPOSIT";
		} else if(t.getType().equals(tran2.getType())) {
			type = "WITHDRAWAL";
			
		}else if(t.getType().equals(tran3.getType())) {
			type = "TRANSFER";
		}
		
		int senderID = t.getSender().getId();
		int recipientID = Objects.isNull(t.getRecipient()) ? 0 : t.getRecipient().getId();
		System.out.println(recipientID);
		
		
	try {
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, senderID);
		pstmt.setInt(2, recipientID);
		pstmt.setDouble(3, t.getAmount().doubleValue());
		pstmt.setString(4, type);
		pstmt.setObject(5, t.getTimestamp());
		pstmt.executeUpdate();
		if (rs != null)
			rs.close();
		if(pstmt != null)
			stmt.close();
	} catch (Exception e) {
		e.printStackTrace();
		
	}
		return t;
		
	}
	public Account updateAccount(Account a) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean removeAccount(Account a) {
		// TODO Auto-generated method stub
		return false;
	}

}
