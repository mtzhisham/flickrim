package dev.moataz.flickrim

import android.util.Log
import com.google.gson.Gson
import okhttp3.*
import okhttp3.ResponseBody
import okio.Buffer
import okio.ByteString
import org.json.JSONObject
import java.io.IOException
import java.net.URLEncoder
import java.security.GeneralSecurityException
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import kotlin.collections.HashMap
import kotlin.math.log

class Oauth1SigningInterceptor(val nonce: String = UUID.randomUUID().toString(),
                                val timestamp: Long = System.currentTimeMillis() / 1000L) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

//        if (chain.request().url().toString().contains("request_token")) {
//
//            val response = chain.proceed(signRequestTokenRequest(chain.request()))
//
//            var oauth_callback_confirmed:String
//            var oauth_token:String
//            var oauth_token_secret:String
//
//
//
//
//            var paramMap: HashMap<String?, String?> = HashMap<String?, String?>()
//
//            response.body()?.string().let {
//
//                Log.d("myService",  "1"+ it?:"");
//
//                it?.split("&")?.forEach {
//                  val param = it.split("=")
//                  if(param.size > 1)
//                  paramMap[param[0]] = param[1]
//
//                }
//            }
//
//
//            val jsonObject: TokenRequestResponse = TokenRequestResponse(paramMap)
//
//
//            OauthKeys.accessToken =  paramMap["oauth_token"]
//
//
//            OauthKeys.accessSecret= paramMap["oauth_token_secret"]
//
//            Log.d("myService",  "key? "+OauthKeys.accessToken?:"    155" );
//
//            val contentType = "application/json; charset=utf-8"
//
//            Log.d("myService",  jsonObject.toString());
//
//            val body = ResponseBody.create(MediaType.parse(contentType),Gson().toJson(jsonObject))
//            return response.newBuilder().body(body).build()
//
//        } else{
            return chain.proceed(signRequest(chain.request()))
//        }
    }


    @Throws(IOException::class)
    fun signRequest(request: Request): Request {
        val keys = OauthKeys

        Log.d("myService",  "OauthKeys.consumerKey"+ OauthKeys.consumerKey)
//        //Setup default parameters that will be sent with authorization header
        val parameters: HashMap<String, String?> = hashMapOf(
            API_KEY to keys.consumerKey
        )

//        Copy query parameters into param map
        val url = request.url()
        for (i in 0 until url.querySize()) {
            parameters[url.queryParameterName(i)] = url.queryParameterValue(i)
        }

//        Copy form body into param map
        request.body()?.let {
            it.asString().split('&')
                .takeIf { it.isNotEmpty() }
                ?.map { it.split('=', limit = 2) }
                ?.filter {
                    (it.size == 2).also { hasTwoParts ->
                        if (!hasTwoParts) throw IllegalStateException("Key with no value: ${it.getOrNull(0)}")
                    }
                }
                ?.associate {
                    val (key, value) = it
                    key to value
                }
                ?.also { parameters.putAll(it) }
        }
//
//        //Create signature
        val method = request.method().encodeUtf8()
        val baseUrl = request.url().newBuilder().query(null).build().toString().encodeUtf8()
        val signingKey = "${keys.consumerSecret.encodeUtf8()}&${keys.accessSecret?.encodeUtf8()
            ?: ""}"

        parameters[API_KEY] = keys.consumerKey


        val params = parameters.encodeForSignature()



        val dataToSign = "$method&$baseUrl&$params"

        Log.d("myparamas",dataToSign);
        val signt = sign(signingKey, dataToSign)
//        parameters[API_SIG] = signt

        Log.d("myparamas",signt);
        val urlBuilder = request.url().newBuilder()
        for (key in parameters.keys) {
            urlBuilder.setQueryParameter(key, parameters[key])
        }
//        urlBuilder.addQueryParameter(API_SIG, signt)

        val url2: String = urlBuilder.build().url().toString();
        Log.d("myparams", url2)
        return request.newBuilder().url(urlBuilder.build()).build()
    }


