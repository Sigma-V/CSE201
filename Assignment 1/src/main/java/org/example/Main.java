package org.example;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Optional;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.time.Instant;
import java.time.Duration;

class Library{
    protected static HashMap<Integer,Library> books = new HashMap<>();
    protected static HashMap<Integer,Library> availbooks = new HashMap<>();
    protected String title;
    protected String author;
    static Integer BookID = 1;
    public void addbook(String title, String author, Integer number) {
        this.title = title;
        this.author = author;
        for (int i = 0; i < number; i++) {
            Integer emptySlot = findEmptySlot();
            if (emptySlot != null) {
                books.put(emptySlot, this);
                availbooks.put(emptySlot, this);
            } else {
                books.put(BookID, this);
                availbooks.put(BookID, this);
                BookID++;
            }
        }
        System.out.println("Books has been successfully added to the library.");
    }
    public void removeBook(int bookID) {
        if (books.containsKey(bookID)) {
            if (checkfn2(bookID)) {
                books.remove(bookID);
                availbooks.remove(bookID);
                System.out.println("Book with ID " + bookID + " has been removed from the library.");
            } else {
                System.out.println("Cannot remove the book. It is currently issued to a member.");
            }
        } else {
            System.out.println("Book with ID " + bookID + " not found in the library.");
        }
    }
    private boolean checkfn2(int bookID) {
        return Library.availbooks.containsKey(bookID);
    }
    private Integer findEmptySlot() {
        for (int i = 1; i < BookID; i++) {
            if (!books.containsKey(i)) {
                return i;
            }
        }
        return null;
    }
public void showbook(){
        books.forEach((key,value) -> {
            System.out.print("Book ID" + key+"  ");
            System.out.print("Title" + value.title +"  ");
            System.out.print("Author" + value.author+"  ");
            System.out.println();
        });
    }
    public void showavailbook(){
        availbooks.forEach((key,value) -> {
            System.out.print("Book ID" + key+"  ");
            System.out.print("Title " + value.title +"  ");
            System.out.print("Author " + value.author+"  ");
            System.out.println();
        });
    }
}
class Member{
    protected static HashMap<Integer,Member> members = new HashMap<>();
    ArrayList<Integer> myList = new ArrayList<>();
    protected String name;
    protected String phone;
    protected int fine;
    static Integer MemberID = 1;
    protected Instant start;
    protected Instant end;
    public void addmember(String name, String phone){
        Member newMember = new Member();
        newMember.name = name;
        newMember.phone = phone;
        newMember.fine = 0;

        members.put(MemberID, newMember);
        MemberID++;
        System.out.println("Member has been successfully added to the system.");
    }
    public void removemem1(int memberID) {
        if (members.containsKey(memberID)) {
            Member meme = members.get(memberID);
            if (meme.myList.isEmpty() && meme.fine == 0) {
                members.remove(memberID);
                System.out.println("Member with ID " + memberID + " has been successfully removed.");
            } else if(!meme.myList.isEmpty()) {
                System.out.println("Cannot remove the member. They have issued books.Please return the books before removing");
            }else{
                System.out.println("Cannot remove the member. They have pending fine.Please pay the fine before removing ");
            }
        } else {
            System.out.println("Member with ID " + memberID + " not found in the system.");
        }
    }
    public void removemem2(String name, String phone) {
        ArrayList<Integer> keysToRemove = new ArrayList<>();
        members.forEach((key, value) -> {
            if (name.equals(value.name) && phone.equals(value.phone)) {
                if (value.myList.isEmpty() && value.fine == 0) {
                    keysToRemove.add(key);
                    System.out.println("Member has been successfully removed.");
                } else if(!value.myList.isEmpty()) {
                    System.out.println("Cannot remove the member. They have issued books.Please return the books before removing");
                }else{
                    System.out.println("Cannot remove the member. They have pending fine.Please pay the fine before removing ");
                }
            }
        });
        keysToRemove.forEach(members::remove);
        if (keysToRemove.isEmpty()) {
            System.out.println("Member not found in the system.");
        }
    }
    public static Member check(String name, String phone_number) {
        Optional<Member> memberOptional = members.values().stream()
                .filter(member -> name.equals(member.name) && phone_number.equals(member.phone))
                .findFirst();
        return memberOptional.orElse(null);
    }

