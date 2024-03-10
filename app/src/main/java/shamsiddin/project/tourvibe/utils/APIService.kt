package shamsiddin.project.tourvibe.utils

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import shamsiddin.project.tourvibe.model.Destination
import shamsiddin.project.tourvibe.model.Food
import shamsiddin.project.tourvibe.model.Hotel
import shamsiddin.project.tourvibe.model.Restaurant
import shamsiddin.project.tourvibe.model.User

interface APIService {
    @GET("foods")
    fun getFoods(): Call<List<Food>>
    @GET("destinations")
    fun getDestinations(): Call<List<Destination>>
    @GET("restaurants")
    fun getRestaurants(): Call<List<Restaurant>>
    @GET("hotels")
    fun getHotels(): Call<List<Hotel>>
    @POST("register/{email}/{password}/{name}/{country}")
    fun register(@Path("email") email: String, @Path("password") password: String,@Path("name") name: String,@Path("country") country: String): Call<String>
    @GET("login/{email}/{password}")
    fun login(@Path("email") email: String, @Path("password") password: String): Call<String>


}