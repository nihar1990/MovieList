package com.nihar.movielist;

import android.os.Parcel;
import android.os.Parcelable;

public class SpokenLanguage implements Parcelable {
    public String english_name;
    public String iso_639_1;
    public String name;

    protected SpokenLanguage(Parcel in) {
        english_name = in.readString();
        iso_639_1 = in.readString();
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(english_name);
        dest.writeString(iso_639_1);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SpokenLanguage> CREATOR = new Creator<SpokenLanguage>() {
        @Override
        public SpokenLanguage createFromParcel(Parcel in) {
            return new SpokenLanguage(in);
        }

        @Override
        public SpokenLanguage[] newArray(int size) {
            return new SpokenLanguage[size];
        }
    };
}
