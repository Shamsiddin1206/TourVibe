package shamsiddin.project.tourvibe.model

data class Hotel(
    var id: Int,
    var name: String,
    var images: List<String>,
    var comments: List<Comment>,
    var overview: String,
    var locatedCity: String,
    var locatedCountry: String,
    var rating: Double
)