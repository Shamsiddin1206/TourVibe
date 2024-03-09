package shamsiddin.project.tourvibe.utils

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import shamsiddin.project.tourvibe.model.Destination
import shamsiddin.project.tourvibe.model.Food
import shamsiddin.project.tourvibe.model.Hotel
import shamsiddin.project.tourvibe.model.Restaurant

class Manager {

    companion object {
        fun getFoods(callback: (List<Food>) -> Unit) {
            val api = APIClient.getInstance().create(APIService::class.java)
            api.getFoods().enqueue(object : Callback<List<Food>> {
                override fun onResponse(
                    call: Call<List<Food>>, response: Response<List<Food>>
                ) {
                    Log.d("Tag", "${response.body()!!}")
                    callback(response.body()!!)

                }

                override fun onFailure(call: Call<List<Food>>, t: Throwable) {
                    Log.d("TAG", "$t")
                    callback(emptyList())
                }
            })
        }

        fun getRestaurants(callback: (List<Restaurant>) -> Unit) {
            val api = APIClient.getInstance().create(APIService::class.java)
            api.getRestaurants().enqueue(object : Callback<List<Restaurant>> {
                override fun onResponse(
                    call: Call<List<Restaurant>>, response: Response<List<Restaurant>>
                ) {
                    Log.d("Tag","${response.body()!!}")
                    callback(response.body()!!)

                }

                override fun onFailure(call: Call<List<Restaurant>>, t: Throwable) {
                    Log.d("TAG", "$t")
                    callback(emptyList())
                }
            })
        }

        fun getDestinations(callback: (List<Destination>) -> Unit) {
            val api = APIClient.getInstance().create(APIService::class.java)
            api.getDestinations().enqueue(object : Callback<List<Destination>> {
                override fun onResponse(
                    call: Call<List<Destination>>, response: Response<List<Destination>>
                ) {
                    Log.d("Tag","${response.body()!!}")
                    callback(response.body()!!)

                }

                override fun onFailure(call: Call<List<Destination>>, t: Throwable) {
                    Log.d("TAG", "$t")
                    callback(emptyList())
                }
            })
        }

        fun getHotels(callback: (List<Hotel>) -> Unit) {
            val api = APIClient.getInstance().create(APIService::class.java)
            api.getHotels().enqueue(object : Callback<List<Hotel>> {
                override fun onResponse(
                    call: Call<List<Hotel>>, response: Response<List<Hotel>>
                ) {
                    Log.d("Tag","${response.body()!!}")
                    callback(response.body()!!)

                }

                override fun onFailure(call: Call<List<Hotel>>, t: Throwable) {
                    Log.d("TAG", "$t")
                    callback(emptyList())
                }
            })
        }
    }
}