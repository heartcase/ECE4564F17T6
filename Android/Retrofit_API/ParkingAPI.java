import java.io.IOException;
import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ParkingAPI
{
    private Retrofit retrofit;
    UserPackage user;
    ParkingSpotPackage parkingSpot;
    ParkingSpotPackage[] parkingSpots;

    public ParkingAPI(String baseUrl)
    {
        initRetrofit(baseUrl);
    }

    public UserPackage getLogin(String username, String password)
            throws IOException
    {
        String authToken = Credentials.basic(username, password);
        Call<UserPackage> call = retrofit.create(ParkingServer.class)
                .getLogin(authToken);
        return call.execute().body();
    }

    public UserPackage getUser(String username, String password, int uid)
            throws IOException
    {
        String authToken = Credentials.basic(username, password);
        Call<UserPackage> call = retrofit.create(ParkingServer.class)
                .getUser(authToken, uid);
        return call.execute().body();
    }

    public ParkingSpotPackage[] getParkingSpots(String filter) throws IOException
    {
        Call<ParkingSpotPackage[]> call = retrofit.create(ParkingServer.class)
                .getParkingSpots(filter);
        return call.execute().body();
    }

    public ParkingSpotPackage getParkingSpot(
            int spot_id) throws IOException
    {
        Call<ParkingSpotPackage> call = retrofit.create(ParkingServer.class)
                .getParkingSpot(spot_id);
        return call.execute().body();
    }

    public ParkingSpotPackage postParkingSpot(String username, String password,
            int spot_id, String operation, int park_hour) throws IOException
    {
        String authToken = Credentials.basic(username, password);
        Call<ParkingSpotPackage> call = retrofit.create(ParkingServer.class)
                .postParkingSpot(authToken, spot_id, operation, park_hour);
        return call.execute().body();
    }

    public void initRetrofit(String baseUrl)
    {
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.build();
    }

}
