package sam.sultan.newsapp.repository

import retrofit2.Response
import sam.sultan.newsapp.api.RetrofitInstance
import sam.sultan.newsapp.database.NewsDao
import sam.sultan.newsapp.models.Article
import sam.sultan.newsapp.models.News
import sam.sultan.newsapp.utils.Resource

class NewsRepository(private val db: NewsDao){

    //api methods
    suspend fun getNews() = RetrofitInstance.api.getNews()

    //database methods
    suspend fun saveArticle(article: Article) = db.saveArticle(article)

    fun getAllArticles() = db.getAll()

}