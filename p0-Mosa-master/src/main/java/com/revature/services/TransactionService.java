package com.revature.services;

public interface TransactionService {

	public boolean deposit(int accountId, double amount);

	public boolean withdraw(int accountId, double amount);

	public boolean transfer(int fromAccount, int toAccount, double amount);
}