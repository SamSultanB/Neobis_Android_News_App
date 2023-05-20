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

    suspend fun getSearchedNews(query: String) = RetrofitInstance.api.searchNews(query)

    //database methods
    suspend fun saveArticle(article: Article) = db.saveArticle(article)

    suspend fun deleteArticle(article: Article) = db.deleteArticle(article)

    fun getAllArticles() = db.getAll()

}