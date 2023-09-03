import java.util.Objects;
import java.util.Vector;

public class Library {
    private final Vector <Student> StudentList;
    private Student student;
    private final Vector <Book> BookList;
    private Book new_book;

    public Library(){
        this.StudentList = new Vector<>();
        this.student = null;
        this.BookList = new Vector<>();
        this.new_book = null;
    }

    public void add_book(String title, String author){
        int bookID=BookList.size();
        int copies = 1;
        for (Book i : this.BookList){
            if (Objects.equals(i.getAuthor(), author) && Objects.equals(i.getTitle(), title)){
                i.increaseCopies();
                copies++;
            }
        }

        new_book = new Book(bookID, title, author, copies);
        BookList.add(new_book);
    }
    public Book issue_book(int ID){
        for(Book i : this.BookList){
           if (i.getID()==ID && !i.isIssued()) {
               i.issueBook();
               return i;
           }
        }
        return null;
    }
    public Book return_book(Student student, int bookID){
        for (Book i : student.getIssued()){
            if (i.getID()==bookID){
                System.out.println("The book with ID: "+bookID+" has been returned.");
                i.returnBook();
                return i;
            }
        }
        return null;
    }
    public void remove_book(int bookID){
        if (this.BookList.isEmpty()){
            System.out.println("There are no books in the library!");
            return;
        }
        boolean success = false;
        Book temp = null;
        for(Book i : this.BookList){
            if (i.getID()==bookID && !i.isIssued()){
                temp = i;
                success = true;
            }
        }
        if (success){
            System.out.println("Removed Book with ID: "+bookID);
            BookList.remove(temp);
        }
        else{
            System.out.println("No Book found with ID: "+bookID);
        }
    }
    public boolean add_student(String name, int age, String phone){
        student = new Student(name,age,phone,this);
        for (Student i : this.StudentList){
            if (Objects.equals(i.getID(), phone)){
                System.out.println("Student with the given ID already exists!");
                return false;
            }
        }
        StudentList.add(student);
        return true;
    }
    public void remove_student(String phone){
        if (this.StudentList.isEmpty()){
            System.out.println("There are no members in the library!");
            return;
        }
        boolean success = false;
        Student temp = new Student("Dummy", -1, "0000000000", this);

        for (Student i : this.StudentList){
            temp = i;
            if (Objects.equals(i.getID(), phone) && i.getIssued().isEmpty()){
                success = true;
                break;
            }
        }

        if (success) {
            System.out.println("Removed Student with ID: " + phone);
            StudentList.remove(temp);
        }
        else if (!temp.getIssued().isEmpty()){
            System.out.println("Member has a book issued!");
        }
        else{
            System.out.println("No member with registered ID: "+phone);
        }
    }
    public void viewAllBooks(){
        if (!this.BookList.isEmpty()){
            for (Book i : this.BookList) {
                System.out.println("ID: "+i.getID());
                System.out.println("Title: "+i.getTitle());
                System.out.println("Author: "+i.getAuthor());
                System.out.println("Copies: "+i.getCopies());
                System.out.println();
            }
        }
        else{
            System.out.println("No books available!");
        }
    }
    public void listAllBooks(){
        if (!this.BookList.isEmpty()){
            for (Book i : this.BookList) {
                if (!i.isIssued()) {
                    System.out.println("ID: "+i.getID());
                    System.out.println("Title: "+i.getTitle());
                    System.out.println("Author: "+i.getAuthor());
                    System.out.println("Copies: "+i.getCopies());
                    System.out.println();
                }
            }
        }
        else{
            System.out.println("No books available!");
        }
    }
    public void listAllStudents(){
        if (!this.StudentList.isEmpty()){
            for (Student i : this.StudentList){
                System.out.println("Name: "+i.getName());
                System.out.println("Age: "+i.getAge());
                System.out.println("ID: "+i.getID());

                Vector <Book> Books = i.getIssued();
                if (!Books.isEmpty()){
                    for (Book j : Books){
                        System.out.println("Book ID: "+j.getID());
                        System.out.println("Title: "+j.getTitle());
                    }
                }
                System.out.println(i.getName()+" has pending fine of "+i.getFine());
                System.out.println();
            }
        }
        else{
            System.out.println("No registered members!");
        }
    }
    public Student getStudent(String ID){
        for (Student i : StudentList){
            if (Objects.equals(i.getID(), ID)){
                return i;
            }
        }
        return null;
    }
}