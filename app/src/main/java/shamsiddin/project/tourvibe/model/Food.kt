package shamsiddin.project.tourvibe.model

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
)
