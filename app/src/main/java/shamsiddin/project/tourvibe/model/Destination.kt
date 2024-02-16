package shamsiddin.project.tourvibe.model

import android.net.Uri
import com.google.gson.Gson
import shamsiddin.project.tourvibe.utils.JsonNavType

data class Destination(
    var id: Int,
    var mainImage: String,
    var images: List<String>,
    var name: String,
    var description: String,
    var rating: Double,
    var comments: List<Comment>?,
    var category: String,
    var locatedCountry: String,
    var locatedState: String
){
    override fun toString(): String = Uri.encode(Gson().toJson(this))

}

class DestinationArgType : JsonNavType<Destination>(){
    override fun fromJsonParse(value: String): Destination = Gson().fromJson(value, Destination::class.java)

    override fun Destination.getJsonParse(): String = Gson().toJson(this)
}
