package dev.moataz.flickrim.model.repository.remote.sync

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.annotation.Nullable
import android.os.Handler
import android.widget.Toast
import dev.moataz.flickrim.R
import dev.moataz.flickrim.application.MyApp

/*
*  only a runnable implemented here, it notifies the SyncObserver each minute
 */
class SyncService : Service() {

    private var handler: Handler? = null

    companion object{
        @JvmStatic
        private var isFirstRun: Boolean = true
    }


    val DEFAULT_SYNC_INTERVAL = (60 * 1000).toLong() //1 min.

    private val runnableService = object : Runnable {
        override fun run() {
            if (!isFirstRun) {
                Toast.makeText(MyApp.applicationContext(), resources.getString(R.string.updating)
                    , Toast.LENGTH_SHORT).show()
                SyncObserver.updatePhotos()
                isFirstRun = false
            }

            handler?.postDelayed(this, DEFAULT_SYNC_INTERVAL)
        }
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        handler = Handler()
        handler?.post(runnableService)
        return START_STICKY
    }

    @Nullable
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onDestroy() {
        handler?.removeCallbacks(runnableService);
        stopSelf()
        super.onDestroy()
    }

}