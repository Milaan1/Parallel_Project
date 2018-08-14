package com.cg.parproj.service;

import com.cg.parproj.bean.*;
import com.cg.parproj.dao.*;
import com.cg.parproj.exceptions.*;

public class AccountServiceImpl implements AccountService {
	
	AccountDao dao = new AccountDaoImpl();
	
	public void addAccount(Account acc) {
		dao.addAccount(acc);
	}
	
	public Account getAccountDetails(String phoneNumber) {
		return dao.getAccountDetails(phoneNumber);
	}
	
	public void deposit(String phoneNumber, double amount) {
		dao.deposit(phoneNumber, amount);
	}
	
	public void withdraw(String phoneNumber, double amount) {
		dao.withdraw(phoneNumber, amount);
	}
	
	public void addHistory(String phoneNumber, String history) {
		dao.addHistory(phoneNumber, history);
	}
	
	public boolean validateTransaction(String phoneNumber, double amount, String withdrawNumber, int flag) throws ProductExceptions{
		
		if(amount<0) throw new ProductExceptions("Please enter a valid, non-negative amount");
		
		if(withdrawNumber!="0") {
			if(!(withdrawNumber.matches("[0-9]{10}"))) throw new ProductExceptions("Enter a valid ten-digit phone number");
			else if (getAccountDetails(withdrawNumber)==null) throw new ProductExceptions("Account with this phone number (" + withdrawNumber + ") does not exist in our records");
			else if(amount>getAccountDetails(withdrawNumber).getBalance()) throw new ProductExceptions("Insufficient funds in account " + withdrawNumber + ". Transaction did not go through");
		}
		
		if(phoneNumber!="0") {
			if(!(phoneNumber.matches("[0-9]{10}"))) throw new ProductExceptions("Enter a valid ten-digit phone number");
			else if ((getAccountDetails(phoneNumber)==null)&&(flag!=1)) throw new ProductExceptions("Account with this phone number (" + phoneNumber + ") does not exist in our records");
		}
		
		return true;
	}
}
