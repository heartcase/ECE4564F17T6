package com.example.pc.parkingsystem;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pc on 2017/12/10.
 */

public class UserParcel extends UserPackage implements Parcelable{

    protected UserParcel(Parcel in) {
        super._id = in.readInt();
        super.park_hour = in.readInt();
        super.spotId = in.readInt();
        super.park_time = in.readString();
        super.password = in.readString();
        super.status = in.readString();
        super.username = in.readString();
    }

    protected UserParcel(UserPackage user){
        super._id = user._id;
        super.park_hour = user.park_hour;
        super.spotId = user.spotId;
        super.park_time = user.park_time;
        super.password = user.password;
        super.status = user.status;
        super.username = user.username;
    }

    public static final Creator<UserParcel> CREATOR = new Creator<UserParcel>() {
        @Override
        public UserParcel createFromParcel(Parcel in) {
            return new UserParcel(in);
        }

        @Override
        public UserParcel[] newArray(int size) {
            return new UserParcel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(getUid());
        parcel.writeInt(getPark_hour());
        parcel.writeInt(getSpotId());
        parcel.writeString(getPark_time());
        parcel.writeString(getPassword());
        parcel.writeString(getStatus());
        parcel.writeString(getUsername());

    }
}
