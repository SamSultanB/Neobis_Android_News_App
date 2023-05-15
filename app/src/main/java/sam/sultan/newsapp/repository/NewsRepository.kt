package sam.sultan.newsapp.repository

import sam.sultan.newsapp.api.RetrofitInstance

class NewsRepository(){

    suspend fun getNews(page: Int) = RetrofitInstance.api.getNews(pageNumber = page)

}