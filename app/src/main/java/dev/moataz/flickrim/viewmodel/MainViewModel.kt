package dev.moataz.flickrim.viewmodel

import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import dev.moataz.flickrim.model.data.Photo
import dev.moataz.flickrim.util.InternetUtil
import androidx.lifecycle.*
import dev.moataz.flickrim.model.repository.datasource.PhotosDataSource
import dev.moataz.flickrim.model.repository.remote.sync.UpdateRequestedListener


class MainViewModel : ViewModel(),
    UpdateRequestedListener {



    override fun onUpdateRequested() {
        refreshLayout()
    }

    var photosLiveData: LiveData<PagedList<Photo>>


    init {
        val config = PagedList.Config.Builder()
            .setPageSize(100)
            .setEnablePlaceholders(false)
            .build()

        photosLiveData = initializedPagedListBuilder(config).build()



    }

    fun getPhotoss():LiveData<PagedList<Photo>> = photosLiveData


    var dataSourceLiveData: MutableLiveData<PhotosDataSource>  = MutableLiveData<PhotosDataSource>()

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<String, Photo> {

        val dataSourceFactory = object : DataSource.Factory<String, Photo>() {
            override fun create(): DataSource<String, Photo> {

                val photosDataSource =
                    PhotosDataSource(viewModelScope)
                dataSourceLiveData.postValue(photosDataSource)



                return photosDataSource
            }
        }
        return LivePagedListBuilder<String, Photo>(dataSourceFactory, config)
    }

    public fun refreshLayout(){
        if(InternetUtil.isOnline()){
            dataSourceLiveData.value?.invalidateDataSource(true)
        }
    }



}