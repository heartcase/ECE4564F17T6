import java.io.IOException;

public class Test
{
    static UserPackage user;
    static ParkingSpotPackage parkingSpot;
    static ParkingSpotPackage[] parkingSpots;
    
	public static void main(String args[]) throws IOException {
        String username = "User3";
        String password = "321";
        String baseUrl = "http://localhost:5000";
        String operation = "check_out";
        int uid = 1;
        int lot_id = 1;
        int spot_id = 2;
        int park_hour = 1;
        String filter = "all";
        ParkingAPI apiTest = new ParkingAPI(baseUrl);
        //Example Login
        //get user object from server
        user = apiTest.getLogin(username, password);
        //print uid
        System.out.println(user.getUid());
        //Example get parking spots
        parkingSpots = apiTest.getParkingSpots(filter);
        for(ParkingSpotPackage ps: parkingSpots) {
            System.out.println(ps.getName());
            System.out.println(ps.get_id());
        }
        //Example Check in/ Check out
        operation = "check_in";
        parkingSpot = apiTest.postParkingSpot(username, password, spot_id, operation, park_hour);
        System.out.println(parkingSpot.getStatus());
        operation = "check_out";
        parkingSpot = apiTest.postParkingSpot(username, password, spot_id, operation, park_hour);
        System.out.println(parkingSpot.getStatus());
    }
}
