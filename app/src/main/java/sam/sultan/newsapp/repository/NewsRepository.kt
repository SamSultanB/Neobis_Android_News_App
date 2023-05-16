package sam.sultan.newsapp.repository

import sam.sultan.newsapp.api.RetrofitInstance
import sam.sultan.newsapp.database.NewsDao
import sam.sultan.newsapp.database.NewsDataBase
import sam.sultan.newsapp.models.Article

class NewsRepository(private val db: NewsDao){

    //api methods
    suspend fun getNews() = RetrofitInstance.api.getNews()

    suspend fun refreshNews() = RetrofitInstance.api.getNews()

    //database methods
    suspend fun saveArticle(article: Article) = db.saveArticle(article)

    fun getAllArticles() = db.getAll()

}