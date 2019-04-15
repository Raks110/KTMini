package com.knights.ktmini;

import com.knights.ktmini.DatabaseClasses.Users.UserLogin;
import com.knights.ktmini.DatabaseClasses.Users.UserSchema;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;

public class Templar {
	private JTextField textField1;
	private JPasswordField passwordField1;
	private JTextField textField2;
	private JPasswordField passwordField2;
	private JPasswordField passwordField3;
	private JPanel login;
	private JButton continueButton;
	private JButton signUpButton;
	private JTextField textField3;
	private JTextField textField4;
	private JTextField textField5;
	private JButton switchToBusinessButton;
	private String username = "";
	private String lastname = "";
	private String middlename = "";
	private String password = "";
	private String contact = "";
	private String confPassword = "";

	public static JPanel createHorizontal(String label){
		JPanel jPanel = new JPanel(new GridLayout(1,2));

		JLabel jLabel = new JLabel(label);
		JTextField jTextField = new JTextField();

		jPanel.add(jLabel);
		jPanel.add(jTextField);

		return jPanel;
	}

	public Templar() {
		continueButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserSchema.username = username;
				UserSchema.password = password;
				new UserSchema().insertValue();
			}
		});
		signUpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(confPassword.equals(password)){

						System.out.println("Successfully registered.");
						UserSchema.username = username;
						UserSchema.lastname = middlename;
						UserSchema.middlename = lastname;
						UserSchema.password = password;
						UserSchema.contact = contact;
						new UserSchema().insertValue();
				}
			}
		});
		textField1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				super.keyPressed(e);
				username += e.getKeyChar();
			}
		});
		passwordField1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				super.keyPressed(e);
				password += e.getKeyChar();
			}
		});
		textField2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				super.keyTyped(e);
				username += e.getKeyChar();
			}
		});
		passwordField2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				super.keyPressed(e);
				password += e.getKeyChar();
			}
		});
		passwordField3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				super.keyPressed(e);
				confPassword += e.getKeyChar();
			}
		});
		textField3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				super.keyPressed(e);
				lastname += e.getKeyChar();
			}
		});
		textField4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				super.keyPressed(e);
				middlename += e.getKeyChar();
			}
		});
		textField5.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				super.keyPressed(e);
				contact += e.getKeyChar();
			}
		});

		switchToBusinessButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JFrame enterpriseFrameMain = new JFrame("KT Enterprise");

				JPanel enterpriseFrame = new JPanel(new GridLayout(10,1));

				JLabel jLabelLog = new JLabel("Enterprise Login");

				JPanel innerPanel1 = Templar.createHorizontal("Manager UIN");
				JPanel innerPanel2 = Templar.createHorizontal("Password");

				JLabel jLabelReg = new JLabel("Enterprise Register");

				JPanel innerPanel3 = Templar.createHorizontal("Company Name");
				JPanel innerPanel4 = Templar.createHorizontal("Manager UIN");
				JPanel innerPanel5 = Templar.createHorizontal("Password");
				JPanel innerPanel6 = Templar.createHorizontal("Confirm Password");

				JPanel innerPanel7 = new JPanel(new GridLayout(1,2));

				JButton bankReg = new JButton("Register Bank");

				bankReg.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

					}
				});

				JButton busReg = new JButton("Register Business");

				innerPanel7.add(bankReg);
				innerPanel7.add(busReg);

				JPanel innerPanel8 = new JPanel(new GridLayout(1,2));

				JButton bankLog = new JButton("Login as Bank");
				JButton busLog = new JButton("Login as Business");

				innerPanel8.add(bankLog);
				innerPanel8.add(busLog);


				jLabelLog.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
				jLabelReg.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

				innerPanel1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
				innerPanel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
				innerPanel3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
				innerPanel4.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
				innerPanel5.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
				innerPanel6.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
				innerPanel7.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
				innerPanel8.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

				enterpriseFrame.add(jLabelLog);
				enterpriseFrame.add(innerPanel1);
				enterpriseFrame.add(innerPanel2);
				enterpriseFrame.add(innerPanel8);
				enterpriseFrame.add(jLabelReg);
				enterpriseFrame.add(innerPanel3);
				enterpriseFrame.add(innerPanel4);
				enterpriseFrame.add(innerPanel5);
				enterpriseFrame.add(innerPanel6);
				enterpriseFrame.add(innerPanel7);

				UserLogin.setFrameVis(enterpriseFrameMain,enterpriseFrame);

			}
		});
	}

	public static void main(String[] args) {
		JFrame jFrame = new JFrame("Templar");
		jFrame.setContentPane(new Templar().login);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.pack();
		jFrame.setVisible(true);
	}
}
