package com.example.pc.parkingsystem;

public class UserPackage
{
    /**
     *  '_id': uid,
        'username': username,
        'password': None,
        'spot_id': spot_id,
        'status': status,
        'park_time': park_time,
        'logs': logs,
     */
    int _id;
    int spotId;
    int park_hour;
    String username;
    String password;
    String status;
    String park_time;
    
    
    /**
     * @return the park_hour
     */
    protected int getPark_hour()
    {
        return park_hour;
    }
    /**
     * @return the UID
     */
    protected int getUid()
    {
        return _id;
    }
    /**
     * @return the spotId
     */
    protected int getSpotId()
    {
        return spotId;
    }
    /**
     * @return the user name
     */
    protected String getUsername()
    {
        return username;
    }
    /**
     * @return the password
     */
    protected String getPassword()
    {
        return password;
    }
    /**
     * @return the status
     */
    protected String getStatus()
    {
        return status;
    }
    /**
     * @return the park_time
     */
    protected String getPark_time()
    {
        return park_time;
    }
}
