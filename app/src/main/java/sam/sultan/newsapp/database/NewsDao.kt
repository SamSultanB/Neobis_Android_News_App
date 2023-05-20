package sam.sultan.newsapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import sam.sultan.newsapp.models.Article

@Dao
interface NewsDao {

    @Insert
    suspend fun saveArticle(article: Article)

    @Query("SELECT * FROM articles")
    fun getAll(): LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)

}