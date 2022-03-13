package com.revature.services;

import com.revature.beans.Account;
import com.revature.beans.Transaction;
import com.revature.beans.User;
import com.revature.beans.Transaction.TransactionType;
import com.revature.dao.AccountDao;
import com.revature.dao.AccountDaoDB;
import com.revature.dao.AccountDaoFile;
import com.revature.dao.TransactionDaoFile;
import com.revature.exceptions.OverdraftException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class should contain the business logic for performing operations on Accounts
 */
public class AccountService {
	
	public AccountDao actDao;
	public static final double STARTING_BALANCE = 25d;
	
	
	public AccountService(AccountDao dao) {
		this.actDao = dao;
	}
	
	/**
	 * Withdraws funds from the specified account
	 * @throws OverdraftException if amount is greater than the account balance
	 * @throws UnsupportedOperationException if amount is negative
	 */
	public void withdraw(Account a, Double amount) {
		if (amount < 0) {
			throw new UnsupportedOperationException();
		} else {
			Transaction withdraw = new Transaction();
			withdraw.setType(TransactionType.WITHDRAWAL);
			withdraw.setRecipient(a);
			withdraw.setSender(a);
			withdraw.setTimestamp();
			withdraw.setAmount(amount);
			a.setBalance(a.getBalance() + amount);
			a.getTransactions().add(withdraw);
			AccountDaoFile update = new AccountDaoFile();
			update.updateAccount(a);
			
		}
	}
	/**
	 * Deposit funds to an account
	 * @throws UnsupportedOperationException if amount is negative
	 */
	public void deposit(Account a, Double amount) {
		if (amount < 0) {
			throw new UnsupportedOperationException();
		} else if(!a.isApproved()) {
			throw new UnsupportedOperationException("Not approved");
		} else {
			
			Transaction deposit = new Transaction();
			AccountDaoFile update = new AccountDaoFile();
			Account account = new Account();
			AccountDaoDB dbupdate = new AccountDaoDB();
			TransactionDaoFile tranDao = new TransactionDaoFile();
			
			deposit.setType(TransactionType.DEPOSIT);
			deposit.setRecipient(a);
			deposit.setSender(a);
			deposit.setTimestamp();
			deposit.setAmount(amount);
			
			a.getTransactions().add(deposit);
			tranDao.addTransaction(deposit);
			dbupdate.addTransaction(deposit);
			a.setBalance(a.getBalance() + amount);
			update.updateAccount(a);
			dbupdate.updateAccount(a);
		}
	
	}
	
	/**
	 * Transfers funds between accounts
	 * @throws UnsupportedOperationException if amount is negative or 
	 * the transaction would result in a negative balance for either account
	 * or if either account is not approved
	 * @param fromAct the account to withdraw from
	 * @param toAct the account to deposit to
	 * @param amount the monetary value to transfer
	 */
	public void transfer(Account fromAct, Account toAct, double amount) {
		if (amount < 0) {
			throw new UnsupportedOperationException();
		} else if(!fromAct.isApproved()) {
			throw new UnsupportedOperationException("Not approved");
		} else {
			
			Transaction deposit = new Transaction();
			AccountDaoFile update = new AccountDaoFile();
			Account account = new Account();
			AccountDaoDB dbupdate = new AccountDaoDB();
			TransactionDaoFile tranDao = new TransactionDaoFile();
			
			deposit.setType(TransactionType.DEPOSIT);
			deposit.setRecipient(toAct);
			deposit.setSender(fromAct);
			deposit.setTimestamp();
			deposit.setAmount(amount);
			
			toAct.getTransactions().add(deposit);
			tranDao.addTransaction(deposit);
			dbupdate.addTransaction(deposit);
			toAct.setBalance(toAct.getBalance() + amount);
			update.updateAccount(toAct);
			dbupdate.updateAccount(toAct);
		}
		
	}
	
	
	/**
	 * Creates a new account for a given User
	 * @return the Account object that was created
	 */
	public Account createNewAccount(User u) {
	return actDao.addAccount(u.getAccounts().get(0));
	//	Account newAct = new Account();  //def not right ask
	  /*if (u == actDao) {
		return null; }
	  else{return newAct;} */
	}
	
	/**
	 * Approve or reject an account.
	 * @param a
	 * @param approval
	 * @throws UnauthorizedException if logged in user is not an Employee
	 * @return true if account is approved, or false if unapproved
	 */
	public boolean approveOrRejectAccount(Account a, boolean approval) {
		return false;
	}
	public List<Account> getAccounts(User u) {
		List<Account> accountList = new ArrayList<Account>();
		accountList = u.getAccounts();
		return accountList;
	}
}

