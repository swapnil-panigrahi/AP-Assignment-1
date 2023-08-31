import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    static Library Lib = new Library();
    static Librarian Librarian = new Librarian(Lib);

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
        int option;
        int SubOption;
        do{
            printMainMenu();
            option = input.nextInt();
            input.nextLine();

            if (option==1){
                do{
                    printLibrarianMenu();
                    SubOption = input.nextInt();
                    input.nextLine();

                    if (SubOption==1){
                        Librarian.add_student();
                    }
                    else if (SubOption==2){
                        Librarian.remove_student();
                    }
                    else if (SubOption==3){
                        Librarian.add_book();
                    }
                    else if (SubOption==4){
                        Librarian.remove_book();
                    }
                    else if (SubOption==5){
                        Librarian.view_students();
                    }
                    else if (SubOption==6){
                        Librarian.view_book();
                    }
                    else if (SubOption>7){
                        System.out.println("Please enter a valid option!");
                    }
                }while(SubOption!=7);
            }
            else if (option==2){
                printMemberMenu();
                do{
                    SubOption = input.nextInt();
                    input.nextLine();


                }while(SubOption!=6);
            }
            else{
                System.out.println("Please input a valid option!");
            }
            option = input.nextInt();
            input.nextLine();
        }while(option!=3);
    }
}
