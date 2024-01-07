class BookingService{

    int tickets;
    String cinema;

    BookingService(int tickets , String cinema){
        this.cinema=cinema;
        this.tickets=tickets;
    }

    void bookTickets(){
        System.out.println("Ticket Booked ");
    }

    int getTickets(){
        return this.tickets;

    }

    String getCinema(){
        return this.cinema;
    }
}

class Payement{
    int userID;
    String paymentMethod;

    enum StateOfPayemnt{
        SUCCESS,FAILED,HOLD;
    }

    Payement(int userId, String paymentMethod){
        this.userID=userId;
        this.paymentMethod=paymentMethod;
    }
}
class Notification{

    void sendNotifications(){
        System.out.println("Sent Notifications via whatsapp");
    }
}

public class BookMyShowLLD {
    public static void main(String[] args) {
        
    }
}
