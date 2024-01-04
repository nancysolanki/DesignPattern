class LibraryCard{
    String name ;
    String role;
    String department;
    int uniqueId;

    LibraryCard(String name ,String role, String department,int uniqueId){
        this.name=name;
        this.role=role;
        this.department=department;
        this.uniqueId=uniqueId;
    }

    String getName(){
        return this.name;
    }

    String getRole(){
        return this.role;
    }

    String getDepartment(){
         return this.department;
    }

    int getUniqueId(){
        return this.uniqueId;
    }
}

class Book{
    String name;
    String barcode;
    int copies;
    
    enum cover{
       HARD,SOFT,NONE; 
    }

    Book(String name , String barcode , int copies){
        this.barcode=barcode;
        this.name=name;
        this.copies=copies;
    }
    
    String getName(){
        return name;
    }

    String getBarcode(){
        return this.barcode;
    }

    int getCopies(){
        return copies;
    }
}

class Issuer{
    LibraryCard libraryCard;
    int booksIssued;
    int dateIssued;
    int endDate;
   
    Issuer(LibraryCard libraryCard, int booksIssued, int dateIssued, int endDate) {
        this.libraryCard = libraryCard;
        this.booksIssued = booksIssued;
        this.dateIssued = dateIssued;
        this.endDate = endDate;
    }

    // Getter methods
    public LibraryCard getLibraryCard() {
        return this.libraryCard;
    }

    public int getBooksIssued() {
        return this.booksIssued;
    }

    public int getDateIssued() {
        return this.dateIssued;
    }

    public int getEndDate() {
        return this.endDate;
    }
   
}

class Librarian{
    LibraryCard libraryCard;
     void issueBook(){
     //logic
     
     }
    void addBook(){
     //logic
     
     }

    void deleteBook(){
     //logic
     
     }


}
class Fine{
    int date;
    Issuer issuer;
    int fine;

      void calculateFine(){
     // logic
     
     }
}
public class LibraryManagementSystem {
    public static void main(String[] args) {
        
    }
    
}
