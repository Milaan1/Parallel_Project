package com.cg.parproj.bean;

import java.util.ArrayList;

public class Account {
	private String name;
	private String phoneNumber;
	private double balance;
	private ArrayList<String> history = new ArrayList<>(); 
	
	public Account(String name, String phoneNumber, double balance){
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.balance = balance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public ArrayList<String> getHistory() {
		return history;
	}

	public void addHistory(String history) {
		this.history.add(history);
	}

}