    public void issueBook(int bookID) {
        if (fine > 0) {
            System.out.println("You have a pending fine of Rs" + fine + ". Please clear your fine before borrowing a new book.");
        } else if (myList.size() >= 2) {
            System.out.println("You have already borrowed 2 books. You cannot borrow more until you return some.");
        } else {
            if (Library.availbooks.containsKey(bookID)) {
                if (checkfn1()) {
                    myList.add(bookID);
                    Library.availbooks.remove(bookID);
                    start = Instant.now();
                    System.out.println("Selected book has been successfully issued.");
                } else {
                    System.out.println("You cannot issue another book as you have a fine of Rs "+ fine);
                    fine = 0;
                }
            } else {
                System.out.println("Selected book is not available for issuing right now. Come and Check Later.");
            }
        }
    }
    private boolean checkfn1() {
        if (myList.isEmpty()) {
            return true;
        }
        Instant now = Instant.now();
        Duration time = Duration.between(start,now);
        long timereq = time.getSeconds();
        if(timereq >10){
            fine+= (int) ((timereq-10)*3);
            return false;
        }
        else{
            return true;
        }
    }
    public void returnBook(int bookID) {
        if (myList.contains(bookID)) {
            myList.remove(Integer.valueOf(bookID));
            Library.availbooks.put(bookID, Library.books.get(bookID));
            end=Instant.now();
            Duration time = Duration.between(start, end);
            long timereq = time.getSeconds();

            if(timereq > 10){
                fine+= (int) ((timereq-10)*3);
            }
            System.out.println("Your selected book has been successfully returned.");
        } else {
            System.out.println("You did not issue the selected book. Check again and Retry.");
        }
    }

    public void memberbooks() {
        System.out.println("Books Borrowed by " + name + ":");
        System.out.println("--------------------------------");
        for (Integer bookID : myList) {
            if (Library.books.containsKey(bookID)) {
                Library book = Library.books.get(bookID);
                System.out.println("Book ID: " + bookID);
                System.out.println("Title: " + book.title);
                System.out.println("Author: " + book.author);
            }
        }
        System.out.println("--------------------------------");
    }

    public void Memberslist() {
        members.forEach((key,value) -> {
            System.out.println("-------------------------");
            System.out.println("     Members Details     ");
            System.out.println("-------------------------");
            System.out.println("Name: " + value.name);
            System.out.println("Books Issued :");
            for (Integer bookID : value.myList) {
                if (Library.books.containsKey(bookID)) {
                    Library book = Library.books.get(bookID);
                    System.out.println("Book ID: " + bookID);
                    System.out.println("Title: " + book.title);
                    System.out.println("Author: " + book.author);
                }
            }
            Instant now = Instant.now();
            Duration time = Duration.between(start,now);
            long timereq = time.getSeconds();
            if(timereq >10) {
                fine += (int) ((timereq - 10) * 3);
            }
            System.out.println("Fine to be paid: Rs " + value.fine);
            System.out.println("-------------------------");
            fine = 0;
        });
    }

    public void payfine(){
        if(fine>0){
            System.out.println("You have a pending fine of Rs "+fine);
            fine = 0;
            System.out.println("Fine paid successfully. Now you can issue your books");
        }
        else{
            System.out.println("You have already paid all the past fines. No more amount needed to be paid");
        }

    }
}

