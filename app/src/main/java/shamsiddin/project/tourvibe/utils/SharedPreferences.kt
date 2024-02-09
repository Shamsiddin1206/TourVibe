package shamsiddin.project.tourvibe.utils

import android.content.Context
import com.google.gson.Gson

class SharedPreferences private constructor(context: Context){
    private val shared = context.getSharedPreferences("data", Context.MODE_PRIVATE)
    private val edit = shared.edit()
    val gson = Gson()

    companion object{
        private var instance: SharedPreferences? = null
        fun getInstance(context: Context): SharedPreferences{
            if (instance == null){
                instance = SharedPreferences(context)
            }
            return instance!!
        }
    }

    fun setCountry(country: String){
        edit.putString("Country", country).apply()
    }
    fun getCountry(): String{
        return shared.getString("Country", "")!!
    }
}