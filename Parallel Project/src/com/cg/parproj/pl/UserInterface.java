package com.cg.parproj.pl;

import java.util.Scanner;
import com.cg.parproj.bean.*;
import com.cg.parproj.service.*;
import com.cg.parproj.exceptions.*;

public class UserInterface {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int choice;
		String conti;
		String name, phoneNumber;
		double balance;
		AccountService serv = new AccountServiceImpl();
		Account account;
		
		do {
		
		System.out.println("Choose among the following options:-");
		System.out.println("1. Create new account");
		System.out.println("2. Show balance");
		System.out.println("3. Make deposit");
		System.out.println("4. Withdraw");
		System.out.println("5. Fund transfer");
		System.out.println("6. Print transactions");
		
		choice = sc.nextInt();
		
		switch(choice)
		{
		case 1:
			System.out.println("Enter customer name");
			name = sc.next();
			System.out.println("Enter phone number to be linked");
			phoneNumber = sc.next();
			System.out.println("Enter initial amount to be deposited (can be 0)");
			balance = sc.nextDouble();
			//validate mobile no. and amount>0
			try {
				if(serv.validateTransaction(phoneNumber, balance, "0", 1)) {
					Account acc = new Account(name, phoneNumber, balance);
					serv.addAccount(acc);
					serv.addHistory(phoneNumber, "Account created with initial balance: " + balance);
				}
			} catch(ProductExceptions e1) {
				System.out.println(e1.getMessage());
			}
			//end validation code
			break;
		case 2:
			System.out.println("Enter phone number of customer");
			phoneNumber = sc.next();
			//validate mobile number's existence
			try {
				if(serv.validateTransaction(phoneNumber, 0, "0", 0)) {
					account = serv.getAccountDetails(phoneNumber);
					System.out.println("Account balance is " + account.getBalance());
				}
			}catch(ProductExceptions e1) {
				System.out.println(e1.getMessage());
			}
			//end validation code
			break;
		case 3:
			double amount;
			System.out.println("Enter the phone number of payee");
			phoneNumber = sc.next();
			System.out.println("Enter the amount to be deposited");
			amount = sc.nextInt();
			//validate mobile no. and amount>0
			try {
				if(serv.validateTransaction(phoneNumber, amount, "0", 0)) {
					serv.deposit(phoneNumber, amount);
					System.out.println("Amount successfully deposited\nCurrent balance is: " + serv.getAccountDetails(phoneNumber).getBalance());
					serv.addHistory(phoneNumber, "Amount of " + amount + " credited to account with phone Number " + phoneNumber);
				}
			} catch(ProductExceptions e1) {
				System.out.println(e1.getMessage());
			}
			//end validation code
			break;
		case 4:
			double amount2;
			System.out.println("Enter the phone number of payee");
			phoneNumber = sc.next();
			System.out.println("Enter the amount to be withdrawn");
			amount2 = sc.nextInt();
			//validate mobile no. and amount>0 && amount<serv.getAccountDetails(phoneNumber).getBalance();
			try {
				if(serv.validateTransaction("0", amount2, phoneNumber, 0)) {
					serv.withdraw(phoneNumber, amount2);
					System.out.println("Amount successfully withdrawn\nCurrent balance is: " + serv.getAccountDetails(phoneNumber).getBalance());
					serv.addHistory(phoneNumber, "Amount of " + amount2 + " withdrawn from account with phone Number " + phoneNumber);
				}
			} catch(ProductExceptions e1) {
				System.out.println(e1.getMessage());
			}
			//end validation code
			break;
		case 5:
			double amount3;
			String phoneNo2;
			System.out.println("Enter the account (phone number) to transfer FROM");
			phoneNumber = sc.next();
			System.out.println("Enter the account (phone number) to transfer TO");
			phoneNo2 = sc.next();
			System.out.println("Enter the amount to be transfered");
			amount3 = sc.nextInt();
			//validate both nos, amount>0, amount < serv.getAccountDetails(phoneNumber).getBalance();
			try {
				if(serv.validateTransaction(phoneNo2, amount3, phoneNumber, 0)) {
					serv.withdraw(phoneNumber, amount3);
					serv.deposit(phoneNo2, amount3);
					System.out.println("Amount successfully withdrawn from " + phoneNumber + "\nCurrent balance is: " + serv.getAccountDetails(phoneNumber).getBalance());
					System.out.println("Amount successfully deposited into " + phoneNo2 + " \nCurrent balance is: " + serv.getAccountDetails(phoneNo2).getBalance());
					serv.addHistory(phoneNumber, "Amount of " + amount3 + " transfered to account with phone Number " + phoneNo2);
					serv.addHistory(phoneNo2, "Amount of " + amount3 + " transferred from account with phone Number " + phoneNumber);
				}
			} catch(ProductExceptions e1) {
				System.out.println(e1.getMessage());
			}
			//end code
			break;
		case 6:
			System.out.println("Enter the phone number of account to print all transactions");
			phoneNumber = sc.next();
			try {
				if(serv.validateTransaction(phoneNumber, 0, "0", 0)) {
					int i = 1;
					for(String history : serv.getAccountDetails(phoneNumber).getHistory()) {
						System.out.println(i + ") " + history);
						i++;
					}
				}
			} catch(ProductExceptions e1) {
				System.out.println(e1.getMessage());
			}
		}
		
		System.out.println("Do you wish to quit the app? (Y/N)");
		conti = sc.next();
		}while((conti.charAt(0)=='N')||(conti.charAt(0)=='n'));
		
		System.out.println("Thank you for using the application!");
	}

}
