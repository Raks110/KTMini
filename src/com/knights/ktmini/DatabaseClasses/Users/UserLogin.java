package com.knights.ktmini.DatabaseClasses.Users;

import models.queries.Queries;

import javax.swing.*;

public class UserLogin extends Queries {
	public static int UID;
	private JPanel mainUserPanel;

	UserLogin(int UID){
		this.UID = UID;
		initView();
	}

	public void initView(){
		if(isEmpty("SELECT * FROM user_account WHERE UIN = " + Integer.toString(UID))){
			JButton jButton = new JButton("Add Account");
			mainUserPanel.add(jButton);
		}
		else{

		}
	}

}
