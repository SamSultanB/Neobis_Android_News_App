package sam.sultan.newsapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import sam.sultan.newsapp.models.Article
import sam.sultan.newsapp.models.News
import sam.sultan.newsapp.repository.NewsRepository
import sam.sultan.newsapp.utils.Resource

class NewsViewModel(private val newsRepository: NewsRepository): ViewModel() {
    val news: MutableLiveData<Resource<News>> = MutableLiveData()
    val savedArticles = newsRepository.getAllArticles()

    init {
        getNews()
    }

    //api
    fun getNews(){
        viewModelScope.launch {
            news.postValue(Resource.Loading())
            val response = newsRepository.getNews()
            if (response.isSuccessful){
                response.body()?.let {
                    news.postValue(Resource.Success(it))
                }
            }else{
                news.postValue(Resource.Error(response.message()))
            }
        }
    }


    //database
    fun saveArtilce(article: Article){
        viewModelScope.launch {
            newsRepository.saveArticle(article)
        }
    }

}