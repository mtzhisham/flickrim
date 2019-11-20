package dev.moataz.flickrim.application


object Consts{
object SharedPrefs{

    const val PREFS_DEFAULT: String = "PREFS_FLICKRIM"
    const val PREFS_APP_FIRST_TIME: String = "PREFS_APP_FIRST_TIME"
    const val PREFS_LAST_SYNC_TIME: String = "PREFS_LAST_SYNC_TIME"

}


    object Database {
        const val DATABSE_NAME: String = "photos.db"
    }



    object API {
        const val BASE_URL: String =  "https://www.flickr.com/"
        const val API_SERVICE_PATH: String = "services/rest/"
        const val PARAM_METHOD_KEY: String =  "method"
        const val PARAM_METHOD_VALUE: String = "flickr.photos.search"
        const val PARAM_API_KEY: String ="api_key"
        const val PARAM_MIN_UPLOAD_DATE_KEY: String ="min_upload_date"
        const val PARAM_MAX_UPLOAD_DATE_KEY: String = "max_upload_date"
        const val PARAM_SORT_KEY: String ="sort"
        const val PARAM_SORT_VALUE: String ="interestingness-desc"
        const val PARAM_SAFE_SEARCH_KEY: String = "safe_search"
        const val PARAM_SAFE_SEARCH_VALUE: String ="1"
        const val PARAM_JSON_CALLBACK_KEY: String = "nojsoncallback"
        const val PARAM_JSON_CALLBACK_VALUE: String ="1"
        const val PARAM_FPRMAT_KEY: String = "format"
        const val PARAM_FPRMAT_Value: String  = "json"
        const val PARAM_PAGE_KEY: String = "page"
        const val PARAM_PER_PAGE_KEY: String = "per_page"
        const val PARAM_PER_PAGE_VALUE: Int = 100

        const val STAT_OK: String = "ok"


    }



}