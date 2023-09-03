import java.util.Objects;
import java.util.Scanner;
public class Main {
    static Scanner input = new Scanner(System.in);
    static Library Lib = new Library();
    static Librarian Librarian = new Librarian(Lib);
    static Student Member = new Student("Dummy",18,"0000000000",Lib);
    private static void printMainMenu(){
        System.out.println("1.\tEnter as a librarian");
        System.out.println("2.\tEnter as a member");
        System.out.println("3.\tExit");
    }
    private static void printLibrarianMenu(){
        System.out.println("1.Register a member");
        System.out.println("2.Remove a member");
        System.out.println("3.Add a book");
        System.out.println("4.Remove a book");
        System.out.println("5.View all members");
        System.out.println("6.View all books");
        System.out.println("7.Back");
    }
    private static void printMemberMenu(){
        System.out.println("1.List Available Books");
        System.out.println("2.List My Books");
        System.out.println("3.Issue book");
        System.out.println("4.Return book");
        System.out.println("5.Pay Fine");
        System.out.println("6.Back");
    }

    public static void main(String[] args){
        System.out.println("Welcome to our Library Automation System!");
        String option;
        String SubOption;
        do{
            printMainMenu();
            option = input.nextLine();

            if (Objects.equals(option, "1")){
                do{
                    printLibrarianMenu();
                    SubOption = input.nextLine();

                    if (Objects.equals(SubOption, "1")){
                        Librarian.add_student();
                    }
                    else if (Objects.equals(SubOption, "2")){
                        Librarian.remove_student();
                    }
                    else if (Objects.equals(SubOption, "3")){
                        Librarian.add_book();
                    }
                    else if (Objects.equals(SubOption, "4")){
                        Librarian.remove_book();
                    }
                    else if (Objects.equals(SubOption, "5")){
                        Librarian.view_students();
                    }
                    else if (Objects.equals(SubOption, "6")){
                        Librarian.viewAllBooks();
                    }
                    else if (!Objects.equals(SubOption, "7")){
                        System.out.println("Please enter a valid option!");
                    }
                }while(!Objects.equals(SubOption, "7"));
            }
            else if (Objects.equals(option, "2")){
                if (!Lib.checkStudent()){
                    System.out.println("No members in library!");
                    continue;
                }
                while (Objects.equals(Member.getID(),"0000000000")) {
                    Member = Member.login();
                    if (Member == null)
                        Member = new Student("Dummy", 18, "0000000000", Lib);
                }

                do{
                    printMemberMenu();
                    SubOption = input.nextLine();

                    if (Objects.equals(SubOption, "1")){
                        Member.view_book();
                    }
                    else if (Objects.equals(SubOption, "2")){
                        Member.viewIssued();
                    }
                    else if (Objects.equals(SubOption, "3")){
                        Member.issue_book();
                    }
                    else if (Objects.equals(SubOption, "4")){
                        Member.return_book();
                    }
                    else if (Objects.equals(SubOption, "5")){
                        Member.pay_fine();
                    }
                    else if (!Objects.equals(SubOption,"6")){
                        System.out.println("Please enter a valid option!");
                    }
                }while(!Objects.equals(SubOption, "6"));

                Member = new Student("Dummy", -1, "0000000000", Lib);
            }
        }while(!Objects.equals(option, "3"));
        System.out.println();
        System.out.println("Deleting Library...");
    }
}
