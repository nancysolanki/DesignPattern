import java.util.*;

class Hotel {
    String name;     
    List<String> location = new ArrayList<>();
    ArrayList<String> locationArrayList = new ArrayList<>();
    

Hotel (String name, List<String> location) {
   if (name != null) 
        this.name=name;
    else
        this.name = "Default name";

    if (location != null) 
        this.location = location;
    else
        this.location = new ArrayList<>();    
   }

String getName() {
    return this.name;
}




void addLocation(String location){
    this.location.add(location);
    
}


List<String> getLocation(){
    return this.location;
}
}

class Room{
    int roomNumber;
    String roomType;
    int price;
    StatusOfRoom statusofroom;

  
    Room (int roomNumber, String roomType, int price){
        if(roomType!=null)
        this.roomType=roomType;
        else
        this.roomType="Deluxe";

        if(roomNumber>1)
        this.roomNumber=roomNumber;
        else
        this.roomNumber=1;

        if(price>1)
        this.price=price;
        else
        this.price=1;
        
    }

    String getroomType(){
        return this.roomType;
    }
    int getroomNumber(){
        return this.roomNumber;
    }
    int getprice(){
        return this.price;
    }

    enum StatusOfRoom{
        RESERVED,CANCELLED,INPROGRESS;
    }

}
class BookingRoom{
       private Room room;
       private String guestName;
       private int nights;

    BookingRoom(Room room , String guestName , int nights){
        if(room!=null)
            this.room=room;
        if(guestName!=null)
            this.guestName=guestName;
        else
            this.guestName="NULL";
        if(nights>0)
            this.nights=nights;
        else
            this.nights=0;    
           
    }

    Room getRoom(){
        return this.room;
    }
    String getguestName(){
        return this.guestName;
    }
    int getnights(){
        return this.nights;
    }

}
public class HotelManagementSystem {
    public static void main(String[] args) {
        //Hotel
        Hotel hotel=new Hotel("Picaso", new ArrayList<>());
        hotel.addLocation("Church Street");
        System.out.println("HotelName : " + hotel.getName());
        System.out.println("Location of the hotel : "+hotel.getLocation());

        //Room
        Room room=new Room(101, "Luxury", 2300);
        System.out.println("Room Number allocated :"+ room.getroomNumber()+" and the room is of type : "+ room.getroomType()+" of Price : "+ room.getprice() +" "+ room.statusofroom.RESERVED);
        
        //RoomBooking
        BookingRoom bookingroom=new BookingRoom(room, "jinks", 2);
        System.out.println(bookingroom.getguestName()+ " picked room "+ bookingroom.getRoom().roomNumber+ " for nights = "+ bookingroom.getnights());


    }
}
