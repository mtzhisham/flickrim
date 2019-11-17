package dev.moataz.flickrim

data class OauthKeys(
    var consumerKey: String, //api_key
    var consumerSecret: String, //api_secret
    var accessToken: String? = null, //oath
    var accessSecret: String? = null //oath
) {


        companion object {

                var consumerKey: String = "f2820dd88b1b11070df6ce1387c63553" //api_key
                var consumerSecret: String = "107536027d586f39" //api_secret
                var accessToken: String? = null //oath
                var accessSecret: String? = null  //oath



        }



}