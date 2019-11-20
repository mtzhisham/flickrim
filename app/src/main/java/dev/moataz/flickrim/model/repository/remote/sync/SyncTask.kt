package dev.moataz.flickrim.model.repository.remote.sync

import android.content.Intent
import dev.moataz.flickrim.application.MyApp
import dev.moataz.flickrim.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
/*
* responsible of starting and canceling the sync service
 */
object SyncTask {

    private val scope: CoroutineScope = GlobalScope
    @JvmStatic
    var isAlreadyStarted: Boolean = false

    fun startSyncTask(viewModel: MainViewModel) {
        if (!isAlreadyStarted) {
        SyncObserver.viewModel = viewModel
        scope.launch {

            MyApp.applicationContext()
                .startService(Intent(MyApp.applicationContext(), SyncService::class.java))
            isAlreadyStarted = true
        }
    }
    }

    fun stopSyncTask(){
        scope.launch {

            MyApp.applicationContext().stopService(Intent(MyApp.applicationContext(),
                SyncService::class.java))

        }

    }


}