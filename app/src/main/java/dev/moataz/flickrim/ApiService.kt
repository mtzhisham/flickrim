package dev.moataz.flickrim

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("services/rest/")
    fun getInterestingPhotos(
        @Query("method") method:String =  "flickr.photos.search",
        @Query("api_key") api_key:String =  "",
        @Query("min_upload_date") min_upload_date:String =  "1571097600",
        @Query("max_upload_date") max_upload_date:String =  "1573862399",
        @Query("sort") sort:String =  "interestingness-desc",
        @Query("safe_search") safe_search:String =  "1",
        @Query("nojsoncallback") nojsoncallback:String =  "1",
        @Query("format") format:String =  "json"
    ): Deferred<Response<ResponseBody>>



    @GET("services/oauth/request_token")
    fun getApiSign(
        @Query("oauth_nonce") method:String =  "",
        @Query("oauth_timestamp") api_key:String =  "",
        @Query("oauth_consumer_key") sort:String =  "",
        @Query("oauth_signature_method") safe_search:String =  "HMAC-SHA1",
        @Query("oauth_version") format:String =  "1.0",
        @Query("oauth_callback") nojsoncallback:String =  "test"

    ): Deferred<Response<TokenRequestResponse>>


    @GET("services/rest/")
    fun getPopular(
        @Query("method") method:String =  "flickr.photos.getPopular",
        @Query("api_key") api_key:String =  "f2820dd88b1b11070df6ce1387c63553",
        @Query("api_sig") api_sig:String =  "",
        @Query("nojsoncallback") nojsoncallback:String =  "1",
        @Query("format") format:String =  "json"

    ): Deferred<Response<JsonObject>>

}