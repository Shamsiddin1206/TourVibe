package shamsiddin.project.tourvibe.model

data class User(
    var id: Int,
    var name: String,
    var email: String,
    var number: String?,
    var country: String,
    var savedDestinations: List<Destination>?,
    var image: String?,
    var savedFoods: List<Food>?,
    var password: String
)
