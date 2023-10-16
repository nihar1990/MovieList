package com.nihar.movielist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements MovieAdapter.OnItemClickListener {
    private MovieViewModel movieViewModel;
    private MovieAdapter movieAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        movieAdapter = new MovieAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(movieAdapter);

        //Initializing retrofit
        createApiService();

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        String apiKey = "fcd061802893b977ae3629ab5a904465";
        movieViewModel.getTopRatedMovies(apiKey).observe(this, movies -> {
            movieAdapter.setMovies(movies);
            progressBar.setVisibility(View.GONE);
        });

        progressBar.setVisibility(View.VISIBLE);
    }

    private ApiService createApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiService.class);
    }

    @Override
    public void onItemClick(Result movie) {
        progressBar.setVisibility(View.VISIBLE);
        fetchMovieDetails(movie);
    }

    private void fetchMovieDetails(Result movie) {
        ApiService apiService = createApiService();

        Call<MovieDetails> call = apiService.getMovieDetails(movie.getId(), "fcd061802893b977ae3629ab5a904465");

        call.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                if (response.isSuccessful() && response.body() != null) {
                    MovieDetails movieDetails = response.body();
                    launchMovieDetailsActivity(movieDetails, movie);
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "Failed to fetch movie details", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void launchMovieDetailsActivity(MovieDetails movieDetails, Result movie) {
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra("movieDetails", movieDetails);
        intent.putExtra("movie", movie);
        startActivity(intent);
    }
}