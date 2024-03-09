package shamsiddin.project.tourvibe.utils

import retrofit2.Call
import retrofit2.http.GET
import shamsiddin.project.tourvibe.model.Destination
import shamsiddin.project.tourvibe.model.Food
import shamsiddin.project.tourvibe.model.Hotel
import shamsiddin.project.tourvibe.model.Restaurant

interface APIService {
    @GET("foods")
    fun getFoods(): Call<List<Food>>
    @GET("destinations")
    fun getDestinations(): Call<List<Destination>>
    @GET("restaurants")
    fun getRestaurants(): Call<List<Restaurant>>
    @GET("hotels")
    fun getHotels(): Call<List<Hotel>>

}