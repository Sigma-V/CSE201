Library Management System

Overview:-

The Library Management System is a Java-based console application that allows librarians and members to manage library resources. The system keeps track of books, members, book issuance, and fines. It provides functionality for librarians to add and remove books, register and remove members, and view the list of all members along with their books and fines. Members can list available books, view their borrowed books, issue and return books, and pay fines if they have any.

Classes and Functionalities:-
    a.Library` Class
        This class represents the library's book management system.

        - `addbook(String title, String author, Integer number)`: Adds books to the library with the specified title, author, and the number of copies.
        - `removeBook(int bookID)`: Removes a book from the library by its unique book ID.
        - `showbook()`: Displays a list of all books in the library.
        - `showavailbook()`: Displays a list of available books in the library.

    b.Member` Class

        This class represents library members and their activities.

        - `addmember(String name, String phone)`: Registers a new library member with a name and phone number.
        - `removemem1(int memberID)`: Removes a member by their unique Member ID if they have no issued books or fines.
        - `removemem2(String name, String phone)`: Removes a member by their name and phone number if they have no issued books or fines.
        - `issueBook(int bookID)`: Allows a member to borrow a book if they have no fines and have not reached the maximum number of borrowed books (2 by default).
        - `returnBook(int bookID)`: Allows a member to return a borrowed book.
        - `memberbooks()`: Displays the books borrowed by a member.
        - `Memberslist()`: Displays a list of all registered members along with their books and fines.
        - `payfine()`: Allows a member to pay their fines.

    c.`Main` Class

        The main class of the application containing the entry point.

        - `librarian()`: Implements the librarian's functionalities like adding/removing books and members.
        - `member()`: Implements the member's functionalities like listing available books, borrowing, returning books, and paying fines.

Usage:-
To run the Library Management System, execute the `Main` class. You can choose to enter the system as a librarian or a member, then follow the prompts to perform various tasks.
