package com.example.pc.api;
import java.lang.reflect.Method;
class Caller
{

    UserPackage user;
    ParkingSpotPackage spot;
    ParkingSpotPackage spots[];
    String username;
    String password;
    String baseUrl;

    void init() {
        user = null;
        spot = null;
        spots = null;
    }
    
    public void setBaseUrl(String baseUrl)
    {
        this.baseUrl = "http://" + baseUrl + ":5000";
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void getUser()
    {
        init();
        try
        {
            Method ms = Caller.class.getMethod("callbackUser",
                    UserPackage.class);
            Method mf = Caller.class.getMethod("callbackFails");
            ParkingAPI api = new ParkingAPI(baseUrl, this, ms, mf);
            api.getLogin(username, password);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void getUser(int _id)
    {
        init();
        try
        {
            Method ms = Caller.class.getMethod("callbackUser",
                    UserPackage.class);
            Method mf = Caller.class.getMethod("callbackFails");
            ParkingAPI api = new ParkingAPI(baseUrl, this, ms, mf);
            api.getUser(username, password, _id);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void getSpot(int lot_id, int _id)
    {
        init();
        try
        {
            Method ms = Caller.class.getMethod("callbackParkingSpot",
                    ParkingSpotPackage.class);
            Method mf = Caller.class.getMethod("callbackFails");
            ParkingAPI api = new ParkingAPI(baseUrl, this, ms, mf);
            api.getParkingSpot(username, password, lot_id, _id);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void getSpots(int lot_id)
    {
        init();
        try
        {
            Method ms = Caller.class.getMethod("callbackParkingSpots",
                    ParkingSpotPackage[].class);
            Method mf = Caller.class.getMethod("callbackFails");
            ParkingAPI api = new ParkingAPI(baseUrl, this, ms, mf);
            api.getParkingSpots(username, password, lot_id);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void checkInSpot(int lot_id, int _id)
    {
        init();
        try
        {
            Method ms = Caller.class.getMethod("callbackParkingSpot",
                    ParkingSpotPackage.class);
            Method mf = Caller.class.getMethod("callbackFails");
            ParkingAPI api = new ParkingAPI(baseUrl, this, ms, mf);
            api.postParkingSpot(username, password, lot_id, _id, "check_in");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void checkOutSpot(int lot_id, int _id)
    {
        init();
        try
        {
            Method ms = Caller.class.getMethod("callbackParkingSpot",
                    ParkingSpotPackage.class);
            Method mf = Caller.class.getMethod("callbackFails");
            ParkingAPI api = new ParkingAPI(baseUrl, this, ms, mf);
            api.postParkingSpot(username, password, lot_id, _id, "check_out");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void callbackUser(UserPackage user)
    {
        this.user = user;
    }

    public void callbackParkingSpot(ParkingSpotPackage spot)
    {
        System.out.println("spot: " + spot);
        this.spot = spot;

    }

    public void callbackParkingSpots(ParkingSpotPackage[] spots)
    {
        this.spots = spots;

    }

    public void callbackFails()
    {
        System.out.println("Fail");
    }
}