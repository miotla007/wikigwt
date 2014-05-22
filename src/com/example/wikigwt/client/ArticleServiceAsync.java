package com.example.wikigwt.client;

import java.util.List;

import com.example.wikigwt.shared.models.Article;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ArticleServiceAsync {

	void addArticle(Article articleToAdd, AsyncCallback<Void> callback);

	void deleteArticle(Long id, AsyncCallback<Void> callback);

	void editArticle(Article articleToEdit, AsyncCallback<Void> callback);

	void getAllArticles(AsyncCallback<List<Article>> callback);

	void getOneArticle(Long id,AsyncCallback<Article> callback);

}
