package com.nihar.movielist;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MovieDetailsActivity extends AppCompatActivity {
    private ImageView imgPoster;
    private TextView txtTitle, txtOverviewLabel, txtOverview, txtReleaseDate, txtAverageRating,txtBudget,txtRevenue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Result movie = getIntent().getParcelableExtra("movie");
        MovieDetails movieDetails = getIntent().getParcelableExtra("movieDetails");

        imgPoster = findViewById(R.id.imgPoster);
        txtTitle = findViewById(R.id.txtTitle);
        txtOverviewLabel = findViewById(R.id.txtOverviewLabel);
        txtOverview = findViewById(R.id.txtOverview);
        txtReleaseDate = findViewById(R.id.txtReleaseDate);
        txtAverageRating = findViewById(R.id.txtAverageRating);
        txtBudget = findViewById(R.id.txtBudget);
        txtRevenue = findViewById(R.id.txtRevenue);

        Picasso.get()
                .load("https://image.tmdb.org/t/p/w500" + movie.getPoster_path())
                .into(imgPoster);

        txtTitle.setText(movieDetails.original_title);
        txtOverviewLabel.setText("Overview");
        txtOverview.setText(movieDetails.overview);
        txtReleaseDate.setText("Release Date: " + getDate(movieDetails.release_date));
        txtAverageRating.setText("Average Rating: " + movieDetails.vote_average);
        txtBudget.setText("Budget: " + withSuffix(movieDetails.budget));
        txtRevenue.setText("Revenue: " + withSuffix(movieDetails.revenue));
    }

    public static String withSuffix(long count) {
        if (count < 1000) return "" + count;
        int exp = (int) (Math.log(count) / Math.log(1000));
        return String.format("%.1f %c",
                count / Math.pow(1000, exp),
                "kMGTPE".charAt(exp-1));
    }

    private String getDate(String rawDate) {
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy");
        Date date = null;
        try {
            date = inputFormat.parse(rawDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String newDate = outputFormat.format(date);
        return newDate;
    }
}