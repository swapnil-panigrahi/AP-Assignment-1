import java.util.Objects;
import java.util.Vector;

public class Library {
    private Vector <Student> StudentList;
    private Student student;
    private Vector <Book> BookList;
    private Book new_book;

    public Library(){
        this.StudentList = new Vector<>();
        this.student = null;
        this.BookList = new Vector<>();
        this.new_book = null;
    }

    public void add_book(int bookID, String title, String author){
        int copies = 1;
        boolean success = true;
        for (Book i : this.BookList){
            if (i.getID()==bookID){
                System.out.println("Book with the given ID already exists!");
                success = false;
                break;
            }
            if (Objects.equals(i.getAuthor(), author) && Objects.equals(i.getTitle(), title)){
                i.increaseCopies();
                copies++;
            }
        }

        new_book = new Book(bookID, title, author, copies);
        if (success) BookList.add(new_book);
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
        boolean success = false;
        Book temp = null;
        for(Book i : this.BookList){
            if (i.getID()==bookID){
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
    public void add_student(String name, int age, String phone){
        student = new Student(name,age,phone,this);
        for (Student i : StudentList){
            if (Objects.equals(i.getID(), phone)){
                System.out.println("Student with the given ID already exists!");
            }
        }
        StudentList.add(student);
    }
    public void remove_student(String phone){
        boolean success = false;
        Student temp = null;
        for (Student i : this.StudentList){
            if (Objects.equals(i.getID(), phone)){
                temp = i;
                success = true;
            }
        }

        if (success) {
            System.out.println("Removed Student with ID: " + phone);
            StudentList.remove(temp);
        }
        else{
            System.out.println("No Student registered with ID: " + phone);
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