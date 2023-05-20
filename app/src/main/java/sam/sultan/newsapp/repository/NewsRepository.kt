package sam.sultan.newsapp.repository

import sam.sultan.newsapp.api.RetrofitInstance
import sam.sultan.newsapp.database.NewsDao
import sam.sultan.newsapp.models.Article

class NewsRepository(private val db: NewsDao){

    //api methods
    suspend fun getNews() = RetrofitInstance.api.getNews()

    suspend fun getSearchedNews(query: String) = RetrofitInstance.api.searchNews(query)

    //database methods
    suspend fun saveArticle(article: Article) = db.saveArticle(article)

    suspend fun deleteArticle(article: Article) = db.deleteArticle(article)

    fun getAllArticles() = db.getAll()

}