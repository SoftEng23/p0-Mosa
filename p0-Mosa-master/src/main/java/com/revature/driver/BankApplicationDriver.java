package com.revature.driver;
import com.revature.utils.SessionCache;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.beans.Account;
import com.revature.beans.Account.AccountType;
import com.revature.beans.Transaction;
import com.revature.beans.Transaction.TransactionType;
import com.revature.beans.User;
import com.revature.beans.User.UserType;
import com.revature.dao.AccountDaoFile;
import com.revature.dao.TransactionDaoDB;
import com.revature.dao.TransactionDaoFile;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoDB;
import com.revature.dao.UserDaoFile;
import com.revature.services.AccountService;
import com.revature.services.UserService;
import com.revature.services.UserServiceImpl;
import com.revature.services.UsersService;
import com.revature.dao.AccountDao;
import com.revature.dao.AccountDaoDB;

/**
 * This is the entry point to the application
 */
public class BankApplicationDriver {

	public static void printLine() {
		for (int i = 0; i < 80; i++) {
			System.out.print("*");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int choice = 0;
		int id = 0;
		Scanner input = new Scanner(System.in);
		String fName = null;
		String lName = null;
		String username = null;
		String password = null;
		UserDao userDao = new UserDaoDB();
		AccountDao accountDao = new AccountDaoDB();
		UserService userService = new UserService(userDao, accountDao);
		AccountService accountService = new AccountService(accountDao);
		UsersService usersService = new UserServiceImpl();
		while (choice < 6) {
			BankApplicationDriver.printLine();
			System.out.println("***** \t\t\t\t\t\t\t\t\t\t *****");
			System.out.println("***** \t\t\t\t\t\t\t\t\t\t *****");
			System.out.println("***** \t\t\t\t Welcome to Revature Bank \t\t\t *****");
			System.out.println("***** \t\t\t\t\t\t\t\t\t\t *****");
			System.out.println("***** \t\t\t\t\t\t\t\t\t\t *****");
			BankApplicationDriver.printLine();
			System.out.println("\n\t\t\t 1. Register ");
			System.out.println("\t\t\t 2. Login ");
			System.out.println("\t\t\t 3. View Customers ");
			System.out.println("\t\t\t 4. Remove Customer ");
			System.out.println("\t\t\t 5. Update Customer ");
			System.out.println("\t\t\t 6. Exit");
			System.out.print("Enter your Choice [1-6] :");
			choice = input.nextInt();

			switch (choice) {
			case 1:
				id = UserDaoFile.usersList.size();
				System.out.print("Enter First Name :");
				fName = input.next();
				System.out.print("Enter Last Name :");
				lName = input.next();
				System.out.print("Enter Userame :");
				username = input.next();
				System.out.print("Enter Password :");
				password = input.next();

				User user = new User(id++, username, password, fName, lName, UserType.CUSTOMER);
				userService.register(user);
				break;
			case 2:
				//id = UserDaoFile.usersList.size();
			
				// id = UserDaoFile.usersList.size();
				usersService = new UserServiceImpl();
				System.out.print("Enter Username :");
				username = input.next();
				System.out.print("Enter Password :");
				password = input.next();
				usersService.login(username, password);
				if (usersService.login(username, password)) {
					System.out.println("Login is Successful!!!");
					
			/*	}else {
					System.out.println("Error while Logging In. Please Check the username and/or password!!!"); */
					
			//	}if (loggedUser != null) {
			//			System.out.println("Logged in Successfully!!!");
			//			SessionCache.setCurrentUser(loggedUser);

				/*else {
					System.out.println("Error while Logging. Pls Check the username / password!!!");
				}*/
				
			//	if (loggedUser != null) {
			//		System.out.println("Login is Successful!!!");
			//		SessionCache.setCurrentUser(loggedUser);
		/*		else {
					System.out.println("Error while Logging. Pls Check the username / password!!!");
*/
					int option = 0;
					int accountType = 0;
					double startingBalance = 0;

					while (option <= 6) {
						System.out.println("\t\t\t 1.Apply Account ");
						System.out.println("\t\t\t 2.Deposit");
						System.out.println("\t\t\t 3.Withdraw ");
						System.out.println("\t\t\t 4.Fund Transfer ");
						System.out.println("\t\t\t 5.Approve/Reject Account ");
						System.out.println("\t\t\t 6.Logout ");
						System.out.print("Enter your option [1-6]:");
						option = input.nextInt();
						switch (option) {
						case 1:
							List<Transaction> transactions = null;
							Integer ownerId = null;
							System.out.print("\t\t\t Let's Create An Account:");
							System.out.print("\n Enter 1 for Checking or 2 for Savings:");
							
							Integer checks = input.nextInt();
							
							
							if(checks == 1) {
								System.out.println("\n Let's Create Your Checking account");
							}
							else {
								System.out.println("\n Let's Create Your Savings account");
							}
							//String user_Type = "customer";
							
							System.out.println("Enter Your SSN to Create Account: ");
							//String names = input.next();
							//	String g = input.next();
							
						
							id = input.nextInt();
							String accnum = "817253434";
							transactions = TransactionDaoFile.usersList;
							ownerId = 6;
							Double balances2 = (double) 50000;
							Account accountServices = new Account();
							accountServices.setTransactions(transactions);
							//accountServices.setType(AccountType.SAVINGS);
							accountServices.setApproved(true);
							accountServices.setOwnerId(ownerId);
							accountServices.setId(id);
							accountServices.setBalance(balances2);
							System.out.println("Thank you. You have created a new account " + accountServices);
							
							System.out.println("Your account # is: " + accnum);
							/*System.out.print("select the Account Type [1.Checking/2.Saving]: ");
							accountType = input.nextInt();
							System.out.print("Enter Starting balance:");
							startingBalance = input.nextDouble();
							Account account = new Account();
							account.setBalance(startingBalance);
							System.out.println("Logged user ID :" + SessionCache.getCurrentUser().get().getId());
							account.setOwnerId(loggedUser.getId());
							account.setType(accountType == 1 ? AccountType.CHECKING.toString()
									: AccountType.SAVINGS.toString());
							List<Account> accountList = new ArrayList<Account>();
							accountList.add(account);
							loggedUser.setAccounts(accountList);
							accountService.createNewAccount(loggedUser);*/
							break;
						case 2:
						//	Integer ownerId = null;
						//	List<Transaction> transactions = null;
							Account accountsService = new Account();
							ownerId = 2;
							usersService = new UserServiceImpl();
							//id = AccountDaoFile.usersList.size();
							id = 2;
							transactions = TransactionDaoFile.usersList;
						
							Double balances = (double) 40000;
							accountsService = new Account();
							accountsService.setTransactions(transactions);
						//	accountsService.setType(AccountType.CHECKING);
							accountsService.setApproved(true);
							accountsService.setOwnerId(ownerId);
							accountsService.setId(id);
							accountsService.setBalance(balances);
							
							System.out.print("\t\t\t Let's Make A Deposit:");
							System.out.print("\n Enter 1 for Checking or 2 for Savings:");
							
							Integer checking = input.nextInt();
							
							
							if(checking == 1) {
								System.out.println("\t\t\t==How Much Do You Want to Deposit into Checkings?== ");
							}
							else {
								System.out.println("\t\t\t==How Much Do You Want to Deposit into Savings?== ");
							}
						//	Account sender = input.next();
							Double amounts = input.nextDouble();
							Transaction list = new Transaction();
							list.setType(TransactionType.DEPOSIT);
							list.setSender(accountsService);
							list.getAmount();
							list.setAmount(amounts);
							list.setTimestamp();
							list.setRecipient(accountsService);
							System.out.println(list);
							System.out.println("\n You deposited: " + amounts);
							break;
						/*	System.out.println("Available Accounts for this user");
							accountService.getAccounts(loggedUser).forEach(System.out::println);
							System.out.print("Enter Account ID to Deposit :");
							int accountId = 0 ;
							accountId = input.nextInt();
							System.out.print("Enter the amount to deposit :");
							double amount = 0;
							amount = input.nextDouble();
							account = accountDao.getAccount(accountId);
							accountService.deposit(account, amount); */
							
						case 3:
							ownerId = 4;
							id = AccountDaoFile.usersList.size();
							Account acc2 = new Account();
							acc2.getId();
							acc2.setOwnerId(ownerId);
							
							acc2.getType();
							acc2.getTransactions();
							System.out.print("\t\t\t Let's Make A Withdrawal:");
							System.out.print("\n Enter 1 for Checking or 2 for Savings:");
							
							Integer check = input.nextInt();
							
							
							if(check == 1) {
								System.out.println("\n\t\t\t==How Much Do You Want to Withdraw into Checkings?== ");
							}
							else {
								System.out.println("\t\t\t==How Much Do You Want to Withdraw into Savings?== ");
							}
						//	Account sender = null;
							Double amount2 = input.nextDouble();
							acc2.getBalance();
							System.out.print(acc2.getBalance());
							
							
							Transaction list2 = new Transaction();
							list2.setType(TransactionType.WITHDRAWAL);
						//	list2.setSender(acc2);
							list2.getAmount();
							list2.setAmount(amount2);
							list2.setTimestamp();
							System.out.println(list2);
							System.out.println("\n You Made of Withdrawal for: " + amount2);
							break;
					
						case 4:

							break;
						case 5:
							break;
						case 6:
							System.out.print("Do you want to Logout? (1.Yes/2.No) :");
							int logout = 0;
							logout = input.nextInt();
							if (logout == 1) {
								SessionCache.setCurrentUser(null);
								System.out.println("You have logged out");
							}
							System.exit(0);
							break;
						default:
							System.out.println("Enter a number between 1 to 6");
							break;
						}

					}
				}
				break;
			case 3:
				userDao.getAllUsers().forEach(System.out::println);
				break;
			case 4:
				System.out.print("Enter Id of the customer to remove: ");
				id = input.nextInt();
				User u = userDao.getUser(id);
				userDao.removeUser(u);
				break;
			case 5:
				System.out.print("Enter Id of the customer to Update: ");
				id = input.nextInt();
				System.out.print("Enter First Name to Update :");
				fName = input.next();
				System.out.print("Enter Last Name to Update:");
				lName = input.next();
				System.out.print("Enter Password to Update:");
				password = input.next();
				User updatedUser = new User();
				updatedUser.setId(id);
				updatedUser.setFirstName(fName);
				updatedUser.setLastName(lName);
				updatedUser.setUsername(username);
				updatedUser.setPassword(password);
				updatedUser.setUserType("CUSTOMER");
				
				userDao.updateUser(updatedUser);
				break;
			case 6:
				System.out.println("Thanks for using Revature Bank!!! Have a Nice Day!!!");
				System.exit(0);
				break;

			default:
				break;
			}

		}

		input.close();
	}

}