package com.cg.parproj.dao;

import com.cg.parproj.bean.Account;
import com.cg.parproj.exceptions.ProductExceptions;

public interface AccountDao {
	public void addAccount(Account acc);
	public Account getAccountDetails(String phoneNumber);
	public void deposit(String phoneNumber, double amount);
	public void withdraw(String phoneNumber, double amount);
	public void addHistory(String phoneNumber, String history);
}
