package sam.sultan.newsapp.repository

import retrofit2.Response
import sam.sultan.newsapp.api.RetrofitInstance
import sam.sultan.newsapp.models.News

class NewsRepository(){

    suspend fun getNews(page: Int) = RetrofitInstance.api.getNews("us",page)

}