package dev.moataz.flickrim.model.repository.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.moataz.flickrim.application.Consts.Database.DATABSE_NAME
import dev.moataz.flickrim.model.data.Photo

@Database(entities = [Photo::class],
    version = 1
)
abstract class PhotoDatabase : RoomDatabase() {

    abstract fun photoDao(): PhotoDao


    companion object{

        @Volatile private var instance: PhotoDatabase? =null
        private val  LOCK = Any()


        operator fun invoke(context: Context) = instance?: synchronized(LOCK){
            instance?: buildDatabse(context).also{ instance = it}
        }

        operator fun invoke() = instance

        private fun buildDatabse(context: Context) =  Room.databaseBuilder(context,
            PhotoDatabase::class.java,
            DATABSE_NAME).build()



    }



}