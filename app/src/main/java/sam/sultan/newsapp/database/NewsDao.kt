package sam.sultan.newsapp.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import sam.sultan.newsapp.models.Article
import sam.sultan.newsapp.models.News

@Dao
interface NewsDao {

    @Insert
    suspend fun saveArticle(article: Article)

    @Query("SELECT * FROM articles")
    fun getAll(): LiveData<List<Article>>

}