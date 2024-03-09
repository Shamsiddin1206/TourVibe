package shamsiddin.project.tourvibe.model

import android.net.Uri
import com.google.gson.Gson
import shamsiddin.project.tourvibe.utils.JsonNavType

data class Food(
    var id: Int,
    var mainImage: String,
    var images: List<String>,
    var name: String,
    var description: String,
    var rating: Double,
    var comments: List<Comment>?,
    var rec_places: List<String>?,
    var locatedState: String,
    var locatedCountry: String,
    var caloryInfo: String,
    var price: String
){
    override fun toString(): String = Uri.encode(Gson().toJson(this))

}

class FoodArgType : JsonNavType<Food>(){
    override fun fromJsonParse(value: String): Food = Gson().fromJson(value, Food::class.java)

    override fun Food.getJsonParse(): String = Gson().toJson(this)
}
