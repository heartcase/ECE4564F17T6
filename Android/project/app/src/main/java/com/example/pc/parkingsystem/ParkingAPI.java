package com.example.pc.parkingsystem;

import android.util.Log;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ParkingAPI
{
    private String flag[] = {"User", "ParkingSpot"};
    private Retrofit retrofit;
    UserPackage user;
    ParkingSpotPackage parkingSpot;
    ParkingSpotPackage parkingSpots[];
    Method callbackUserPackage;
    Method callbackParkingSpotPackage;
    Method callbackParkingSpotPackages;
    Object caller;

    public ParkingAPI(String baseUrl, Object caller, Method callbackUser, Method callbackSpot, Method callbackSpots)
    {
        this.caller = caller;
        this.callbackUserPackage = callbackUser;
        this.callbackParkingSpotPackage = callbackSpot;
        this.callbackParkingSpotPackages = callbackSpots;
        initRetrofit(baseUrl);
    }

    public void getLogin(String username, String password)
            throws IOException
    {
        String authToken = Credentials.basic(username, password);
        Call<UserPackage> call = retrofit.create(ParkingServer.class)
                .getLogin(authToken);
        callbackUser(call);
    }

    public void getUser(String username, String password, int uid)
            throws IOException
    {
        String authToken = Credentials.basic(username, password);
        Call<UserPackage> call = retrofit.create(ParkingServer.class)
                .getUser(authToken, uid);
        callbackUser(call);
    }

    public void getParkingSpots(String filter) throws IOException
    {
        Call<ParkingSpotPackage[]> call = retrofit.create(ParkingServer.class)
                .getParkingSpots(filter);
        callbackParkingSpotPackages(call);
    }

    public void getParkingSpot(
            int spot_id) throws IOException
    {
        Call<ParkingSpotPackage> call = retrofit.create(ParkingServer.class)
                .getParkingSpot(spot_id);
        callbackParkingSpotPackage(call);
    }

    public void postParkingSpot(String username, String password,
                                              int spot_id, String operation, int park_hour) throws IOException
    {
        String authToken = Credentials.basic(username, password);
        Call<ParkingSpotPackage> call = retrofit.create(ParkingServer.class)
                .postParkingSpot(authToken, spot_id, operation, park_hour);
        callbackParkingSpotPackage(call);
    }

    public void initRetrofit(String baseUrl)
    {
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.build();
    }

    public void callbackUser(Call<UserPackage> call) {
        user = null;
        try {
            call.enqueue(new Callback<UserPackage>() {
                @Override
                public void onResponse(Call<UserPackage> call, Response<UserPackage> response) {
                    user = response.body();
                    try {
                        callbackUserPackage.invoke(caller, user);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onFailure(Call<UserPackage> call, Throwable t) {
                    try {
                        callbackUserPackage.invoke(caller, user);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void callbackParkingSpotPackage(Call<ParkingSpotPackage> call) {
        parkingSpot = null;
        try {
            call.enqueue(new Callback<ParkingSpotPackage>() {
                @Override
                public void onResponse(Call<ParkingSpotPackage> call, Response<ParkingSpotPackage> response) {
                    parkingSpot = response.body();
                    try {
                        callbackParkingSpotPackage.invoke(caller, parkingSpot);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onFailure(Call<ParkingSpotPackage> call, Throwable t) {
                    try {
                        callbackParkingSpotPackage.invoke(caller, parkingSpot);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void callbackParkingSpotPackages(Call<ParkingSpotPackage[]> call) {
        parkingSpots = new ParkingSpotPackage[]{};
        try {
            call.enqueue(new Callback<ParkingSpotPackage[]>() {
                @Override
                public void onResponse(Call<ParkingSpotPackage[]> call, Response<ParkingSpotPackage[]> response) {
                    parkingSpots = response.body();
                    try {
                        callbackParkingSpotPackages.invoke(caller, (Object) parkingSpots);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onFailure(Call<ParkingSpotPackage[]> call, Throwable t) {
                    try {
                        callbackParkingSpotPackages.invoke(caller, (Object) parkingSpots);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
