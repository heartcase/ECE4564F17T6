package com.example.pc.api;
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
    int lot_id;
    int uid;
    String name;
    String floor;
    String status;
    Body body;
    /**
     * @return the _id
     */
    protected int get_id()
    {
        return _id;
    }
    /**
     * @return the lot_id
     */
    protected int getLot_id()
    {
        return lot_id;
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
    protected String getFloor()
    {
        return floor;
    }
    /**
     * @return the status
     */
    protected String getStatus()
    {
        return status;
    }
    
    public void setBody(String operation) {
        body = new Body();
        body.setOperation(operation);
    }
    
    public class Body {
        
        String operation;

        protected void setOperation(String operation)
        {
            this.operation = operation;
        }
    }
}
