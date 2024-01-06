import java.util.HashMap;
import java.util.Map;

class BookDelivery{
    String title;
    Map<Integer, String> address;

    BookDelivery(String title , Map<Integer,String> address){
        this.address = new HashMap<>(address);
        this.title=title;
}

    public Map<Integer, String> getAddress() {
    return address;
    }
    public String getTitle() {
    return this.title;
    }

}

class OfflineDelivery extends BookDelivery {
    Boolean status;
    BookDelivery bookDelivery;
    OfflineDelivery(BookDelivery bookDelivery, Boolean status) {
        super(bookDelivery.getTitle(), bookDelivery.getAddress());
        this.bookDelivery = bookDelivery;  // Initialize the bookDelivery field
        this.status = status;
    }

    public void delivery() {
        if (getAddress().containsValue("1194 , UP , India")) {
            System.out.println("Delivered");
            status = true;
        } else {
            System.out.println("Address not found. Delivery failed.");
            status = false;
        }
    }

    Boolean getStatus(){
        return status;
    }
}

class OnlineDelivery extends BookDelivery{
    BookDelivery bookDelivery;
    Boolean status;

    OnlineDelivery(BookDelivery bookDelivery,Boolean status){
     super(bookDelivery.getTitle(), bookDelivery.getAddress());
     this.bookDelivery = bookDelivery;
     this.status=status;
    }
    public void send(){
     System.out.println("Online Delivered " + bookDelivery.getTitle());
     status=true;

    }
        Boolean getStatus(){
        return status;
    }
}

class Hardcover extends OfflineDelivery{
OfflineDelivery offlineDelivery;

Hardcover(OfflineDelivery offlineDelivery)
{
    super(offlineDelivery.bookDelivery,offlineDelivery.status);
    this.offlineDelivery=offlineDelivery;
}
@Override
public void delivery(){
    super.delivery();
}

}
public class LiskovsPrinciple {
    public static void main(String[] args) {
        Map<Integer, String> newaddress=new HashMap<>();
        newaddress.put(1, "1194 , UP , India");
        newaddress.put(2, "1195 , Delhi , India");
        BookDelivery bookDelivery= new BookDelivery("A to Z CS", newaddress);
        System.out.println(bookDelivery.getTitle() + " "  );
        //create object of offlineDelivery
        OfflineDelivery offlineDelivery= new OfflineDelivery(bookDelivery, false);
        //Hardcover object
        Hardcover hardcover = new Hardcover(offlineDelivery);
    
        hardcover.delivery();  
        System.out.println("Hardcover class function " + hardcover.getStatus() );    
    }
}
