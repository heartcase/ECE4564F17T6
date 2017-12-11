package com.example.pc.parkingsystem;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pc on 2017/12/10.
 */

public class ParkingSpotParcel extends ParkingSpotPackage implements Parcelable{

    protected ParkingSpotParcel(Parcel in) {
        super._id = in.readInt();
        super.uid = in.readInt();
        super.location = in.readString();
        super.name = in.readString();
        super.status = in.readString();
    }

    protected ParkingSpotParcel(ParkingSpotPackage spot){
        super._id = spot._id;
        super.uid = spot.uid;
        super.location = spot.location;
        super.name = spot.name;
        super.status = spot.status;
    }

    public static final Creator<ParkingSpotParcel> CREATOR = new Creator<ParkingSpotParcel>() {
        @Override
        public ParkingSpotParcel createFromParcel(Parcel in) {
            return new ParkingSpotParcel(in);
        }

        @Override
        public ParkingSpotParcel[] newArray(int size) {
            return new ParkingSpotParcel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(getUid());
        parcel.writeInt(get_id());
        parcel.writeString(getLocation());
        parcel.writeString(getName());
        parcel.writeString(getStatus());
    }
}
