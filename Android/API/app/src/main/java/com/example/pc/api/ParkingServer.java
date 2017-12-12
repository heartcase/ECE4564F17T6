package com.example.pc.api;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ParkingServer
{
    @GET("/Login")
    Call<UserPackage> getLogin(@Header("Authorization") String token);
    @GET("/Users/{uid}")
    Call<UserPackage> getUser(@Header("Authorization") String token, @Path("uid") int uid);
    @GET("/ParkingLots/{lot_id}")
    Call<ParkingSpotPackage[]> getParkingSpots(@Header("Authorization") String token, @Path("lot_id") int lot_id);
    @GET("/ParkingLots/{lot_id}/{spot_id}")
    Call<ParkingSpotPackage> getParkingSpot(@Header("Authorization") String token, @Path("lot_id") int lot_id, @Path("spot_id") int spot_id);
    @FormUrlEncoded
    @POST("/ParkingLots/{lot_id}/{spot_id}")
    Call<ParkingSpotPackage> postParkingSpot(@Header("Authorization") String token, @Path("lot_id") int lot_id, @Path("spot_id") int spot_id, @Field("operation") String operation);
}
