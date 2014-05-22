package com.example.wikigwt.client;

import java.util.List;

import com.example.wikigwt.shared.models.Article;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Wikigwt implements EntryPoint {

	@SuppressWarnings("unused")
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	private final ArticleServiceAsync articleService = GWT.create(ArticleService.class);

	private final LoginServiceAsync loginService = GWT.create(LoginService.class);
	
	private void FlexArticlesList(final ListBox lb){
		articleService.getAllArticles(new AsyncCallback<List<Article>>() {

			@Override
			public void onFailure(Throwable caught) {

			}

			@Override
			public void onSuccess(List<Article> result) {		
				lb.clear();
				for(Article e : result){
				
					lb.addItem(e.getTitle(), e.getId().toString());
				
				}
			}
		});
		
	}
	
	public void onModuleLoad(){
		final TextBox loginTextBox = new TextBox();
		final PasswordTextBox passTextBox = new PasswordTextBox();
		Button loginButton = new Button("zaloguj");
		final TextBox titleTb = new TextBox();
		final TextBox descTb = new TextBox();
		
		Button saveBtn = new Button("Zapisz");
		Button deleteBtn = new Button("Usuń");
		Button addBtn = new Button("Dodaj");
		Button cancelBtn = new Button("Anuluj");
		Button editBtn = new Button("Edytuj");
		
		
		final ListBox lb = new ListBox();
		RootPanel.get("listBoxHolder").add(lb);
		
		RootPanel.get("titleTbHolder").add(titleTb);
		RootPanel.get("dscrTaHolder").add(descTb);
		RootPanel.get("editBtnHolder").add(saveBtn);
		RootPanel.get("rmEntryBtnHolder").add(deleteBtn);
		RootPanel.get("addBtnHolder").add(addBtn);
		RootPanel.get("cancelBtnHolder").add(cancelBtn);
		RootPanel.get("editEntryBtnHolder").add(editBtn);
		
		
		RootPanel.get("loginTbHolder").add(loginTextBox);
		RootPanel.get("passwdTbHolder").add(passTextBox);
		RootPanel.get("signinBtnHolder").add(loginButton);
		
		final Label userNameLbl = new Label("user name");
		Button logoutBtn = new Button(" wyloguj ");

		RootPanel.get("userNameLblHolder").add(userNameLbl);
		RootPanel.get("logoutBtnHolder").add(logoutBtn);
		
		logoutBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				DOM.getElementById("loginPanel").getStyle().setDisplay(Display.BLOCK);
				DOM.getElementById("loggedinPanel").getStyle().setDisplay(Display.NONE);
				DOM.getElementById("crudHolder").getStyle().setDisplay(Display.NONE);
				DOM.getElementById("articles").getStyle().setDisplay(Display.BLOCK);
				
			}
		});
		
		loginButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				loginService.checkUser(loginTextBox.getText(), passTextBox.getText(), new AsyncCallback<Boolean>() {
					
					@Override
					public void onSuccess(Boolean result) {
						if(result){
							DOM.getElementById("loginPanel").getStyle().setDisplay(Display.NONE);
							DOM.getElementById("loggedinPanel").getStyle().setDisplay(Display.BLOCK);
							DOM.getElementById("crudHolder").getStyle().setDisplay(Display.BLOCK);
							DOM.getElementById("articles").getStyle().setDisplay(Display.NONE);
							userNameLbl.setText(loginTextBox.getText());
							
							FlexArticlesList(lb);
							
						}
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						DOM.getElementById("siginErr").getStyle().setDisplay(Display.BLOCK);
						
					}
				});
				
			}
		});

		articleService.getAllArticles(new AsyncCallback<List<Article>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(List<Article> result) {
				while(RootPanel.get("articles").getWidgetCount() > 0){
					RootPanel.get("articles").remove(0);
				}	
				
				for(Article e : result){
					VerticalPanel vp1 = new VerticalPanel();
					Label title = new Label(e.getTitle());
					title.addStyleName("titleHeader");
					Label desc = new Label(e.getDesc());
					desc.addStyleName("descField");
					
					vp1.add(title);
					vp1.add(desc);
					
					RootPanel.get("articles").add(vp1);
				}
				
			}
		});
		
		editBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Long id = Long.parseLong(lb.getValue(lb.getSelectedIndex()));
				articleService.getOneArticle(id, new AsyncCallback<Article>() {
					
					@Override
					public void onSuccess(Article result) {
						titleTb.setValue(result.getTitle());
						descTb.setText(result.getDesc());
					}
					
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}
				});
			}
		});
		
		saveBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Long id = Long.parseLong(lb.getValue(lb.getSelectedIndex()));
				String title = titleTb.getText();
				String descr = descTb.getText();
				
				articleService.editArticle(new Article(id, title, descr), new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Void result) {
						titleTb.setText("");
						descTb.setText("");
						FlexArticlesList(lb);
					}
				});
			}
		});
		
		deleteBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Long id = Long.parseLong(lb.getValue(lb.getSelectedIndex()));
				articleService.deleteArticle(id, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Void result) {
						FlexArticlesList(lb);
					}
				});
			}
		});
		
		addBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				String title = titleTb.getText();
				String descr = descTb.getText();
				articleService.addArticle(new Article(title, descr), new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Void result) {
						titleTb.setText("");
						descTb.setText("");
						FlexArticlesList(lb);
					}
				});
			}
		});
		
		cancelBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				titleTb.setText("");
				descTb.setText("");
			}
		});
	}
	
}
