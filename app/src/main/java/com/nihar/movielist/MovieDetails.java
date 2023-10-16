package com.nihar.movielist;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class MovieDetails implements Parcelable {
    public boolean adult;
    public String backdrop_path;
    public BelongsToCollection belongs_to_collection;
    public int budget;
    public ArrayList<Genre> genres;
    public String homepage;
    public int id;
    public String imdb_id;
    public String original_language;
    public String original_title;
    public String overview;
    public double popularity;
    public String poster_path;
    public ArrayList<ProductionCompany> production_companies;
    public ArrayList<ProductionCountry> production_countries;
    public String release_date;
    public int revenue;
    public int runtime;
    public ArrayList<SpokenLanguage> spoken_languages;
    public String status;
    public String tagline;
    public String title;
    public boolean video;
    public double vote_average;
    public int vote_count;

    protected MovieDetails(Parcel in) {
        adult = in.readByte() != 0;
        backdrop_path = in.readString();
        belongs_to_collection = in.readParcelable(BelongsToCollection.class.getClassLoader());
        budget = in.readInt();
        genres = in.createTypedArrayList(Genre.CREATOR);
        homepage = in.readString();
        id = in.readInt();
        imdb_id = in.readString();
        original_language = in.readString();
        original_title = in.readString();
        overview = in.readString();
        popularity = in.readDouble();
        poster_path = in.readString();
        production_companies = in.createTypedArrayList(ProductionCompany.CREATOR);
        production_countries = in.createTypedArrayList(ProductionCountry.CREATOR);
        release_date = in.readString();
        revenue = in.readInt();
        runtime = in.readInt();
        spoken_languages = in.createTypedArrayList(SpokenLanguage.CREATOR);
        status = in.readString();
        tagline = in.readString();
        title = in.readString();
        video = in.readByte() != 0;
        vote_average = in.readDouble();
        vote_count = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (adult ? 1 : 0));
        dest.writeString(backdrop_path);
        dest.writeParcelable(belongs_to_collection, flags);
        dest.writeInt(budget);
        dest.writeTypedList(genres);
        dest.writeString(homepage);
        dest.writeInt(id);
        dest.writeString(imdb_id);
        dest.writeString(original_language);
        dest.writeString(original_title);
        dest.writeString(overview);
        dest.writeDouble(popularity);
        dest.writeString(poster_path);
        dest.writeTypedList(production_companies);
        dest.writeTypedList(production_countries);
        dest.writeString(release_date);
        dest.writeInt(revenue);
        dest.writeInt(runtime);
        dest.writeTypedList(spoken_languages);
        dest.writeString(status);
        dest.writeString(tagline);
        dest.writeString(title);
        dest.writeByte((byte) (video ? 1 : 0));
        dest.writeDouble(vote_average);
        dest.writeInt(vote_count);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MovieDetails> CREATOR = new Creator<MovieDetails>() {
        @Override
        public MovieDetails createFromParcel(Parcel in) {
            return new MovieDetails(in);
        }

        @Override
        public MovieDetails[] newArray(int size) {
            return new MovieDetails[size];
        }
    };
}
