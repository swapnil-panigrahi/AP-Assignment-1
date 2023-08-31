import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;
import java.util.Vector;

public class Student {
    Scanner input = new Scanner(System.in);
    private final String Name;
    private final int Age;
    private final String PhoneNumber;
    private int Fine;
    private final Library Lib;
    private Vector <Book> BookIssued;

    public Student(String Name, int Age, String Phone, Library Library){
        this.Name = Name;
        this.Age = Age;
        this.PhoneNumber = Phone;
        this.Fine = 0;
        this.Lib = Library;
        this.BookIssued = null;
    }

    public String getName() {
        return Name;
    }
    public int getAge() {
        return Age;
    }
    public String getID(){
        return this.PhoneNumber;
    }
    public Vector <Book> getIssued() {
        return this.BookIssued;
    }

    public void view_book(){
        Lib.listAllBooks();
    }
    public void issue_book(){
        if (this.Fine!=0){
            System.out.println("Please pay your pending fine before issuing another book!");
        }
        else{
            if (this.BookIssued.size()==2){
                System.out.println("You cannot hold more than 2 books at a time. Please return at least one of them to issue a new one");
            }

            else{
                System.out.println("Enter the Book ID you want to issue: ");
                int bookID = input.nextInt();
                input.nextLine();

                Book toIssue = Lib.issue_book(bookID);
                if (toIssue!=null){
                    this.BookIssued.add(toIssue);
                }
            }
        }
    }
    public void return_book(){
        if (!this.BookIssued.isEmpty()){
            int ID = input.nextInt();
            input.nextLine();

            Book ToReturn = Lib.return_book(this,ID);
            if (ToReturn!=null){
                this.update_fine();
                this.BookIssued.remove(ToReturn);
            }
        }
        else{
            System.out.println("You haven't issued any book");
        }
    }
    public void update_fine(){
        if (!this.BookIssued.isEmpty()){
            for (Book i : BookIssued){
                int days = Duration.between(i.getIssueTime(), Instant.now()).toSecondsPart();

                if (days>10){
                    this.Fine+=days;
                }
            }
        }
    }
    public void pay_fine(){
        this.Fine=0;
    }
}
