package dev.moataz.flickrim.model.repository.datasource

import androidx.paging.PageKeyedDataSource
import dev.moataz.flickrim.application.Consts
import dev.moataz.flickrim.application.Consts.API.STAT_OK
import dev.moataz.flickrim.application.MyApp
import dev.moataz.flickrim.util.PreferenceHelper
import dev.moataz.flickrim.util.PreferenceHelper.get
import dev.moataz.flickrim.util.PreferenceHelper.set
import dev.moataz.flickrim.model.data.Photo
import dev.moataz.flickrim.model.repository.local.database.PhotoDatabase
import dev.moataz.flickrim.model.repository.remote.api.ApiService
import dev.moataz.flickrim.model.repository.remote.api.RetrofitClient
import dev.moataz.flickrim.util.InternetUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class PhotosDataSource (private val scope: CoroutineScope) : PageKeyedDataSource<String, Photo>() {

    var isAppFirstRun: Boolean = true

     var db: PhotoDatabase = MyApp.applicationContext().db
    var api: ApiService = RetrofitClient.instance

    init {

        isAppFirstRun = PreferenceHelper
            .defaultPrefs(MyApp.applicationContext())[Consts.SharedPrefs.PREFS_APP_FIRST_TIME,true]
            ?:true

    }

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, Photo>
    ) {
        scope.launch {

            if (InternetUtil.isOnline() && (isAppFirstRun || isRefresh)) {
            api.getInterestingPhotos(page = 1).await().let {
                val response = it
                if (response.isSuccessful) {
                    response.body().let {
                        val responseBody = it
                        if (responseBody?.stat.equals(STAT_OK)
                            && responseBody?.photos?.pages?.toInt()?.compareTo(
                                1
                            ) ?: -1 >= 1
                        ) {
                            responseBody?.photos.let {
                                val photos = it

                                photos?.photo?.let {
                                    db.photoDao().insertAll(it).let {
                                        callback.onResult(
                                            photos.photo?: listOf(),
                                            null,
                                            (photos.page?.plus(1)).toString()
                                        )
                                    }
                                }

                                PreferenceHelper
                                    .defaultPrefs(
                                        MyApp.applicationContext())[
                                        Consts.SharedPrefs.PREFS_APP_FIRST_TIME] = false
                                isRefresh = false
                            }

                        }

                    }

                }
            }
        } else{
                val photos = db.photoDao().getFirstPage()

                if (photos.isNotEmpty()) {
                    callback.onResult(
                        photos,
                        null,
                        photos[photos.size-1]._id.plus(1).toString()
                    )
                }
            }
        }
    }



    override fun loadAfter(
        params: LoadParams<String>,
        callback: LoadCallback<String, Photo>
    ) {



        scope.launch {
            if (InternetUtil.isOnline()) {
            api.getInterestingPhotos(page = params.key.toInt()).await().let {
                val response = it
                if (response.isSuccessful) {
                    response.body().let {
                        val responseBody = it
                        if (responseBody?.stat.equals(STAT_OK) && responseBody?.photos?.pages
                                ?.toInt()?.compareTo(
                                1
                            ) ?: -1 >= 1
                        ) {
                            responseBody?.photos.let {
                                val photos = it
                                photos?.photo?.let {
                                    db.photoDao().insertAll(it).let {
                                        callback.onResult(
                                            photos.photo ?: listOf(),
                                            (photos.page?.plus(1)).toString()
                                        )
                                    }
                                }

                            }

                        }

                    }

                }
            }
        }else{
                val photos = db.photoDao().getPageAfter(params.key.toInt())
                if (photos.isNotEmpty()) {
                    callback.onResult(
                        photos,
                        (params.key.toInt() + 100).toString()

                    )
                }
      }
    }
    }


    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Photo>) {
        //nothing
    }
        fun invalidateDataSource(isRefresh: Boolean) {
        Companion.isRefresh = isRefresh
        scope.launch {
            db.photoDao().nukeTable()
        }

        super.invalidate()
    }

    companion object{
        @JvmStatic
        var isRefresh: Boolean = false

    }
}