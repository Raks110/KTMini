package com.knights.ktmini;

import com.knights.ktmini.DatabaseClasses.Users.UserSchema;

import javax.swing.*;
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
	private String username = "";
	private String lastname = "";
	private String middlename = "";
	private String password = "";
	private String contact = "";
	private String confPassword = "";

	public Templar() {
		continueButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserSchema.username = username;
				UserSchema.password = password;
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
	}

	public static void main(String[] args) {
		JFrame jFrame = new JFrame("Templar");
		jFrame.setContentPane(new Templar().login);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.pack();
		jFrame.setVisible(true);
	}
}
