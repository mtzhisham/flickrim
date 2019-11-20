package dev.moataz.flickrim.model.repository.remote.sync

/*
* acts as a holder for the viewModel object
 */

object SyncObserver{

    var  viewModel: UpdateRequestedListener?= null

    fun updatePhotos(){
        viewModel?.onUpdateRequested()
    }


}