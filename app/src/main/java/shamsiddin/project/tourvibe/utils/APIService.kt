package shamsiddin.project.tourvibe.utils

import retrofit2.Call
import retrofit2.http.Body
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
    @GET("destinations/")
    fun getDestinations(): Call<List<Destination>>
    @GET("destinations/states/")
    fun getDestinationStates(): Call<List<String>>

    @GET("restaurants")
    fun getRestaurants(): Call<List<Restaurant>>
    @GET("hotels")
    fun getHotels(): Call<List<Hotel>>

    @GET("login/{username}/{password}")
    fun login(@Path("username") username: String, @Path("password") password: String): Call<String>

    @GET("register/{username}/{password}/{name}/{country}")
    fun register(@Path("username") username: String,@Path("password") password: String,@Path("name") name: String,@Path("country") country: String): Call<String>

    @GET("foods/categories/")
    fun getFoodsAllCategory(): Call<List<String>>

    @GET("foods/{category}/")
    fun getCategoryFoods(@Path("category") category: String): Call<List<String>>

}