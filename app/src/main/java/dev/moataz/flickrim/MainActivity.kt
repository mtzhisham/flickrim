package dev.moataz.flickrim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.Main) {

         RetrofitClient.instance.getInterestingPhotos().await().let {

//
//             it.body()?.listTokens?.forEach { (key, value) -> Log.d("myService","$key = $value") }
//
//             if(it.isSuccessful) {
//                 Log.d("myAsync", "" + it.code() + " " + it.body()?.toString())
//                 Log.d("myAsync",  "from main"+ OauthKeys.accessToken)
//                 RetrofitClient.instance.getInterestingPhotos().await().let {
//                     Log.d("myAsync", "" + it.code() + " " + it.body()?.toString())
//                     Toast.makeText(this@MainActivity, "myAsync", Toast.LENGTH_SHORT).show()
//                 }
//             }

             if (it.isSuccessful)
             Log.d("myAsync", "" + it.code() + " " + it.body()?.photos?.total)





         }




        }



    }
}
