package com.example.wikigwt.server;

import java.util.List;
import java.util.ArrayList;

import com.example.wikigwt.client.ArticleService;
import com.example.wikigwt.shared.models.Article;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ArticleServiceImpl extends RemoteServiceServlet implements ArticleService{

	private static final long serialVersionUID = 1L;
	private List<Article> ArticleDB = new ArrayList<Article>();
	
	public ArticleServiceImpl(){
		ArticleDB.add(new Article(1L, "Siemanko1", "Pierwsza wrzutka do wiki"));
		ArticleDB.add(new Article(2L, "Siemanko2", "Druga wrzutka do wiki"));
		ArticleDB.add(new Article(3L, "Siemanko3", "Trzecia wrzutka do wiki"));
		
	}
	
	@Override
	public void addArticle(Article articleToAdd) {
		ArticleDB.add(articleToAdd);
		
	}

	@Override
	public void deleteArticle(Long id) {
		System.out.println(id);
		for(Article a : ArticleDB){
			System.out.println(a.getId());
			if( ( a.getId().toString().compareTo( id.toString() ) ) == 0 ){
				//Integer i = EntryDBstub.indexOf(e);
				ArticleDB.remove(a);
				System.out.println("Remove " + a.getId());// i);
			}
		}
		
	}

	@Override
	public void editArticle(Article articleToEdit) {
		for(Article a : ArticleDB){
			if( ( a.getId().toString().compareTo( articleToEdit.getId().toString() ) ) == 0 ){
				Integer i = ArticleDB.indexOf(a);
				ArticleDB.set(i, articleToEdit);
			}
		}
		
	}

	@Override
	public List<Article> getAllArticles() {
		System.out.println("DBstub size: " + ArticleDB.size());
		//Lists.sort(ArticleDB);
		return ArticleDB;
		
	}

	@Override
	public Article getOneArticle(Long id) {
		//System.out.println(id);
		for(Article a : ArticleDB){
			//System.out.println(e.getId());
			if( ( a.getId().toString().compareTo(  id.toString() ) ) == 0 ){
				return a;
			}
		}
		return null;
	}
		
}
