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
import java.util.Objects;

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
		//AccountDaoDB acctdb = new AccountDaoDB();
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

	@Override
	public Transaction getTransaction(int transactionId) {
		// TODO Auto-generated method stub
		return null;
	}

}
