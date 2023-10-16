package com.nihar.movielist;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductionCompany implements Parcelable {
    public int id;
    public String logo_path;
    public String name;
    public String origin_country;

    protected ProductionCompany(Parcel in) {
        id = in.readInt();
        logo_path = in.readString();
        name = in.readString();
        origin_country = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(logo_path);
        dest.writeString(name);
        dest.writeString(origin_country);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProductionCompany> CREATOR = new Creator<ProductionCompany>() {
        @Override
        public ProductionCompany createFromParcel(Parcel in) {
            return new ProductionCompany(in);
        }

        @Override
        public ProductionCompany[] newArray(int size) {
            return new ProductionCompany[size];
        }
    };
}
