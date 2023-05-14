package sam.sultan.newsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import sam.sultan.newsapp.models.Article

@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(TypeConverter::class)
abstract class NewsDataBase: RoomDatabase(){

    abstract fun getNewsDao(): NewsDao

    companion object{
        @Volatile
        private var INSTANCE: NewsDataBase ?= null

        fun getDatabase(context: Context): NewsDataBase{
            val tempInstance = INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsDataBase::class.java,
                    "news-database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }

}

