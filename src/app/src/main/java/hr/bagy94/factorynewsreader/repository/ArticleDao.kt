package hr.bagy94.factorynewsreader.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import hr.bagy94.factorynewsreader.model.Article

@Dao
interface ArticleDao {
    @Insert
    fun insertAll(news:List<Article>)

    @Query ("DELETE FROM article")
    fun deleteAll()


    @Query("SELECT * FROM article")
    fun getAll():LiveData<List<Article>>

    @Query("SELECT COUNT(*) FROM article")
    fun count():Int
}