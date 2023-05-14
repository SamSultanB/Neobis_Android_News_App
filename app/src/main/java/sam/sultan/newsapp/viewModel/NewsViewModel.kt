package sam.sultan.newsapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import sam.sultan.newsapp.models.News
import sam.sultan.newsapp.repository.NewsRepository
import sam.sultan.newsapp.utils.Resource

class NewsViewModel: ViewModel() {
    val news: MutableLiveData<Response<News>> = MutableLiveData()
    private val page: Int = 1

    private val newsRepository: NewsRepository = NewsRepository()
    fun getNews(){
        viewModelScope.launch {
            newsRepository.getNews(page)
        }
    }


}