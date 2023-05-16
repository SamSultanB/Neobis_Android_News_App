package sam.sultan.newsapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import sam.sultan.newsapp.models.Article
import sam.sultan.newsapp.models.News
import sam.sultan.newsapp.repository.NewsRepository

class NewsViewModel(private val newsRepository: NewsRepository): ViewModel() {
    val news: MutableLiveData<Response<News>> = MutableLiveData()
    val savedArticles = newsRepository.getAllArticles()

    init {
        getNews()
    }

    //api
    fun getNews(){
        viewModelScope.launch {
            news.postValue(newsRepository.getNews())
        }
    }

    fun refresh(){
        viewModelScope.launch {
            news.postValue(newsRepository.refreshNews())
        }
    }

    //database
    fun saveArtilce(article: Article){
        viewModelScope.launch {
            newsRepository.saveArticle(article)
        }
    }

}