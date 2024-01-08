public class BookMyShow{

    //API
    public List<Cinema> getCinemas(String location , int date);
    //API
    public List<Movie> getMovie(String shows, String Location);

}
class Cinema{
    String name;
    String location;
    List<Audi> audiList;
   
    //API
    public Map<Date,Moviename> getMovies( String name, String location);

    public Map<Date,Moviename> getShows(int date);
}

class Audi{
    int audiId;
    int totalSeats;
    List<Shows> showsList;

}

class Shows{
    int availableSeats;
    List<Movies> moviesList;
    List<Seats>seats;

}

class Seats{
    Cinema cinema;
    Status status;

    //API
    public Map<row,seatNo> availableSeats(Cinema cinema); 
    
    enum Status{
        SUCCESS , RESERVED , EMPTY;
    }

}

class Movie{
  String name;
  String movieId;
  String language;
  String actor;

}

class User{
    int userid;
}

class SystemUser extends User{
    String name;
    int AccountNumber;
    String email;
}

class Customer extends SystemUser{
    //API
    public void makeBooking(){};
    public void cancelBooking(){};

}

class Admin{
    //APIs
    public void addShows(){};
    public void rescheduleShows(){};

}
class Search {

	public List<Movie> searchMoviesByNames(String name);
	public List<Movie> searchMoviesByGenre(Genre genre);

}

public class Booking {

	String bookingId;
	Customer customer;
	Audi audi;
	Shows show;
	Seats.Status bookingStatus;
	double totalAmount;
	List<Seats> seats;
	Payment paymentObj;

	public boolean makePayment(Payment payment);

}
class Payment{
    
    int date;
    int paymentId;
    String mode;
    
}