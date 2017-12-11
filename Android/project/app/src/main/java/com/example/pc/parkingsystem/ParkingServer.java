package com.example.pc.parkingsystem;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ParkingServer
{
    @GET("/Login")
    Call<UserPackage> getLogin(
            @Header("Authorization") String token
            );
    @GET("/Users/{uid}")
    Call<UserPackage> getUser(
            @Header("Authorization") String token,
            @Path("uid") int uid
            );
    @GET("/ParkingSpots")
    Call<ParkingSpotPackage[]> getParkingSpots(
            @Query("filter") String filter
            );
    @GET("/ParkingSpots/{spot_id}")
    Call<ParkingSpotPackage> getParkingSpot(
            @Path("spot_id") int spot_id
            );
    @FormUrlEncoded
    @POST("/ParkingSpots/{spot_id}")
    Call<ParkingSpotPackage> postParkingSpot(
            @Header("Authorization") String token, 
            @Path("spot_id") int spot_id,
            @Field("operation") String operation,
            @Field("park_hour") int park_hour
            );
}
