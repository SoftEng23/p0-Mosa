package com.revature.services;

import com.revature.beans.User;

public interface UsersService {

	public boolean login(String username, String password);

	public boolean register(User user);
	
	public boolean logout();

	public void register(int id, String username, String password, String firstName, String lastName, String user_type);

}