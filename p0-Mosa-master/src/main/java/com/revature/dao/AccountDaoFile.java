package com.revature.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Account;
import com.revature.beans.User;

/**
 * Implementation of AccountDAO which reads/writes to files
 */
public class AccountDaoFile implements AccountDao {
	// use this file location to persist the data to
	public static String fileLocation = "C:\\Users\\hamid\\Downloads\\p0-Mosa-master\\src\\output\\Users.txt";
	private static File userFile = new File(fileLocation);
	//private static int id = 0;
	public static List<Account> usersList = new ArrayList<Account>();
	public Account addAccount(Account a) {
		// TODO Auto-generated method stub
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(userFile))) {
			oos.writeObject(a);
			System.out.println("Opened Account!!!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return a;
	}

	public Account getAccount(Integer actId) {
		// TODO Auto-generated method stub
		Account requestedUser = null;
		for (Account a : usersList) {
			if (a.getId() == actId)
				requestedUser = a;
		}
		return requestedUser;
	}

	public List<Account> getAccounts() {
		// TODO Auto-generated method stub
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(userFile))) {
			do {
				usersList.add((Account) ois.readObject());
			} while (ois.readObject() != null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return usersList;
	}

	public List<Account> getAccountsByUser(User u) {
		// TODO Auto-generated method stub

		return null;
	}

	public Account updateAccount(Account a) {
		// TODO Auto-generated method stub
		usersList = getAccounts();
		
		for(Account acc: usersList) {
			if(acc.getId().equals(a.getId())) {
				break;
			}
		}
		return a;
	}

	public boolean removeAccount(Account a) {
		// TODO Auto-generated method stub
		return false;
	}

}
