package dev.moataz.flickrim.model.repository.local.database

import androidx.room.*
import dev.moataz.flickrim.model.data.Photo


@Dao
interface PhotoDao {

    @Query("SELECT * FROM photo LIMIT 100")
    suspend fun getFirstPage() : List<Photo>


    @Query("SELECT * FROM photo WHERE _id BETWEEN :from and :from+100")
    suspend fun getPageAfter(from: Int) : List<Photo>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(photos:  List<Photo?>) : List<Long>

    @Query("DELETE FROM photo")
    suspend fun nukeTable()


}