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
    private final Vector <Book> BookIssued;

    public Student(String Name, int Age, String Phone, Library Library){
        this.Name = Name;
        this.Age = Age;
        this.PhoneNumber = Phone;
        this.Fine = 0;
        this.Lib = Library;
        this.BookIssued = new Vector<>();
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
    public int getFine(){
        return this.Fine;
    }

    public void view_book(){
        Lib.listAllBooks();
    }
    public void issue_book(){
        if (this.Fine!=0){
            System.out.println("Fine due: "+ this.Fine);
            System.out.println("Please pay your pending fine before issuing another book!");
        }
        else{
            if (this.BookIssued.size()==2){
                System.out.println("You cannot hold more than 2 books at a time. Please return at least one of them to issue a new one");
            }

            else{
                System.out.println("Enter the Book ID you want to issue: ");
                while (!input.hasNextInt()){
                    System.out.println("Please enter valid input!");
                    input.next();
                }
                int bookID = input.nextInt();

                Book toIssue = Lib.issue_book(bookID);
                if (toIssue!=null){
                    this.BookIssued.add(toIssue);
                    System.out.println("Book ID: "+bookID+" has been issued!");
                }
                else{
                    System.out.println("No such book with the given ID exists currently!");
                }
            }
        }
    }
    public void return_book(){
        if (!this.BookIssued.isEmpty()){
            System.out.println("Enter the book ID you want to return: ");
            while (!input.hasNextInt()){
                System.out.println("Please enter valid input!");
                input.next();
            }
            int ID = input.nextInt();

            Book ToReturn = Lib.return_book(this,ID);
            if (ToReturn!=null){
                this.update_fine();

                if (this.Fine>0){
                    System.out.println("You have an pending fine of "+this.Fine);
                }
                this.BookIssued.remove(ToReturn);
                System.out.println("The book has been returned!");
            }
            else{
                System.out.println("You haven't issued this book");
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
                    this.Fine=this.Fine+(days-10)*3;
                }
            }
        }
    }
    public void pay_fine(){
        this.Fine=0;
        System.out.println("Your fines have been cleared!");
    }
    public Student login(){
        System.out.println("Please enter your student ID: ");
        String ID = input.nextLine();
        while (ID.length()!=10 || !isNumeric(ID)){
            System.out.println("Invalid phone number! Please try again");
            ID = input.nextLine();
        }

        Student student = Lib.getStudent(ID);
        if (student!=null){
            System.out.println("You have been logged in!");
        }
        else{
            System.out.println("Not a valid ID! Please try again");
        }
        return student;
    }
    public void viewIssued(){
        if (!this.BookIssued.isEmpty()){
            for (Book i : this.BookIssued){
                System.out.println("Title: "+i.getTitle());
                System.out.println("Author: "+i.getAuthor());
                System.out.println("Book ID: "+i.getID());
            }
        }
        else{
            System.out.println("You don't have any book issued!");
        }
    }
    public static boolean isNumeric(String str) {
        return str.matches("\\d+");
    }
}