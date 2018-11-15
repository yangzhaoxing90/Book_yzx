package service;

import java.util.List;

import Book.User;
import datebases.UserBase;

public class Userservice {
	UserBase userbase = new UserBase();
	
	public boolean addUser(User user){
		if(userbase.findCount(user.getUserName()) > 0){
			return false;
		}
		userbase.add(user);
		return true;
	}
	public List<User> findAllList(){
		return userbase.findAllList();
	}
	public boolean deleteUser(String name){
		return userbase.delete(name);
	}
	public boolean updateUser(User user){
		if(userbase.findCount(user.getUserName()) > 0){
			return false;
		}
		return userbase.update(user);
	}
	
}
