package shamsiddin.project.tourvibe.model

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
)
