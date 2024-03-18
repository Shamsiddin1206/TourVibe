package shamsiddin.project.tourvibe.model

import android.net.Uri
import com.google.gson.Gson
import shamsiddin.project.tourvibe.utils.JsonNavType

data class Hotel(
    var id: Int,
    var name: String,
    var mainImage:String?,
    var images: List<String>,
    var comments: List<Comment>,
    var description: String,
    var locatedState: String,
    var locatedCountry: String,
    var latitude:Double,
    var longitude:Double,
    var price:String,
    var rating: Double
){
    override fun toString(): String = Uri.encode(Gson().toJson(this))
}
class HotelArgType : JsonNavType<Hotel>(){
    override fun fromJsonParse(value: String): Hotel = Gson().fromJson(value, Hotel::class.java)

    override fun Hotel.getJsonParse(): String = Gson().toJson(this)
}