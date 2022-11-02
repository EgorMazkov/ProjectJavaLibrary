package egor.jFrame.utils.repositories;

public interface MessagesRepository {

    boolean checkUser(String idEmployee, String password);

    void save(String libraryCard, String booksCode, String dateOfIssue,
              String dateOfDelivery);

    int updateDelivery(String booksCode);

    void addBooks(String nameBook, String amount, String author, String theYearOfPublishing);

    void addEmployee(String surname, String name, String middleName, String INN,
                     String SNILS, String dateOfBirth, String startDate,
                     String password, String post, String phoneNumber, int index);

    void returnBook(String libraryCard, String dateReturnBook, String numberBook);

    void delete(String ticket);

    void addTicket(String surname, String name, String middleName, String phoneNumber, String address, int sub);

    String[][] listForBooks();

    String listForTicket(int check);

    boolean hasTheBookBeenIssued(String libraryCard, String booksCode);

    boolean checkAddTicket(String text);

    boolean updateRenewalTicket(String text, int renewalNumber, int number);

    String searchTicketForSurname(String text);

    void closeSubscription(String text, String textOne);

    String[][] listForDelivery();

    String[][] listForTicketTable();

    int checkingHowManyBooksWereTaken(String s);

    String[][] listOfBooksTicket(String s);

    String[][] thereIsASubcription();

    String[][] noSubcription();

    String[][] listForEmployee();
}
