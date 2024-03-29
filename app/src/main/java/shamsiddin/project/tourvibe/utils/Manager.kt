package shamsiddin.project.tourvibe.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import shamsiddin.project.tourvibe.model.Destination
import shamsiddin.project.tourvibe.model.Food
import shamsiddin.project.tourvibe.model.Hotel
import shamsiddin.project.tourvibe.model.Restaurant
import shamsiddin.project.tourvibe.model.User

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
                    Log.d("Tag", "${response.body()!!}")
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
                    Log.d("Tag", "${response.body()!!}")
                    callback(response.body()!!)

                }

                override fun onFailure(call: Call<List<Destination>>, t: Throwable) {
                    Log.d("TAG", "$t")
                    callback(emptyList())
                }
            })
        }

        fun getDestinationStates(callback: (List<String>) -> Unit) {
            val api = APIClient.getInstance().create(APIService::class.java)
            api.getDestinationStates().enqueue(object : Callback<List<String>> {
                override fun onResponse(
                    call: Call<List<String>>, response: Response<List<String>>
                ) {
                    Log.d("Tag", "${response.body()!!}")
                    callback(response.body()!!)

                }

                override fun onFailure(call: Call<List<String>>, t: Throwable) {
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
                    Log.d("Tag", "${response.body()!!}")
                    callback(response.body()!!)

                }

                override fun onFailure(call: Call<List<Hotel>>, t: Throwable) {
                    Log.d("TAG", "$t")
                    callback(emptyList())
                }
            })
        }
        fun getHotelCities(callback: (List<String>) -> Unit){
            val api = APIClient.getInstance().create(APIService::class.java)
            api.getHotelCities().enqueue(object : Callback<List<String>> {
                override fun onResponse(
                    call: Call<List<String>>, response: Response<List<String>>
                ) {
//                    Log.d("Tag","${response.body()!!}")
                    callback(response.body()!!)

                }

                override fun onFailure(call: Call<List<String>>, t: Throwable) {
                    Log.d("TAG", "$t")
                    callback(emptyList())
                }
            })
        }

        fun updateUser(user: User, callback: (User) -> Unit){
            val api = APIClient.getInstance().create(APIService::class.java)
            api.edit(user.id, user.email, user.password, user.name, user.country!!).enqueue(object : Callback<User>{
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    Log.d("TAG", "onResponse: ${response.body()}")
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.d("TAG", "$t")
                }
            })
        }

        fun register(
            email: String,
            password: String,
            name: String,
            country: String,
            callback: (User) -> Unit
        ) {
            val api = APIClient.getInstance().create(APIService::class.java)
            api.register(email, password, name, country).enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    Log.d("TAG", "onResponse: ${response.body()}")
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.d("TAG", "$t")
                }
            })
        }

        fun login(email: String, password: String, callback: (User) -> Unit) {
            val api = APIClient.getInstance().create(APIService::class.java)
            api.login(email, password).enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.d("TAG", "$t")

                }
            })
        }

        fun getFoodsAllCategory(callback: (List<String>) -> Unit) {
            val api = APIClient.getInstance().create(APIService::class.java)
            api.getFoodsAllCategory().enqueue(object : Callback<List<String>> {
                override fun onResponse(
                    call: Call<List<String>>, response: Response<List<String>>
                ) {
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<List<String>>, t: Throwable) {
                    callback(emptyList())
                }
            })
        }

        fun getFood(id: Int,callback: (Food) -> Unit) {
            val api = APIClient.getInstance().create(APIService::class.java)
            api.getFood(id).enqueue(object : Callback<Food> {
                override fun onResponse(
                    call: Call<Food>, response: Response<Food>
                ) {
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<Food>, t: Throwable) {
                    Log.d("TAG", "onFailure:$t ")
                }
            })
        }

        fun getRestaurant(id: Int,callback: (Restaurant) -> Unit) {
            val api = APIClient.getInstance().create(APIService::class.java)
            api.getRestaurant(id).enqueue(object : Callback<Restaurant> {
                override fun onResponse(
                    call: Call<Restaurant>, response: Response<Restaurant>
                ) {
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<Restaurant>, t: Throwable) {
                    Log.d("TAG", "onFailure:$t ")
                }
            })
        }

        fun getDestination(id: Int,callback: (Destination) -> Unit) {
            val api = APIClient.getInstance().create(APIService::class.java)
            api.getDestionation(id).enqueue(object : Callback<Destination> {
                override fun onResponse(
                    call: Call<Destination>, response: Response<Destination>
                ) {
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<Destination>, t: Throwable) {
                    Log.d("TAG", "onFailure:$t ")
                }
            })
        }

        fun getHotel(id: Int,callback: (Hotel) -> Unit) {
            val api = APIClient.getInstance().create(APIService::class.java)
            api.getHotel(id).enqueue(object : Callback<Hotel> {
                override fun onResponse(
                    call: Call<Hotel>, response: Response<Hotel>
                ) {
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<Hotel>, t: Throwable) {
                    Log.d("TAG", "onFailure:$t ")
                }
            })
        }



        fun getCategoryFoods(category: String, callback: (List<Food>) -> Unit) {
            val api = APIClient.getInstance().create(APIService::class.java)
            api.getCategoryFoods(category).enqueue(object : Callback<List<Food>> {
                override fun onResponse(
                    call: Call<List<Food>>, response: Response<List<Food>>
                ) {
                    Log.d("Abdulbosit", "onResponse:${response.body()} ")
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<List<Food>>, t: Throwable) {
                    callback(emptyList())
                }
            })
        }

        fun giveComment(
            address: String,
            address_id: Int,
            username: String,
            rating: Double,
            text: String,
            callback: (String) -> Unit
        ) {
            val api = APIClient.getInstance().create(APIService::class.java)
            api.giveComment(address, address_id, username, rating, text).enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    Log.d("abdulbosit api", "onResponse: ${response.body()}")
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    callback("")
                }
            })
        }
    }
}