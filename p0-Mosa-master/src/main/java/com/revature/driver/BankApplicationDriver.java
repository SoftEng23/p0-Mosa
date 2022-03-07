package com.revature.driver;
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
import com.revature.dao.AccountDao;
import com.revature.dao.AccountDaoFile;
import com.revature.dao.TransactionDao;
import com.revature.dao.TransactionDaoFile;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoFile;
import com.revature.services.AccountService;
import com.revature.services.UserService;
import com.revature.services.UserServiceImpl;
import com.revature.services.UsersService;

/**
 * This is the entry point to the application
 */
public class BankApplicationDriver implements Runnable { // implements Runnable multithread
	static boolean LoggedOn = false;	
	private static Connection conn;
	private static Statement stmt;
	private static PreparedStatement pstmt;
	private static ResultSet rs;
	public static void printLine() {
		for (int i = 0; i < 80; i++) {
			System.out.print("*");
		}
	}

	public static void getConnection() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/p0_user", "root", "Softw@re23!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		// TODO Auto-generated method stub
		int choice = 0;
		int id = 0;
		//String user_type= "customer";
		Integer accountId = null;
		Integer ownerId = null;
		Double balance = null;
		AccountType type = null;
		Boolean approved = true;
	//	AccountType type = null;
		List<Transaction> transactions = null;
//		String fName =null;
//		String lName =null;
		String firstName = null;
		String lastName = null;
		//String username = null;
		//String password = null;
		UserDao userDao = new UserDaoFile();
		
		AccountDao accountDao = new AccountDaoFile();
		UsersService usersService = new UserServiceImpl();
		UserService userService = new UserService(userDao, accountDao);
		Account accountService = new Account();
		//AccountService accountService = null;
		//TransactionService transactionService = null;
		String Url = "jdbc:mysql://localhost:3306/p0";
		String username = "root";
		String password = "Softw@re23!";
		//Step 2
		Connection conn = DriverManager.getConnection(Url, username, password);
		
		//Step 3: Creating & Running the Queries
		String query = "select * from p0_user";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		//add insert query
		String query1 = "insert into p0_user (id,first_name,last_name,username,password,user_type) values (?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(query1);
		pstmt.setInt(1, 111);
		pstmt.setString(2, "Kyle");
		pstmt.setString(3, "Wells");
		pstmt.setString(4, "kylewells");
		pstmt.setString(5, "123");
		pstmt.setString(6, "CUSTOMER");
		pstmt.executeUpdate();
		//Step 4: 
		System.out.println("p0_user Table Content");
		System.out.println("id \t first_name \t last_name ");
		while(rs.next()) {
			System.out.println(rs.getInt("id")+"\t" + rs.getString("first_name") + "\t\t" + rs.getString("last_name"));
		}
				
		Scanner input = new Scanner(System.in);
		while (choice < 6) {
			BankApplicationDriver.printLine();
			System.out.println("***** \t\t\t\t\t\t\t\t\t\t *****");
			System.out.println("***** \t\t\t\t\t\t\t\t\t\t *****");
			System.out.println("***** \t\t\t\t Welcome to Revature Bank \t\t\t *****");
			System.out.println("***** \t\t\t\t\t\t\t\t\t\t *****");
			System.out.println("***** \t\t\t\t\t\t\t\t\t\t *****");
			BankApplicationDriver.printLine();
			System.out.println("\n\t\t\t 1. Login ");
			System.out.println("\t\t\t 2. Register ");
			System.out.println("\t\t\t 3. Make Deposit ");
			System.out.println("\t\t\t 4. Make Withdrawal ");
			System.out.println("\t\t\t 5. Create an Account ");
			System.out.println("\t\t\t 6. Exit");
			System.out.print("Enter your Choice [1-6] :");
			choice = input.nextInt();

			switch (choice) {
			case 1:
				//id = UserDaoFile.usersList.size();
				usersService = new UserServiceImpl();
				System.out.print("Enter Username :");
				username = input.next();
				System.out.print("Enter Password :");
				password = input.next();
				if (usersService.login(username, password))
					System.out.println("Login is Successful!!!");
				else {
					System.out.println("Error while Logging. Pls Check the username / password!!!");
				}
				/*User checkLogin = userDao.getUser(username, password);
				if(checkLogin == null) {
					throw new InvalidCredentialsException(); 
				}else if(!checkLogin.getPassword().equals(password)){
					throw new InvalidCredentialsException();
				}else {
					System.out.println("Login Successful");
				}
				 */
				break;
			//	User checkLogin = userDao.getUser(username, password);
			/*	if(checkLogin == null) {
					throw new InvalidCredentialsException(); 
				}else if(!checkLogin.getPassword().equals(password)){
					throw new InvalidCredentialsException();
				}else {
					System.out.println("Login is Successful!!!");
				} */
			
					
			case 2:
				usersService = new UserServiceImpl();
				User emp = null;
				id = UserDaoFile.usersList.size();
				System.out.println("Enter SSN:");
				id = input.nextInt();
				System.out.print("Enter Username :");
				username = input.next();
				System.out.print("Enter Password :");
				password = input.next();
				System.out.print("Enter First Name :");
				firstName = input.next();
				System.out.print("Enter Last Name :");
				lastName = input.next();
				emp = new User(id++, firstName, lastName, username, password, UserType.CUSTOMER);
				userService.register(emp);
				System.out.println(emp);
	
				break;
			case 3:
				ownerId = 2;
				usersService = new UserServiceImpl();
				//id = AccountDaoFile.usersList.size();
				id = 2;
				transactions = TransactionDaoFile.usersList;
			
				Double balances = (double) 40000;
				accountService = new Account();
				accountService.setTransactions(transactions);
				accountService.setType(AccountType.CHECKING);
				accountService.setApproved(true);
				accountService.setOwnerId(ownerId);
				accountService.setId(id);
				accountService.setBalance(balances);
				
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
				Double amount = input.nextDouble();
				Transaction list = new Transaction();
				list.setType(TransactionType.DEPOSIT);
				list.setSender(accountService);
				list.getAmount();
				list.setAmount(amount);
				list.setTimestamp();
				list.setRecipient(accountService);
				System.out.println(list);
				System.out.println("\n You deposited: " + amount);
				break;
			case 4:
				LoggedOn = true;
			//	System.out.println(userDao.getAllUsers());
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
			case 5:
			
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
				accountService = new Account();
				accountService.setTransactions(transactions);
				accountService.setType(AccountType.SAVINGS);
				accountService.setApproved(true);
				accountService.setOwnerId(ownerId);
				accountService.setId(id);
				accountService.setBalance(balances2);
				System.out.println("Thank you. You have created a new account " + accountService);
				
				System.out.println("Your account # is: " + accnum);
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
		if (rs!= null)
			rs.close();
		if (stmt != null)
			stmt.close();
		if (conn != null)
			conn.close();
		
		}
																		


	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Inside BankApplicationDriver class");

	} 
}
