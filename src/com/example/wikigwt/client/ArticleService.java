package com.example.wikigwt.client;

import java.util.List;

import com.example.wikigwt.shared.models.Article;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("article")
public interface ArticleService extends RemoteService {
		public void addArticle(Article articleToAdd);
		public void editArticle(Article articleToEdit);
		public void deleteArticle(Long id);
		public List<Article> getAllArticles();
		public Article getOneArticle(Long id);
}
	
