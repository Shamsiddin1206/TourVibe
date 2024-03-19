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
    @GET("foods/")
    fun getFoods(): Call<List<Food>>

    @GET("destinations/")
    fun getDestinations(): Call<List<Destination>>

    @GET("destinations/states/")
    fun getDestinationStates(): Call<List<String>>

    @GET("restaurants/")
    fun getRestaurants(): Call<List<Restaurant>>

    @GET("hotels/")
    fun getHotels(): Call<List<Hotel>>

    @GET("hotels/categories/")
    fun getHotelCities(): Call<List<String>>

    @GET("login/{username}/{password}")
    fun login(@Path("username") username: String, @Path("password") password: String): Call<User>


    @GET("food/{id}")
    fun getFood(@Path("id") id: Int): Call<Food>

    @GET("restaurant/{id}")
    fun getRestaurant(@Path("id") id: Int): Call<Restaurant>

    @GET("destination/{id}")
    fun getDestionation(@Path("id") id: Int): Call<Destination>

    @GET("hotel/{id}")
    fun getHotel(@Path("id") id: Int): Call<Hotel>

    @GET("register/{username}/{password}/{name}/{country}")
    fun register(
        @Path("username") username: String,
        @Path("password") password: String,
        @Path("name") name: String,
        @Path("country") country: String
    ): Call<User>

    @GET("foods/categories/")
    fun getFoodsAllCategory(): Call<List<String>>

    @GET("foods/{category}")
    fun getCategoryFoods(@Path("category") category: String): Call<List<Food>>

    @POST("comment/{address}/{address_id}/{username}/{rating}")
    fun giveComment(
        @Path("address") address: String,
        @Path("address_id") address_id: Int,
        @Path("username") username: String,
        @Path("rating") rating: Double,
        @Body text: String
    ): Call<String>

}