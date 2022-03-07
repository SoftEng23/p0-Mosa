package com.revature.services;

import com.revature.beans.User;

public interface UsersService {

	public boolean login(String username, String password);

	public boolean register(User user);
	
	public boolean logout();

}