package jFrame.itils.repositories;

import jFrame.print.PrintText;

import javax.sql.DataSource;
import java.sql.*;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    private final DataSource dataSource;
    private final String SQL_CHECK_PASSWORD = "SELECT Пароль FROM library.treaty WHERE Номер_сотрудника = ?";
    private final String SQL_SELECT_DELIVERY = "SELECT * FROM library.delivery";
    private final String SQL_INSERT_DELIVERY = "INSERT INTO library.delivery (Чит_билет, Код_книги, Дата_выдачи, Дата_возврата) VALUES (";
    private final String SQL_CHECK_NUMBER_BOOKS = "SELECT Количество_книг FROM library.books WHERE Номер_книги = ";
    private final String SQL_SEARCH_FOR_AUTHOR = "SELECT * library.books WHERE Автор = ";
    private final String SQL_SEARCH_FOR_NUMBER = "SELECT * FROM library.books WHERE Номер_книги = ";
    private final String SQL_ADD_BOOKS = "INSERT INTO library.books (Название_книги, Количество_книг, Автор, Год_издания) VALUES ";
    private final String SQL_ADD_EMPLOYEE = "INSERT INTO library.treaty (Номер_сотрудника, Пароль, Должность, Номер_телефона, Фамилия, Имя, Отчество, ИНН, СНИЛС, Дата_рождения, Дата_начала_работы) VALUES (";
    private final String SQL_DELETE_TICKET = "DELETE FROM library.ticket WHERE Читательский_билет = ";
    private final String SQL_SAVE_TICKET = "INSERT INTO library.ticket (Фамилия, Имя, Отчество, Адрес, Номер_телефона) VALUES (";

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public boolean checkUser(String idEmployee, String password) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CHECK_PASSWORD)) {
            statement.setInt(1, Integer.parseInt(idEmployee));
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                resultSet.close();
                return true;
            }
            String passwordEmployee = resultSet.getString(1);
            if (passwordEmployee.equals(password))
                return false;

        } catch (SQLException e) {
            e.getMessage();
            return true;
        }
        return false;
    }

    @Override
    public void save(String libraryCard, String booksCode, String dateOfIssue, String dateOfDelivery) {
        connection(SQL_INSERT_DELIVERY + "' " + libraryCard + "', '" + booksCode + "', '" + dateOfIssue + "', '" + dateOfDelivery + "')");
    }

    @Override
    public int updateDelivery(String booksCode) {
        int numberOfBooks = 0;

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_CHECK_NUMBER_BOOKS + booksCode);

            if (!resultSet.next()) {
                return 0;
            }
            numberOfBooks = resultSet.getInt(1);
            if (numberOfBooks == 0) {
                PrintText printText = new PrintText("Данной книги нет в наличие");
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        numberOfBooks -= 1;
        connection("UPDATE library.books SET Количество_книг = " + numberOfBooks + " WHERE Номер_книги = " + booksCode);
        return 0;
    }

    @Override
    public String searchForAuthor(String nameAuthor) {
        String message = connectionSearch(SQL_SEARCH_FOR_AUTHOR + nameAuthor);
        return message;
    }

    @Override
    public String searchForNumber(String number) {
        String message = connectionSearch(SQL_SEARCH_FOR_NUMBER + number);
        return message;
    }

    @Override
    public void addBooks(String nameBook, String amount, String author, String theYearOfPublishing) {
        connection(SQL_ADD_BOOKS + "('" + nameBook + "', '" + amount + "', '" + author + "', '" + theYearOfPublishing + "')");
    }

    @Override
    public void addEmployee(String surname, String name, String middleName, String INN, String SNILS, String dateOfBirth,
                            String startDate, String password, String post, String phoneNumber, int index) {

        String sqlAddEmployee = SQL_ADD_EMPLOYEE + "'" + index + "', '" + password + "', '" + post + "', '"
                + phoneNumber + "', '" + surname + "', '" + name + "', '" + middleName + "', '" + INN + "', '"
                + SNILS + "', '" + dateOfBirth + "', '" + startDate + "')";

        connection(sqlAddEmployee);
    }

    @Override
    public void returnBook(String libraryCard, String dateReturnBook, String numberBook) {
        int numberOfBooks = 0;
        int issueСode;

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_CHECK_NUMBER_BOOKS + numberBook);

            if (!resultSet.next()) {
                return;
            }
            numberOfBooks = resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connection("UPDATE library.books SET Количество_книг = " + ++numberOfBooks + " WHERE Номер_книги = " + numberBook);
        issueСode = checkIssueCode(libraryCard, numberBook);
        if (issueСode == -1) {
            PrintText printText = new PrintText("Книга не выдавалась!");
            return;
        }
        connection("DELETE FROM library.delivery WHERE Код_выдачи = " + issueСode);
    }

    private int checkIssueCode(String libraryCard, String numberBook) {
        String sql = "SELECT * FROM library.delivery WHERE Чит_билет = " + libraryCard;

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                if (resultSet.getString(2).equals(libraryCard) && resultSet.getString(3).equals(numberBook)) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void delete(String ticket) {
        connection(SQL_DELETE_TICKET + ticket);
    }

    @Override
    public void addTicket(String surname, String name, String middleName, String phoneNumber, String address) {
        connection(SQL_SAVE_TICKET + "'" + surname + "', '" + name + "', '"
                + middleName + "', '" + address + "', '" + phoneNumber + "')");
    }

    @Override
    public String readingEmployeeData() {
        String message = "";
        int i = 1;
        String sqlReadingEmployeeData = "SELECT * FROM library.treaty";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlReadingEmployeeData);

            while (resultSet.next()) {
                message += "Номер договора: " + String.valueOf(resultSet.getInt(i++));
                message += "\nНомер сотрудника: " + String.valueOf(resultSet.getInt(i++));
                i++;
                message += "\nДолжность: " + resultSet.getString(i++);
                message += "\nНомер телефона: " + resultSet.getString(i++);
                message += "\nФамилия: " + resultSet.getString(i++);
                message += "\nИмя: " + resultSet.getString(i++);
                message += "\nОтчество: " + resultSet.getString(i++);
                message += "\nИНН: " + resultSet.getInt(i++);
                message += "\nСНИЛС: " + resultSet.getString(i++);
                message += "\nДата рождения: " + resultSet.getString(i++);
                message += "\nДата начала работы: " + resultSet.getString(i++);
                message += "\n----------------------------------------------------------\n";
                i = 1;
            }
            return message;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String listForBooks() {
        String message = "";
        String sqlReadingEmployeeData = "SELECT * FROM library.books";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlReadingEmployeeData);

            while (resultSet.next()) {
                message += "Номер книги: " + String.valueOf(resultSet.getInt(1));
                message += "\nНазвание книги: " + resultSet.getString(2);
                message += "\nКоличество книг: " + String.valueOf(resultSet.getInt(3));
                message += "\nАвтор: " + resultSet.getString(4);
                message += "\nГод издания: " + resultSet.getString(5);
                message += "\n----------------------------------------------------------\n";
            }
            return message;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public String booksThatWereNotReturned() {
        String message = "";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_DELIVERY);

            while (resultSet.next()) {
                message += "Код выдачи: " + resultSet.getInt(1);
                message += "\nЧитательский билет: " + resultSet.getString(2);
                message += "\nКод книги: " + resultSet.getString(3);
                message += "\nДата выдачи: " + resultSet.getString(4);
                message += "\nДата возврата: " + resultSet.getString(5);
                message += "\n----------------------------------------------------------\n";
            }
            return message;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean checkAddTicket(String phoneNumber) {
        String phone;
        String sql = "SELECT * FROM library.ticket WHERE Номер_телефона = " + phoneNumber;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            resultSet.next();
            phone = resultSet.getString(6);
            if (phone.equals(phoneNumber)) {
                return true;
            }
        } catch (SQLException ignored) {
        }
        return false;
    }

    @Override
    public String listOfBooksThatReadersHaveNotReturned(String text) {
        String message = "";
        String sql = "SELECT * FROM library.delivery WHERE Чит_билет = " + text;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                message += "Код выдачи: " + resultSet.getInt(1);
                message += "\nЧитательский билет: " + resultSet.getString(2);
                message += "\nКод Книги: " + resultSet.getString(3);
                message += "\nДата выдачи: " + resultSet.getString(4);
                message += "\nДата возврата: " + resultSet.getString(5);
                message += "\n----------------------------------------------------------\n";
            }
            return message;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String listForTicket() {
        String message = "";
        String sqlReadingEmployeeData = "SELECT * FROM library.ticket";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlReadingEmployeeData);

            while (resultSet.next()) {
                message += "Читательский билет: " + String.valueOf(resultSet.getInt(1));
                message += "\nФамилия: " + resultSet.getString(2);
                message += "\nИмя: " + resultSet.getString(3);
                message += "\nОтчество: " + resultSet.getString(4);
                message += "\nАдрес: " + resultSet.getString(5);
                message += "\nНомер телефона: " + resultSet.getString(6);
                message += "\n----------------------------------------------------------\n";
            }
            return message;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean hasTheBookBeenIssued(String libraryCard, String booksCode) {
        String libCard, code;
        String sql = "SELECT * FROM library.delivery WHERE Чит_билет = " + libraryCard;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            resultSet.next();
            libCard = resultSet.getString(2);
            code = resultSet.getString(3);
            if (libCard.equals(libraryCard) && booksCode.equals(code)) {
                return true;
            }
        } catch (SQLException ignored) {
        }
        return false;
    }

    public void connection(String str) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(str);

            resultSet.next();
        } catch (SQLException e) {
            String ea = String.valueOf(e);
            if (!ea.matches("\\w+:Запрос не вернул результатов"))
                return;
            String MESSAGE = "Введен неправильный данные!!!";
            PrintText printText = new PrintText(MESSAGE);
        }
    }

    public String connectionSearch(String str) {
        String message = "";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(str);

            resultSet.next();
            message += "Номер книги: " + String.valueOf(resultSet.getInt(1));
            message += "\nНазвание книги: " + resultSet.getString(2);
            message += "\nКоличество книг: " + String.valueOf(resultSet.getInt(3));
            message += "\nАвтор: " + resultSet.getString(4);
            message += "\nГод издания: " + resultSet.getString(5);
            message += "\n----------------------------------------------------------\n";
            return message;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
