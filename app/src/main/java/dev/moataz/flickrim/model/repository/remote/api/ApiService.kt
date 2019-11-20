package dev.moataz.flickrim.model.repository.remote.api

import dev.moataz.flickrim.application.Consts.API.API_SERVICE_PATH
import dev.moataz.flickrim.application.Consts.API.PARAM_API_KEY
import dev.moataz.flickrim.application.Consts.API.PARAM_FPRMAT_KEY
import dev.moataz.flickrim.application.Consts.API.PARAM_FPRMAT_Value
import dev.moataz.flickrim.application.Consts.API.PARAM_JSON_CALLBACK_KEY
import dev.moataz.flickrim.application.Consts.API.PARAM_JSON_CALLBACK_VALUE
import dev.moataz.flickrim.application.Consts.API.PARAM_MAX_UPLOAD_DATE_KEY
import dev.moataz.flickrim.application.Consts.API.PARAM_METHOD_KEY
import dev.moataz.flickrim.application.Consts.API.PARAM_METHOD_VALUE
import dev.moataz.flickrim.application.Consts.API.PARAM_MIN_UPLOAD_DATE_KEY
import dev.moataz.flickrim.application.Consts.API.PARAM_PAGE_KEY
import dev.moataz.flickrim.application.Consts.API.PARAM_PER_PAGE_KEY
import dev.moataz.flickrim.application.Consts.API.PARAM_PER_PAGE_VALUE
import dev.moataz.flickrim.application.Consts.API.PARAM_SAFE_SEARCH_KEY
import dev.moataz.flickrim.application.Consts.API.PARAM_SAFE_SEARCH_VALUE
import dev.moataz.flickrim.application.Consts.API.PARAM_SORT_KEY
import dev.moataz.flickrim.application.Consts.API.PARAM_SORT_VALUE
import dev.moataz.flickrim.model.data.ResponseBody
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(API_SERVICE_PATH)
    fun getInterestingPhotos(
        @Query(PARAM_METHOD_KEY) method:String =  PARAM_METHOD_VALUE,
        @Query(PARAM_API_KEY) api_key:String =  "",
        @Query(PARAM_MIN_UPLOAD_DATE_KEY) min_upload_date:String =  "",
        @Query(PARAM_MAX_UPLOAD_DATE_KEY) max_upload_date:String =  "",
        @Query(PARAM_SORT_KEY) sort:String =  PARAM_SORT_VALUE,
        @Query(PARAM_SAFE_SEARCH_KEY) safe_search:String =  PARAM_SAFE_SEARCH_VALUE,
        @Query(PARAM_JSON_CALLBACK_KEY) nojsoncallback:String =  PARAM_JSON_CALLBACK_VALUE,
        @Query(PARAM_FPRMAT_KEY) format:String =  PARAM_FPRMAT_Value,
        @Query(PARAM_PAGE_KEY) page:Int =  1,
        @Query(PARAM_PER_PAGE_KEY) perpage:Int =  PARAM_PER_PAGE_VALUE
    ): Deferred<Response<ResponseBody>>

}