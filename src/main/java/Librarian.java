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
        System.out.println("Enter your age: ");
        int age = input.nextInt();
        input.nextLine();

        System.out.println("Enter your phone number: ");
        String phone = input.nextLine();

        this.Lib.add_student(name,age,phone);
        System.out.println("You have been registered! Your phone number will act as your unique ID");
    }
    public void remove_student(){
        System.out.println("Enter the ID of the student to be removed: ");
        String phone = input.nextLine();
        this.Lib.remove_student(phone);
    }
    public void add_book(){
        int bookID = input.nextInt();
        input.nextLine();

        String title = input.nextLine();
        String author = input.nextLine();

        this.Lib.add_book(bookID,title,author);
    }

    public void remove_book(){
        int bookID = input.nextInt();
        input.nextLine();

        this.Lib.remove_book(bookID);
    }
    public void collect_fine(){
        String ID = input.nextLine();

        Student temp = this.Lib.getStudent(ID);
        if (temp!=null){
            temp.pay_fine();
        }
    }
    public void view_students(){
        this.Lib.listAllStudents();
    }
    public void view_book(){
        Lib.listAllBooks();
    }
}