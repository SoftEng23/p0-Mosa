package com.revature.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import com.revature.beans.Transaction;

public class TransactionDaoFile implements TransactionDao {
	
	public static String fileLocation = "C:\\Users\\hamid\\Downloads\\p0-Mosa-master\\src\\output\\Users.txt";
	private static File userFile = new File(fileLocation);
	//private static int id = 0;
	public static List<Transaction> usersList = new ArrayList<Transaction>();
	
	public List<Transaction> getAllTransactions() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(userFile))) {
			do {
				usersList.add((Transaction) ois.readObject());
			} while (ois.readObject() != null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return usersList;
	}
	public Transaction addTransaction(Transaction t) {
		// TODO Auto-generated method stub
				try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(userFile))) {
					oos.writeObject(t);
					System.out.println("Transaction added!!!");
				} catch (IOException e) {
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