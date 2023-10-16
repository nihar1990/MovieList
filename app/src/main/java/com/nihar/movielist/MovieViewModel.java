package com.nihar.movielist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieViewModel extends ViewModel {
    private MovieRepository movieRepository;
    private MutableLiveData<List<Result>> moviesData;

    public MovieViewModel() {
        this.movieRepository = new MovieRepository(createApiService());
    }

    public LiveData<List<Result>> getTopRatedMovies(String apiKey) {
        if (moviesData == null) {
            moviesData = movieRepository.getTop10Movies(apiKey);
        }
        return moviesData;
    }

    private ApiService createApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiService.class);
    }
}
