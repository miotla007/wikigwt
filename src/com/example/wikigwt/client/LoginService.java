package com.example.wikigwt.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("auth")
public interface LoginService extends RemoteService {

	Boolean checkUser(String login, String passwd);

}
