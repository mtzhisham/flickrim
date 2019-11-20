package dev.moataz.flickrim.application

import androidx.multidex.MultiDexApplication
import dev.moataz.flickrim.model.repository.local.database.PhotoDatabase
import dev.moataz.flickrim.util.PreferenceHelper
import dev.moataz.flickrim.util.PreferenceHelper.set
import dev.moataz.flickrim.util.PreferenceHelper.get

class MyApp :  MultiDexApplication() {

    lateinit var db: PhotoDatabase
    override fun onCreate() {
        super.onCreate()
        instance = this
        db =  PhotoDatabase.invoke(instance)
        if (PreferenceHelper.defaultPrefs(this)
                    [Consts.SharedPrefs.PREFS_APP_FIRST_TIME,true] == false
        ){
            PreferenceHelper
                .defaultPrefs(this)[Consts.SharedPrefs.PREFS_APP_FIRST_TIME] = false
        }

}

    companion object{

        private lateinit var instance: MyApp

        fun applicationContext() : MyApp {
            return instance
        }


    }

}