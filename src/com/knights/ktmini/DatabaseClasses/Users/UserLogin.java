package com.knights.ktmini.DatabaseClasses.Users;

import models.Bank;
import models.User;
import models.UserAccount;
import models.banking.Loan;
import models.queries.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserLogin extends Queries {
	public static int UID;
	public static int lenUsers;

	public static int accountNum;

	private static Connection connection;

	UserLogin(int UID){
		UserLogin.UID = UID;
		System.out.println("In constructor with UID " + UserLogin.UID);

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@Spectre:1523:ORCL", "knights", "Oracle12345");
			System.out.println("Connection Established Successfull and the DATABASE NAME IS:"
					+ connection.getMetaData().getDatabaseProductName());
		}
		catch(Exception e){e.printStackTrace();}

		String[] args = null;
		main(args);
	}

	public static void setFrameVis(JFrame jFrame,JPanel jPanel){

		jFrame.setContentPane(jPanel);

		jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jFrame.pack();
		jFrame.setVisible(true);

	}

	public static void main(String[] args) {

		System.out.println("Inside InitView");
		JFrame jFrame = new JFrame("Welcome to KT Mini");

		JPanel jPanel = new JPanel(new GridLayout(1,2));

		if(isEmpty("SELECT * FROM user_account WHERE UIN = " + Integer.toString(UID))){

			JButton addAccount = new JButton("Add a Bank Account");
			addAccount.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JFrame jFrame1 = new JFrame("Add a bank account");
					JPanel accountPanel = new JPanel();

					BankQueries bankQueries = new BankQueries(connection);

					String[] banks = bankQueries.getAllBanks();

					JList bankList = new JList(banks);
					bankList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					bankList.setVisibleRowCount(5);

					accountPanel.add(bankList);

					JButton addBank = new JButton("Create Account");
					addBank.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							String bankSel = (String)bankList.getSelectedValue();
							int accn = Integer.parseInt(bankQueries.getMainbankID(bankSel) + Integer.toString(UID));

							String bankID = bankQueries.getMainbankID(bankSel);

							UserAccount userAccount = new UserAccount(accn,UID,Integer.parseInt(bankID),0);
							UserAccountQueries userAccountQueries = new UserAccountQueries(UserLogin.connection);

							userAccountQueries.insert_record(userAccount);

							jFrame1.setVisible(false);
							jFrame.setVisible(false);

						}
					});

					accountPanel.add(addBank);
					jFrame1.add(accountPanel);

					UserLogin.setFrameVis(jFrame1,accountPanel);

				}
			});

			addAccount.setSize(addAccount.getPreferredSize());
			System.out.println(addAccount.toString());

			jPanel.add(addAccount);
		}
		else{

			accountNum = UserAccountQueries.getAccountNum(UID);

			JPanel innerPanel = new JPanel(new GridLayout(4,1));

			JLabel jLabel = new JLabel("Your balance is " + Integer.toString(UserAccountQueries.getBalance(UID)));
			innerPanel.add(jLabel);

			JButton takeLoan = new JButton("Avail Loan");

			takeLoan.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					LoanQueries loanQueries = new LoanQueries(connection);
					System.out.println("Inside Action for AVAIL LOAN");
					if(loanQueries.loanTaken(UID)){
						JFrame payLoanFrame = new JFrame("Avail Loan");
						JPanel payLoanPanel = new JPanel(new GridLayout(2,1));

						JLabel payLoanLabel = new JLabel("You have already taken a loan. Amount: " + loanQueries.loanAmt(UID));
						payLoanPanel.add(payLoanLabel);

						JPanel innerPayPanel = new JPanel(new GridLayout(1,2));

						JTextField payLoanField = new JTextField();

						JButton payLoanButton = new JButton("Pay Loan");
						payLoanButton.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								if(Integer.parseInt(payLoanField.getText()) - UserAccountQueries.getBalance(UID) <= 0){
									int addAmt = 0;
									if(loanQueries.loanAmt(UID) - Integer.parseInt(payLoanField.getText()) < 0){
										addAmt = - (loanQueries.loanAmt(UID) - Integer.parseInt(payLoanField.getText()));
									}
									LoanQueries.updateLoanAmt(UID,loanQueries.loanAmt(UID) - Integer.parseInt(payLoanField.getText()));
									UserAccountQueries.updateBalance(UID, UserAccountQueries.getBalance(UID) - Integer.parseInt(payLoanField.getText()) + addAmt);
									payLoanFrame.setVisible(false);

									jLabel.setText("Your balance is " + Integer.toString(UserAccountQueries.getBalance(UID)));
								}
								else{
									payLoanLabel.setText("Insufficient balance to pay. Amount: " + loanQueries.loanAmt(UID));
								}
							}
						});
						innerPayPanel.add(payLoanButton);
						innerPayPanel.add(payLoanField);
						payLoanPanel.add(innerPayPanel);
						payLoanFrame.add(payLoanPanel);

						UserLogin.setFrameVis(payLoanFrame,payLoanPanel);
					}
					else{
						JFrame loanFrame = new JFrame("Avail Loan");
						JPanel loanPanel = new JPanel(new GridLayout(2,1));

						JTextField loanField = new JTextField();
						JButton loanButton = new JButton("Avail Loan");
						loanButton.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								LoanQueries loanQueries = new LoanQueries(connection);
								loanQueries.insert_record(accountNum,Integer.parseInt(loanField.getText()),UID);
								UserAccountQueries.updateBalance(UID,UserAccountQueries.getBalance(UID) + loanQueries.loanAmt(UID));
								loanFrame.setVisible(false);

								jLabel.setText("Your balance is " + Integer.toString(UserAccountQueries.getBalance(UID)));
							}
						});

						loanPanel.add(loanButton);
						loanPanel.add(loanField);
						loanFrame.add(loanPanel);

						UserLogin.setFrameVis(loanFrame,loanPanel);
					}
				}
			});

			innerPanel.add(takeLoan);

			JButton withdraw = new JButton("Withdraw");
			withdraw.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JFrame payLoanFrame = new JFrame("Withdraw Amount");
					JPanel payLoanPanel = new JPanel(new GridLayout(2,1));

					JLabel withLabel = new JLabel("Withdraw Money.");
					payLoanPanel.add(withLabel);

					JPanel innerPayPanel = new JPanel(new GridLayout(1,2));
					JTextField payLoanField = new JTextField();

					JButton payLoanButton = new JButton("Withdraw");
					payLoanButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							int amountWit = Integer.parseInt(payLoanField.getText());
							int bal = UserAccountQueries.getBalance(UID);
							if(bal - amountWit >= 0) {
								UserAccountQueries.updateBalance(UID, bal - amountWit);
								payLoanFrame.setVisible(false);

								jLabel.setText("Your balance is " + Integer.toString(UserAccountQueries.getBalance(UID)));
							}
							else
								withLabel.setText("Insufficient Balance");
						}
					});
					innerPayPanel.add(payLoanField);
					innerPayPanel.add(payLoanButton);
					payLoanPanel.add(innerPayPanel);
					payLoanFrame.add(payLoanPanel);

					UserLogin.setFrameVis(payLoanFrame,payLoanPanel);
				}
			});

			innerPanel.add(withdraw);

			JButton deposit = new JButton("Add Deposit");
			deposit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JFrame payLoanFrame = new JFrame("Deposit Amount");
					JPanel payLoanPanel = new JPanel(new GridLayout(1,1));

					JPanel innerPayPanel = new JPanel(new GridLayout(1,2));
					JTextField payLoanField = new JTextField();

					JButton payLoanButton = new JButton("Deposit");
					payLoanButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							DepositQueries depositQueries = new DepositQueries(connection);
							int amountDep = Integer.parseInt(payLoanField.getText());
							depositQueries.insert_record(accountNum,amountDep,UID);
							payLoanFrame.setVisible(false);

							jLabel.setText("Your balance is " + Integer.toString(UserAccountQueries.getBalance(UID)));
						}
					});
					innerPayPanel.add(payLoanField);
					innerPayPanel.add(payLoanButton);
					payLoanPanel.add(innerPayPanel);
					payLoanFrame.add(payLoanPanel);

					UserLogin.setFrameVis(payLoanFrame,payLoanPanel);
				}
			});

			innerPanel.add(deposit);

			UserAccountQueries userAccountQueries1 = new UserAccountQueries(connection);

			UserAccount[] list = userAccountQueries1.getAllUserAccounts();
			String[] firstnames = new String[100];

			for(int i=0;i<lenUsers;i++){
				firstnames[i] = list[i].getFirstName();
			}

			JList jList = new JList(firstnames);
			jList.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					if(!e.getValueIsAdjusting()){
						JFrame payUserFrame = new JFrame("Send Money");
						JPanel payUserPanel = new JPanel(new GridLayout(3,1));

						JLabel payUserLabel = new JLabel("Send Money");
						JTextField payUserField = new JTextField();
						JButton payUserButton = new JButton("Send Money To " + jList.getSelectedValue());

						payUserButton.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								int sendingUID = list[jList.getSelectedIndex()].getUIN();
								int amount = Integer.parseInt(payUserField.getText());
								if(UserAccountQueries.getBalance(UID) - amount >= 0) {
									BankQueries.insert_into_payments(sendingUID, UID, amount);
									UserAccountQueries.updateBalance(sendingUID, list[jList.getSelectedIndex()].getBalance() + amount);
									UserAccountQueries.updateBalance(UID, UserAccountQueries.getBalance(UID) - amount);

									jLabel.setText("Your balance is " + UserAccountQueries.getBalance(UID));

									JFrame jTemp = new JFrame("Successful Transaction");
									JPanel jTempPan = new JPanel();

									JLabel jTempLab = new JLabel("Successful Transfer!");
									jTempPan.add(jTempLab);

									UserLogin.setFrameVis(jTemp,jTempPan);
									payUserFrame.setVisible(false);

								}
								else{
									payUserLabel.setText("Insufficient Balance");
								}
							}
						});

						payUserPanel.add(payUserLabel);

						payUserPanel.add(payUserField);
						payUserPanel.add(payUserButton);

						UserLogin.setFrameVis(payUserFrame,payUserPanel);

					}
				}
			});

			jPanel.add(jList);
			jPanel.add(innerPanel);

		}

		UserLogin.setFrameVis(jFrame,jPanel);

	}

}
