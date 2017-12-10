
public class ParkingSpotPackage
{
    /**
            '_id': spot_id,
            'lot_id': lot_id,
            'name': name,
            'floor': floor,
            'status': status,
            'uid': uid
     */
    
    int _id;
    int uid;
    String name;
    String location;
    String status;
    /**
     * @return the _id
     */
    protected int get_id()
    {
        return _id;
    }
    /**
     * @return the uid
     */
    protected int getUid()
    {
        return uid;
    }
    /**
     * @return the name
     */
    protected String getName()
    {
        return name;
    }
    /**
     * @return the floor
     */
    protected String getLocation()
    {
        return location;
    }
    /**
     * @return the status
     */
    protected String getStatus()
    {
        return status;
    }
}
