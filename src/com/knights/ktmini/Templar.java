package com.knights.ktmini;

import com.knights.ktmini.DatabaseClasses.Users.UserLogin;
import com.knights.ktmini.DatabaseClasses.Users.UserSchema;
import models.queries.BankQueries;
import models.queries.BusinessQueries;

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

	public static Connection connection;

	public static JPanel createHorizontal(String label){
		JPanel jPanel = new JPanel(new GridLayout(1,2));

		JLabel jLabel = new JLabel(label);
		JTextField jTextField = new JTextField();

		jPanel.add(jLabel);
		jPanel.add(jTextField);

		return jPanel;
	}

	public static JPanel createHorizontalPass(String label){
		JPanel jPanel = new JPanel(new GridLayout(1,2));

		JLabel jLabel = new JLabel(label);
		JPasswordField jPasswordField = new JPasswordField();

		jPanel.add(jLabel);
		jPanel.add(jPasswordField);

		return jPanel;
	}

	public static String getInnerText (JPanel jPanel){
		String text = "";
		Component[] components = jPanel.getComponents();
		for(int i = 0;i<components.length;i++){
			if(components[i] instanceof JTextField){
				text = ((JTextField)components[i]).getText();
			}
		}
		return text;
	}

	public static String getInnerPass (JPanel jPanel){
		String text = "";
		Component[] components = jPanel.getComponents();
		for(int i = 0;i<components.length;i++){
			if(components[i] instanceof JPasswordField){
				text = String.valueOf(((JPasswordField)components[i]).getPassword());
			}
		}
		return text;
	}

	public static void main(String[] args) {

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@Spectre:1523:ORCL", "knights", "Oracle12345");
			System.out.println("Connection Established Successfull and the DATABASE NAME IS:"
					+ connection.getMetaData().getDatabaseProductName());
		}
		catch(Exception e){e.printStackTrace();}

		JFrame jFrame = new JFrame("KT Mini");

		JPanel jPanel = new JPanel(new GridLayout(13,1));

		JLabel login = new JLabel("Log into Knights Templar");
		JPanel outerPanel1 = createHorizontal("First Name");
		JPanel outerPanel2 = createHorizontalPass("Password");

		JPanel loginButton = new JPanel(new GridLayout(1,1));
		JButton loginButtonIn = new JButton("Login");
		loginButton.add(loginButtonIn);

		loginButtonIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserSchema.username = getInnerText(outerPanel1);
				UserSchema.password = getInnerPass(outerPanel2);
				new UserSchema().insertValue();
			}
		});

		JLabel register = new JLabel("Register into Knights Templar");
		JPanel outerPanel3 = createHorizontal("First Name");
		JPanel outerPanel4 = createHorizontal("Middle Name");
		JPanel outerPanel5 = createHorizontal("Last Name");
		JPanel outerPanel6 = createHorizontal("Phone No.");
		JPanel outerPanel7 = createHorizontalPass("Password");
		JPanel outerPanel8 = createHorizontalPass("Confirm Password");

		JPanel registerButton = new JPanel(new GridLayout(1,1));
		JButton registerButtonIn = new JButton("Register");
		registerButton.add(registerButtonIn);

		registerButtonIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String confPassword = getInnerPass(outerPanel8);
				String password = getInnerPass(outerPanel7);

				if(confPassword.equals(password)){

					System.out.println("Successfully registered.");
					UserSchema.username = getInnerText(outerPanel3);
					UserSchema.lastname = getInnerText(outerPanel4);
					UserSchema.middlename = getInnerText(outerPanel5);
					UserSchema.password = getInnerPass(outerPanel7);
					UserSchema.contact = getInnerText(outerPanel6);
					new UserSchema().insertValue();
				}
			}
		});

		JPanel enterpriseSwitch = new JPanel(new GridLayout(1,1));
		JButton enterpriseSwitchIn = new JButton("Switch into KT Enterprise");
		enterpriseSwitch.add(enterpriseSwitchIn);

		enterpriseSwitchIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JFrame enterpriseFrameMain = new JFrame("KT Enterprise");

				JPanel enterpriseFrame = new JPanel(new GridLayout(10,1));

				JLabel jLabelLog = new JLabel("Enterprise Login");

				JPanel innerPanel1 = Templar.createHorizontal("Manager UIN");
				JPanel innerPanel2 = Templar.createHorizontalPass("Password");

				JLabel jLabelReg = new JLabel("Enterprise Register");

				JPanel innerPanel3 = Templar.createHorizontal("Company Name");
				JPanel innerPanel4 = Templar.createHorizontal("Manager UIN");
				JPanel innerPanel5 = Templar.createHorizontalPass("Password");
				JPanel innerPanel6 = Templar.createHorizontalPass("Confirm Password");

				JPanel innerPanel7 = new JPanel(new GridLayout(1,2));

				JButton bankReg = new JButton("Register as Bank");

				bankReg.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						String name = getInnerText(innerPanel3);
						int mUIN = Integer.parseInt(getInnerText(innerPanel4));
						String password = getInnerPass(innerPanel5);
						String conf = getInnerPass(innerPanel6);

						if(password.equals(conf)){
							BankQueries bankQueries = new BankQueries(connection);
							bankQueries.insert_record(name,mUIN,password);
						}
					}
				});

				JButton busReg = new JButton("Register as Business");

				busReg.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String name = getInnerText(innerPanel3);
						int mUIN = Integer.parseInt(getInnerText(innerPanel4));
						String password = getInnerText(innerPanel5);
						String conf = getInnerText(innerPanel6);

						if(password.equals(conf)){
							BusinessQueries businessQueries = new BusinessQueries(connection);
							businessQueries.insert_record(mUIN,name,password);
						}
					}
				});

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

		login.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
		loginButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		register.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
		registerButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		enterpriseSwitch.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		outerPanel1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		outerPanel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		outerPanel3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		outerPanel4.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		outerPanel5.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		outerPanel6.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		outerPanel7.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		outerPanel8.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


		jPanel.add(login);
		jPanel.add(outerPanel1);
		jPanel.add(outerPanel2);
		jPanel.add(loginButton);
		jPanel.add(register);
		jPanel.add(outerPanel3);
		jPanel.add(outerPanel4);
		jPanel.add(outerPanel5);
		jPanel.add(outerPanel6);
		jPanel.add(outerPanel7);
		jPanel.add(outerPanel8);
		jPanel.add(registerButton);
		jPanel.add(enterpriseSwitch);


		//jFrame.setContentPane(new Templar().login);
		jFrame.setContentPane(jPanel);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.pack();
		jFrame.setVisible(true);
	}
}
