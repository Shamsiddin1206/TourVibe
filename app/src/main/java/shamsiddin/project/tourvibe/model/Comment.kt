package shamsiddin.project.tourvibe.model

data class Comment(
    var id: Int,
    var type: String,
    var author: User,
    var date: String,
    var text: String,
    var rating: Double
)
