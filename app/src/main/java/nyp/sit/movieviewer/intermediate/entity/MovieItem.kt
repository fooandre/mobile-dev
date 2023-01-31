package nyp.sit.movieviewer.intermediate.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
class MovieItem(id: Int = 0,
                poster_path: String?,
                adult: Boolean?,
                overview: String?,
                release_date: String?,
                genre_ids: String?,
                original_title: String?,
                original_language: String?,
                title: String?,
                backdrop_path: String?,
                popularity: Double = 0.0,
                vote_count: Int = 0,
                video: Boolean?,
                vote_average: Double = 0.0) {

    @PrimaryKey
    var id: Int = 0

    var poster_path: String? = null

    var adult: Boolean? = null

    var overview: String? = null

    var release_date: String? = null

    var genre_ids: String? = null

    var original_title: String? = null

    var original_language: String? = null

    var title: String? = null

    var backdrop_path: String? = null

    var popularity: Double = 0.0

    var vote_count: Int = 0

    var video: Boolean? = null

    var vote_average: Double = 0.0

    init {
        this.poster_path = poster_path
        this.adult = adult
        this.overview = overview
        this.release_date = release_date
        this.genre_ids = genre_ids
        this.id = id
        this.original_title = original_title
        this.original_language = original_language
        this.title = title
        this.backdrop_path = backdrop_path
        this.popularity = popularity
        this.vote_count = vote_count
        this.video = video
        this.vote_average = vote_average
    }
}