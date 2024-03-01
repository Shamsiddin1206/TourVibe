package shamsiddin.project.tourvibe.utils

import android.content.Context
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import shamsiddin.project.tourvibe.model.User

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

    fun setUser(user: User){
        edit.putString("User", gson.toJson(user)).apply()
    }
    fun getUser():User?{
        val data = shared.getString("User", "")
        if (data == "") return null
        val typeToken = object : TypeToken<User>() {}.type
        return gson.fromJson(data, typeToken)
    }
}