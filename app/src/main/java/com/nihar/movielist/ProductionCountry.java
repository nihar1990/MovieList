package com.nihar.movielist;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductionCountry implements Parcelable {
    public String iso_3166_1;
    public String name;

    protected ProductionCountry(Parcel in) {
        iso_3166_1 = in.readString();
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(iso_3166_1);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProductionCountry> CREATOR = new Creator<ProductionCountry>() {
        @Override
        public ProductionCountry createFromParcel(Parcel in) {
            return new ProductionCountry(in);
        }

        @Override
        public ProductionCountry[] newArray(int size) {
            return new ProductionCountry[size];
        }
    };
}
