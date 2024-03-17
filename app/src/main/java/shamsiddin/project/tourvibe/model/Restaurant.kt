package shamsiddin.project.tourvibe.model

import android.net.Uri
import com.google.gson.Gson
import shamsiddin.project.tourvibe.utils.JsonNavType

data class Restaurant(
    var longtitude : String,
    var latitude : String,
    var price:String,
    var caloryInfo: String,
    var rating: Double,
    var mainImage:String,
    var name:String,
    var overViewVideo:String
){
    override fun toString(): String = Uri.encode(Gson().toJson(this))

}

class RestaurantArgType : JsonNavType<Restaurant>(){
    override fun fromJsonParse(value: String): Restaurant = Gson().fromJson(value, Restaurant::class.java)

    override fun Restaurant.getJsonParse(): String = Gson().toJson(this)
}
