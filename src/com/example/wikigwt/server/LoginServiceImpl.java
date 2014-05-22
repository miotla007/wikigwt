package com.example.wikigwt.server;

import com.example.wikigwt.client.LoginService;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

	final static String UsrLogin = "admin";
	final static String UsrPassword = "haslo123";

	private static final long serialVersionUID = 1L;

	public Boolean checkUser(String login, String passwd) {

		return (UsrLogin.compareTo(login) == 0 && UsrPassword.compareTo(UsrPassword) == 0);

	}
}
