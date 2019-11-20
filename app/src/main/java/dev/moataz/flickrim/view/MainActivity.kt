package dev.moataz.flickrim.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.moataz.flickrim.R
import dev.moataz.flickrim.model.repository.remote.sync.SyncTask

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

}


    override fun onDestroy() {
        SyncTask.stopSyncTask()
        super.onDestroy()
    }

}