public class Main {
    static void librarian() {
        while (true) {
            System.out.println("-------------------------");
            System.out.println("Welcome Mr/Mrs. Librarian ");
            System.out.println("-------------------------");
            System.out.println("1. Register a member");
            System.out.println("2. Remove a member");
            System.out.println("3. Add a book");
            System.out.println("4. Remove a book");
            System.out.println("5. View all members along with their books and fines to be paid");
            System.out.println("6. View all books");
            System.out.println("7. Back");
            System.out.println("-------------------------");
            Scanner scanner = new Scanner(System.in);
            int nam1 = scanner.nextInt();
            scanner.nextLine();
            try{
                if (nam1 == 7) {
                    System.out.println("-------------------------");
                    System.out.println("Welcome to library Portal");
                    System.out.println("-------------------------");
                    System.out.println("1. Enter as a librarian");
                    System.out.println("2. Enter as a member");
                    System.out.println("3. Exit");
                    System.out.println("-------------------------");
                    break;
                } else {
                    Library bookLibrary = new Library();
                    Member memberval = new Member();

                    if(nam1 == 1){
                        String name = "";
                        String phone = "";
                        System.out.println("Enter Member Name: ");
                        try{
                            name = scanner.nextLine();
                        }catch (InputMismatchException e) {
                            System.out.println("Invalid Input datatype entered. Enter a valid string");
                            scanner.nextLine();
                        }
                        System.out.println("Enter Member's Phone No.: ");
                        try{
                            phone = scanner.nextLine();
                        }catch (InputMismatchException e) {
                            System.out.println("Invalid Input datatype entered. Enter a valid string");
                            scanner.nextLine();
                        }
                        memberval.addmember(name,phone);
                    }

                    else if(nam1 == 2){
                        int number = 0;
                        System.out.println("1.Remove Member by MemberID");
                        System.out.println("2.Remove Member by Phone No. and Username");
                        try{
                            number = scanner.nextInt();
                            scanner.nextLine();
                        }catch (InputMismatchException e) {
                            System.out.println("Invalid Input datatype entered. Enter a valid integer");
                            scanner.nextLine();
                        }
                        if(number == 1){
                            int id1 = 0;
                            System.out.println("Enter MemberID: ");
                            try{
                                id1 = scanner.nextInt();
                                scanner.nextLine();
                            }catch (InputMismatchException e) {
                                System.out.println("Invalid Input datatype entered. Enter a valid integer");
                                scanner.nextLine();
                            }
                            memberval.removemem1(id1);
                        }
                        else if(number == 2){
                            String name = ""; String phone = "";
                            System.out.println("Enter Member Name: ");
                            try{
                                name = scanner.nextLine();
                            }catch (InputMismatchException e) {
                                System.out.println("Invalid Input datatype entered. Enter a valid string");
                                scanner.nextLine();
                            }
                            System.out.println("Enter Member's Phone No.: ");
                            try{
                                phone = scanner.nextLine();
                            }catch (InputMismatchException e) {
                                System.out.println("Invalid Input datatype entered. Enter a valid string");
                                scanner.nextLine();
                            }
                            memberval.removemem2(name,phone);
                        }
                    }
                    if(nam1 == 3){
                        String title = ""; String author = ""; int numCopies = 0;
                        System.out.println("Enter book title: ");
                        try{
                            title = scanner.nextLine();
                        }catch (InputMismatchException e) {
                            System.out.println("Invalid Input datatype entered. Enter a valid string");
                            scanner.nextLine();
                        }
                        System.out.println("Enter book author: ");
                        try{
                            author = scanner.nextLine();
                        }catch (InputMismatchException e) {
                            System.out.println("Invalid Input datatype entered. Enter a valid string");
                            scanner.nextLine();
                        }
                        System.out.println("Enter number of copies: ");
                        try{
                            numCopies = scanner.nextInt();
                            scanner.nextLine();
                        }catch (InputMismatchException e) {
                            System.out.println("Invalid Input datatype entered. Enter a valid integer");
                            scanner.nextLine();
                        }
                        bookLibrary.addbook(title, author, numCopies);
                    }
                    else if(nam1 == 4){
                        int id =0;
                        System.out.println("Enter BookID of the book to be removed: ");
                        try{
                            id = scanner.nextInt();
                            scanner.nextLine();
                        }catch (InputMismatchException e) {
                            System.out.println("Invalid Input datatype entered. Enter a valid integer");
                            scanner.nextLine();
                        }
                        bookLibrary.removeBook(id);
                    } else if(nam1 == 5){
                        System.out.println("----------------------------------");
                        System.out.println("List of all registered member is:");
                        System.out.println("----------------------------------");
                        memberval.Memberslist();
                    } else if(nam1 == 6){
                        bookLibrary.showbook();
                    }
                }
            }catch (InputMismatchException e) {
                System.out.println("Invalid Input datatype entered. Enter a valid integer");
                scanner.nextLine();
            }
        }
    }
    static void member() {
        Scanner scanner = new Scanner(System.in);
        Library bookLibrary1 = new Library();
        System.out.println("-------------------------");
        System.out.println("Welcome Mr/Mrs. Member");
        System.out.println("-------------------------");

        String name = ""; String phone = ""; Member memberval1 = null;
        while (true) {
            if (name.isEmpty() || phone.isEmpty()) {
                System.out.println("Enter Name:");
                try{
                    name = scanner.nextLine();
                }catch (InputMismatchException e) {
                    System.out.println("Invalid Input datatype entered. Enter a valid string");
                    scanner.nextLine();
                }
                System.out.println("Enter Phone No.:");
                try{
                    phone = scanner.nextLine();
                }catch (InputMismatchException e) {
                    System.out.println("Invalid Input datatype entered. Enter a valid string");
                    scanner.nextLine();
                }
            }if (memberval1 == null) {
                memberval1 = Member.check(name, phone);
            }if (memberval1 == null) {
                System.out.println("No such person found in the directory. Please enter valid credentials");
                name = "";
                phone = "";
                continue;
            }

            System.out.println("1. List Available Books");
            System.out.println("2. List My Books");
            System.out.println("3. Issue book");
            System.out.println("4. Return book");
            System.out.println("5. Pay Fine");
            System.out.println("6. Back");
            System.out.println("-------------------------");
            try{
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 6) {
                    System.out.println("-------------------------");
                    System.out.println("Welcome to library Portal");
                    System.out.println("-------------------------");
                    System.out.println("1. Enter as a librarian");
                    System.out.println("2. Enter as a member");
                    System.out.println("3. Exit");
                    System.out.println("-------------------------");
                    break;
                } else {
                    if (choice == 1) {
                        bookLibrary1.showavailbook();
                    } else if (choice == 2) {
                        memberval1.memberbooks();
                    } else if (choice == 3) {
                        bookLibrary1.showavailbook();
                        int id2 = 0;
                        System.out.println("Enter BookID you want to issue: ");
                        try{
                            id2 = scanner.nextInt();
                            scanner.nextLine();
                        }catch (InputMismatchException e){
                            System.out.println("Invalid Input datatype entered. Please enter a valid integer.");
                        }
                        memberval1.issueBook(id2);
                    } else if (choice == 4) {
                        int id2 = 0;
                        System.out.println("Enter BookID you want to return: ");
                        try{
                            id2 = scanner.nextInt();
                            scanner.nextLine();
                        }catch (InputMismatchException e){
                            System.out.println("Invalid Input datatype entered. Please enter a valid integer.");
                        }
                        memberval1.returnBook(id2);
                    } else if (choice == 5) {
                        memberval1.payfine();
                    } else{
                        System.out.println("Please enter a valid option from the given choices");
                    }
                }
            }catch (InputMismatchException e){
               System.out.println("Invalid Input datatype entered. Please enter a valid integer.");
            }
        }
    }
 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------------");
        System.out.println("Welcome to library Portal");
        System.out.println("-------------------------");
        System.out.println("1. Enter as a librarian");
        System.out.println("2. Enter as a member");
        System.out.println("3. Exit");
        System.out.println("-------------------------");
        while(true){
            try{
                int name = scanner.nextInt();
                if (name == 1){
                    librarian();
                } else if(name == 2){
                    member();
                } else if(name == 3){
                    System.out.println("-------------------------");
                    System.out.println("Thanks for Visiting");
                    System.out.println("-------------------------");
                    break;
                } else{
                    System.out.println("Please enter a valid option from the given choices");
                }
            }catch (InputMismatchException e) {
                System.out.println("Invalid Input datatype entered. Enter a valid integer");
                scanner.nextLine();
            }
        }
    }
}