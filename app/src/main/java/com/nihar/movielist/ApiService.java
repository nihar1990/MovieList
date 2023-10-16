package com.nihar.movielist;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(
            @Query("api_key") String apiKey,
            @Query("page") int page,
            @Query("language") String language,
            @Query("region") String region
    );

    @GET("movie/{id}")
    Call<MovieDetails> getMovieDetails(
            @Path("id") int movieId,
            @Query("api_key") String apiKey
    );
}
