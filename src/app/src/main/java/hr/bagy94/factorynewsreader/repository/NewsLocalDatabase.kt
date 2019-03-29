package hr.bagy94.factorynewsreader.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import hr.bagy94.factorynewsreader.model.Article

@Database(entities = [Article::class],version = 1,exportSchema = false)
abstract class NewsLocalDatabase : RoomDatabase() {
    abstract fun articleDao():ArticleDao
}