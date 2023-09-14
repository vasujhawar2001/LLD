import java.util.*;

class Hotel {
    String Name;
    Integer id;
    Location hotelLocation;
    List<Room> roomList;
}
class Location {
    int pin;
    String Street;
    // and so on.......
}

class Room {
    String roomNumber;
    RoomStyle roomStyle; //enum
    RoomStatus roomStatus; //enum
    Double bookingPrice;
    List<HouseKeepingLog> houseKeepingLogs;
}

abstract class HouseKeepingLog{
    String Description;
    Date startDate;
    int duration;
    HouseKeeper houseKeeper;
    public abstract void addRoom(Room room);
}

abstract class Person{
    String name;
    Account accountDetail;
    String phone;
}

public class Account{
    String username;
    String password;
}

abstract class HouseKeeper extends Person{

    public abstract List<Room> getRoomsserviced(Date date);
}

public abstract class Guest extends Person{
    Search searchobj;
    Booking bookingObj;
    public abstract List<RoomBooking> getAllRoomBookings();
}

abstract class Recepetionist extends Person{
    Search searchobj;
    Booking bookingObj;
    public abstract void checkInGues(Guest guest, RoomBooking bookingInfo);
    //checkout similiar
}

class Admin extends Person{
    public void addRoom(Room roomDetail);
    public void deleteRoom(Room roomDetail);
    public void editRoom(Room roomDetail);
}

class Search{
    public List<Room> searchRoom(RoomStyle roomStyle, Date startDate, int duration);
}

class Booking{
    public RoomBooking createBooking(Guest guestInfo);
    public RoomBooking cxancelBooking(int bookingid);
}

class Roombooking{
    String bookingId;
    Date startDate;
    int durationdays;
    BookingStatus bookingstatus;
    List<Guest> guestList;
    List<Room> roomInfo;
    BaseRoomCharge totalroomCharges;
}

//Decorator Pattern

interface BaseRoomCharge{
    Double getCost();
}
class RoomCharge implements BaseRoomCharge{
    double cost;
    public Double getCost(){
        return cost;
    }
    void setCost(double cost){
        this.cost = cost;
    }
}

class RoomServiceCharge implements BaseRoomCharge{
    double cost;
    RoomCharge roomCharge;
    BaseRoomCharge baseRoomCharge;
    public Double getCost(){
        roomCharge.setCost(baseRoomCharge.getCost()+cost);
        return baseRoomCharge.getCost();;
    }
}