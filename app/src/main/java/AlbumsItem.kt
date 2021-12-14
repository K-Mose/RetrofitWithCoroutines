import com.google.gson.annotations.SerializedName

// add plugin 'JSON to Kotlin class'
data class AlbumsItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)