package jFrame.itils.repositories;

public interface MessagesRepository {

    boolean checkUser(String idEmployee, String password);
    void save(String libraryCard, String booksCode, String dateOfIssue,
                String dateOfDelivery);
    int updateDelivery(String booksCode);

    String searchForAuthor(String nameAuthor);
    String searchForNumber(String number);
    void addBooks(String nameBook, String amount, String author, String theYearOfPublishing);
    void addEmployee(String surname, String name, String middleName, String INN,
                     String SNILS, String dateOfBirth, String startDate,
                     String password, String post, String phoneNumber, int index);

    void returnBook(String libraryCard, String dateReturnBook, String numberBook);
    void delete(String ticket);
    void addTicket(String surname, String name, String middleName, String phoneNumber, String address);

    String readingEmployeeData();
    String listForBooks();
    String listForTicket();

    boolean hasTheBookBeenIssued(String libraryCard, String booksCode);
    String booksThatWereNotReturned();

    boolean checkAddTicket(String text);

    String listOfBooksThatReadersHaveNotReturned(String text);
}
