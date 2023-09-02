import java.util.Scanner;

public class Librarian {
    Scanner input = new Scanner(System.in);
    private final Library Lib;

    public Librarian(Library Lib){
        this.Lib = Lib;
    }
    public void add_student(){
        System.out.print("Enter your name: ");
        String name = input.nextLine();
        System.out.print("Enter your age: ");
        int age = input.nextInt();
        input.nextLine();
        System.out.print("Enter your phone number: ");
        String phone = input.nextLine();

        while (phone.length()!=10 || isNumeric(phone)){
            System.out.println("Invalid phone number! Please try again");
            phone = input.nextLine();
        }
        this.Lib.add_student(name,age,phone);
        System.out.println("You have been registered! Your phone number will act as your unique ID");
    }
    public void remove_student(){
        System.out.print("Enter the ID of the student to be removed: ");
        String phone = input.nextLine();

        while (phone.length()!=10 || isNumeric(phone)){
            System.out.println("Invalid Student ID! Please try again");
            phone = input.nextLine();
        }
        this.Lib.remove_student(phone);
    }
    public void add_book(){
        System.out.print("Enter Book ID: ");
        int bookID;
        while (!input.hasNextInt()){
            input.next();
            System.out.println("Book ID should only be an integer!");
        }
        bookID = input.nextInt();
        input.nextLine();

        System.out.print("Title: ");
        String title = input.nextLine();
        System.out.print("Author: ");
        String author = input.nextLine();

        this.Lib.add_book(bookID,title,author);
    }

    public void remove_book(){
        System.out.print("Enter Book ID: ");
        int bookID = input.nextInt();
        input.nextLine();

        this.Lib.remove_book(bookID);
    }

    public void view_students(){
        this.Lib.listAllStudents();
    }
    public void view_book(){
        Lib.listAllBooks();
    }

    public static boolean isNumeric(String str) {
        return !str.matches("\\d+");
    }
}