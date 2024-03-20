package shamsiddin.project.tourvibe.utils

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import shamsiddin.project.tourvibe.model.User

class SharedPreferences private constructor(val context: Context){
    private val shared = context.getSharedPreferences("data", Context.MODE_PRIVATE)
    private val edit = shared.edit()
    val gson = Gson()

    companion object{
        @SuppressLint("StaticFieldLeak")
        private var instance: SharedPreferences? = null
        fun getInstance(context: Context): SharedPreferences{
            if (instance == null){
                instance = SharedPreferences(context)
            }
            return instance!!
        }
    }

    fun setUser(user: User?){
        edit.putString("User", gson.toJson(user)).apply()
    }
    fun getUser(): User?{
        val data = shared.getString("User", "")
        if (data == "") return null
        val typeToken = object : TypeToken<User>() {}.type
        return gson.fromJson(data, typeToken)
    }

    fun setCountry(country: String){
        edit.putString("Country", country).apply()
    }
    fun getCountry(): String{
        return shared.getString("Country", "")!!
    }

    fun logOut() {
        Toast.makeText(context, "Success or fail", Toast.LENGTH_SHORT).show()
        edit.putString("User", "")
    }

}