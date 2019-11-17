package dev.moataz.flickrim

import android.util.Log
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType
import kotlin.reflect.typeOf


class LenientMoshiConverter : TypeAdapter<Any>() {
    override fun write(out: JsonWriter?, value: Any?) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        value?.let { Log.d("myParser", value.toString() ) }

    }

    override fun read(`in`: JsonReader?): Any {


        var line: String =""



        while (`in`?.hasNext()!!){
            `in`?.setLenient(true)
            line = `in`?.nextString()?:`in`?.nextName()
            line?.let { Log.d("myParser", line) }
        }


       return line
    }

}