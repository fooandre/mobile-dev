package nyp.sit.movieviewer.advanced

import android.content.Context
import nyp.sit.movieviewer.advanced.entity.MovieItem
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList

class movieDBJsonUtils() {

    companion object {

        @Throws(JSONException::class)
        fun getMovieDetailsFromJson(context: Context, movieDetailsJsonStr: String): ArrayList<MovieItem>? {
            val parsedMovieData = ArrayList<MovieItem>()

            val res = JSONObject(movieDetailsJsonStr)
            val movies = res.getJSONArray("results")
            for (i in 0 until movies.length()) {
                val movie = movies.getJSONObject(i)
                parsedMovieData.add(MovieItem(
                    poster_path = movie.getString("poster_path"),
                    adult = movie.getBoolean("adult"),
                    overview = movie.getString("overview"),
                    release_date = movie.getString("release_date"),
                    genre_ids = movie.getString("genre_ids"),
                    id = movie.getInt("id"),
                    original_title = movie.getString("original_title"),
                    original_language = movie.getString("original_language"),
                    title = movie.getString("title"),
                    backdrop_path = movie.getString("backdrop_path"),
                    popularity = movie.getDouble("popularity"),
                    vote_count = movie.getInt("vote_count"),
                    video = movie.getBoolean("video"),
                    vote_average = movie.getDouble("vote_average")
                ))
            }

            return parsedMovieData
        }



    }

}