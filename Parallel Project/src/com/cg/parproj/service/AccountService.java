package com.cg.parproj.service;

import com.cg.parproj.bean.Account;
import com.cg.parproj.exceptions.ProductExceptions;

public interface AccountService {
	public void addAccount(Account acc);
	public Account getAccountDetails(String phoneNumber);
	public void deposit(String phoneNumber, double amount);
	public void withdraw(String phoneNumber, double amount);
	public void addHistory(String phoneNumber, String history);
	public boolean validateTransaction(String phoneNumber, double amount, String withdrawNumber, int flag) throws ProductExceptions;
}