//    @Throws(IOException::class)
//    fun signRequestTokenRequest(request: Request): Request {
//        val keys = OauthKeys
//
////        //Setup default parameters that will be sent with authorization header
//        val parameters:HashMap<String, String?> = hashMapOf(
//            OAUTH_CONSUMER_KEY to keys.consumerKey,
//            OAUTH_NONCE to nonce,
//            OAUTH_SIGNATURE_METHOD to OAUTH_SIGNATURE_METHOD_VALUE,
//            OAUTH_TIMESTAMP to timestamp.toString(),
//            OAUTH_VERSION to OAUTH_VERSION_VALUE
//        )
//        keys.accessToken?.let { parameters[OAUTH_TOKEN] = it }
//
////        Copy query parameters into param map
//        val url = request.url()
//        for (i in 0 until url.querySize()) {
//            parameters[url.queryParameterName(i)] = url.queryParameterValue(i)
//        }
//
////        Copy form body into param map
//        request.body()?.let {
//            it.asString().split('&')
//                .takeIf { it.isNotEmpty() }
//                ?.map { it.split('=', limit = 2) }
//                ?.filter {
//                    (it.size == 2).also { hasTwoParts ->
//                        if (!hasTwoParts) throw IllegalStateException("Key with no value: ${it.getOrNull(0)}")
//                    }
//                }
//                ?.associate {
//                    val (key, value) = it
//                    key to value
//                }
//                ?.also { parameters.putAll(it) }
//        }
////
////        //Create signature
//        val method = request.method().encodeUtf8()
//        val baseUrl = request.url().newBuilder().query(null).build().toString().encodeUtf8()
//        val signingKey = "${keys.consumerSecret.encodeUtf8()}&${keys.accessSecret?.encodeUtf8()
//            ?: ""}"
//
//        parameters[OAUTH_CONSUMER_KEY] = keys.consumerKey
//        parameters[OAUTH_NONCE] = nonce
//        parameters[OAUTH_TIMESTAMP] = timestamp.toString()
//
//        val params = parameters.encodeForSignature()
//
//
//        val dataToSign = "$method&$baseUrl&$params"
//        val signt = sign(signingKey, dataToSign)
//        parameters[OAUTH_SIGNATURE] = signt
//        val urlBuilder = request.url().newBuilder()
//        for (key in parameters.keys) {
//            urlBuilder.setQueryParameter(key, parameters[key])
//        }
//        urlBuilder.addQueryParameter(OAUTH_SIGNATURE, signt)
//
//        return request.newBuilder().url(urlBuilder.build()).build()
//    }

    private fun RequestBody.asString() = Buffer().run {
        writeTo(this)
        readUtf8().replace("+", "%2B")
    }

    @Throws(GeneralSecurityException::class)
    private fun sign(key: String, data: String): String {
        Log.d("mySign", "key:" + key + " : " + "data: " + data)
        val secretKey = SecretKeySpec(key.toBytesUtf8(), "HmacSHA1")
        val macResult = Mac.getInstance("HmacSHA1").run {
            init(secretKey)
            doFinal(data.toBytesUtf8())
        }
        return ByteString.of(*macResult).base64()
    }

    private fun String.toBytesUtf8() = this.toByteArray()

    private fun HashMap<String, String?>.encodeForSignature() =
        toList()
            .sortedBy { (key, _) -> key }
            .toMap()
            .map { "${it.key}=${it.value}" }
            .joinToString("&")
            .encodeUtf8()

    private fun String.encodeUtf8() = URLEncoder.encode(this, "UTF-8").replace("+", "%2B")


    companion object {
        private const val METHOD = "method"
        private const val API_KEY = "api_key"
        private const val MIN_UPLOAD_DATE = "min_upload_date"
        private const val MAX_UPLOAD_DATE = "max_upload_date"
        private const val SORT = "sort"
        private const val SAFE_SEARCH = "safe_search"
        private const val FORMAT = "format"
        private const val API_SIG = "api_sig"



        private val baseKeys = arrayListOf(
            METHOD,
            API_KEY,
            MIN_UPLOAD_DATE,
            MAX_UPLOAD_DATE,
            SORT,
            SAFE_SEARCH,
            FORMAT,
            API_SIG
        )
    }
}

