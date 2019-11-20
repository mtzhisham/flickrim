package dev.moataz.flickrim.model.repository.remote.api

import dev.moataz.flickrim.BuildConfig
import dev.moataz.flickrim.application.Consts.API.PARAM_API_KEY
import dev.moataz.flickrim.application.Consts.API.PARAM_MAX_UPLOAD_DATE_KEY
import dev.moataz.flickrim.application.Consts.API.PARAM_MIN_UPLOAD_DATE_KEY
import okhttp3.*
import java.io.IOException
import java.util.*
import kotlin.collections.HashMap


/*
* injecting the API key and start end date to the request
 */
class ApiInterceptor() : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
            return chain.proceed(signRequest(chain.request()))
    }

    @Throws(IOException::class)
    fun signRequest(request: Request): Request {
        val parameters: HashMap<String, String?> = HashMap<String, String?>()
        val url = request.url()
        for (i in 0 until url.querySize()) {
            parameters[url.queryParameterName(i)] = url.queryParameterValue(i)
        }

        parameters[PARAM_API_KEY] =
            BuildConfig.FlickrAPIKey
        val currennt = Calendar.getInstance()

        val mtime = currennt.timeInMillis/1000L
        parameters[PARAM_MAX_UPLOAD_DATE_KEY] = mtime.toString()

        currennt.add(Calendar.YEAR, -1)

        val ltime = currennt.timeInMillis/1000L

        parameters[PARAM_MIN_UPLOAD_DATE_KEY] = ltime.toString()

//        parameters[PARAM_MIN_UPLOAD_DATE_KEY] = (PreferenceHelper.defaultPrefs
//        (MyApp.applicationContext().applicationContext)
//                [Consts.SharedPrefs.PREFS_LAST_SYNC_TIME,ltime.toString()])
//
//        (PreferenceHelper.defaultPrefs(MyApp.applicationContext().applicationContext)
//                [Consts.SharedPrefs.PREFS_LAST_SYNC_TIME]) =  mtime.toString()
//

        val urlBuilder = request.url().newBuilder()
        for (key in parameters.keys) {
            urlBuilder.setQueryParameter(key, parameters[key])
        }

        return request.newBuilder().url(urlBuilder.build()).build()
    }

}

