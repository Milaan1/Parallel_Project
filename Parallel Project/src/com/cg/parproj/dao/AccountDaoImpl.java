package com.cg.parproj.dao;

import com.cg.parproj.bean.*;
import java.util.*;

public class AccountDaoImpl implements AccountDao {
	HashMap<String, Account> database = new HashMap<>();
	
	public void addAccount(Account acc){
		database.put(acc.getPhoneNumber(), acc);
	}
	
	public Account getAccountDetails(String phoneNumber) {
		return database.get(phoneNumber);
	}
	
	public void deposit(String phoneNumber, double amount) {
		Account acc = database.get(phoneNumber);
		acc.setBalance(acc.getBalance()+amount);
		database.remove(phoneNumber);
		database.put(phoneNumber, acc);
	}
	
	public void withdraw(String phoneNumber, double amount) {
		Account acc = database.get(phoneNumber);
		acc.setBalance(acc.getBalance()-amount);
		database.remove(phoneNumber);
		database.put(phoneNumber, acc);
	}
	
	public void addHistory(String phoneNumber, String history) {
		Account acc = database.get(phoneNumber);
		acc.addHistory(history);
		database.remove(phoneNumber);
		database.put(phoneNumber, acc);
	}
}
