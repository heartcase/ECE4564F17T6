package com.example.pc.api;
import java.lang.reflect.Method;
import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ParkingAPI
{
    private Retrofit retrofit;
    UserPackage user;
    ParkingSpotPackage parkingSpot;
    ParkingSpotPackage[] parkingSpots;

    Method callbackOnSucess;
    Method callbackOnFail;
    Object caller;

    public ParkingAPI(String baseUrl, Object c, Method ms, Method mf)
    {
        caller = c;
        callbackOnSucess = ms;
        callbackOnFail = mf;
        initRetrofit(baseUrl);
    }

    public void getLogin(String username, String password)
    {
        String authToken = Credentials.basic(username, password);
        Call<UserPackage> call = retrofit.create(ParkingServer.class)
                .getLogin(authToken);
        requestUser(call);
    }

    public void getUser(String username, String password, int uid)
    {
        String authToken = Credentials.basic(username, password);
        Call<UserPackage> call = retrofit.create(ParkingServer.class)
                .getUser(authToken, uid);
        requestUser(call);
    }

    public void getParkingSpots(String username, String password, int lot_id)
    {
        String authToken = Credentials.basic(username, password);
        Call<ParkingSpotPackage[]> call = retrofit.create(ParkingServer.class)
                .getParkingSpots(authToken, lot_id);
        requestSpots(call);
    }

    public void getParkingSpot(String username, String password, int lot_id,
            int spot_id)
    {
        String authToken = Credentials.basic(username, password);
        Call<ParkingSpotPackage> call = retrofit.create(ParkingServer.class)
                .getParkingSpot(authToken, lot_id, spot_id);
        requestSpot(call);
    }

    public void postParkingSpot(String username, String password, int lot_id,
            int spot_id, String operation)
    {
        String authToken = Credentials.basic(username, password);
        Call<ParkingSpotPackage> call = retrofit.create(ParkingServer.class)
                .postParkingSpot(authToken, lot_id, spot_id, operation);
        requestSpot(call);
    }

    public void initRetrofit(String baseUrl)
    {
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.build();
    }

    public void requestUser(Call<UserPackage> call)
    {
        Callback<UserPackage> callback = new Callback<UserPackage>()
        {
            @Override
            public void onResponse(Call<UserPackage> call,
                    Response<UserPackage> response)
            {
                try
                {
                    user = response.body();
                    callbackOnSucess.invoke(caller, user);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<UserPackage> call, Throwable t)
            {
                try
                {
                    callbackOnFail.invoke(caller);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        };
        call.enqueue(callback);
    }
    
    public void requestSpots(Call<ParkingSpotPackage[]> call)
    {
        Callback<ParkingSpotPackage[]> callback = new Callback<ParkingSpotPackage[]>()
        {
            @Override
            public void onResponse(Call<ParkingSpotPackage[]> call,
                    Response<ParkingSpotPackage[]> response)
            {
                try
                {
                    parkingSpots = response.body();
                    callbackOnSucess.invoke(caller, (Object) parkingSpots);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParkingSpotPackage[]> call, Throwable t)
            {
                try
                {
                    System.out.println("RE: " + t.toString());
                    callbackOnFail.invoke(caller);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        };
        call.enqueue(callback);
    }
    
    public void requestSpot(Call<ParkingSpotPackage> call)
    {
        Callback<ParkingSpotPackage> callback = new Callback<ParkingSpotPackage>()
        {
            @Override
            public void onResponse(Call<ParkingSpotPackage> call,
                    Response<ParkingSpotPackage> response)
            {
                try
                {
                    parkingSpot = response.body();
                    callbackOnSucess.invoke(caller, parkingSpot);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParkingSpotPackage> call, Throwable t)
            {
                try
                {
                    callbackOnFail.invoke(caller);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        };
        call.enqueue(callback);
    }
}
