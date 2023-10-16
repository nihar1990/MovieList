package com.nihar.movielist;

import android.os.Parcel;
import android.os.Parcelable;

public class BelongsToCollection implements Parcelable {
    public int id;
    public String name;
    public String poster_path;
    public String backdrop_path;

    protected BelongsToCollection(Parcel in) {
        id = in.readInt();
        name = in.readString();
        poster_path = in.readString();
        backdrop_path = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(poster_path);
        dest.writeString(backdrop_path);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BelongsToCollection> CREATOR = new Creator<BelongsToCollection>() {
        @Override
        public BelongsToCollection createFromParcel(Parcel in) {
            return new BelongsToCollection(in);
        }

        @Override
        public BelongsToCollection[] newArray(int size) {
            return new BelongsToCollection[size];
        }
    };
}
