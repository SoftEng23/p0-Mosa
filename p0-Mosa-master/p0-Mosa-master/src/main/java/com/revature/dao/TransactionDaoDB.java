package com.revature.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Account;
import com.revature.beans.Transaction;
import com.revature.beans.Transaction.TransactionType;
import com.revature.utils.ConnectionUtil;

public class TransactionDaoDB implements TransactionDao {
	
	private static Connection conn;
	private static Statement stmt;
	private static PreparedStatement pstmt;
	private static ResultSet rs;
	
	public TransactionDaoDB() {
			conn = ConnectionUtil.getConnection();
	}
	public static void getConnection() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/p0","root","Softw@re23!");
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Transaction> getAllTransactions() {
		// TODO Auto-generated method stub
		getConnection();
		List<Transaction> transactionList = new ArrayList<Transaction>();
		String query = "select * from transactions";
		AccountDaoDB acctdb = new AccountDaoDB();
		try { 
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
					Transaction transaction = new Transaction();
					transaction.setType((TransactionType) rs.getObject("tranaction_type"));
					transaction.setSender((Account) rs.getObject("from_acc_id"));
					if ((TransactionType) rs.getObject("tranaction_type") == TransactionType.TRANSFER) {;
						transaction.setRecipient((Account) rs.getObject("to_acct_id"));
					}
					transaction.setTimestamp((LocalDateTime)rs.getObject("timestamp"));
					transaction.setAmount(rs.getDouble("amount"));
					String type = rs.getString("type");
					TransactionType enumVal = TransactionType.valueOf(type);
					transaction.setType(enumVal);
					transactionList.add(transaction);
				}
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			return transactionList;
		}


	@Override
	public Transaction getTransaction(int transactionId) {
		// TODO Auto-generated method stub
		return null;
	}

}
