package egor.jFrame.utils.repositories;

import egor.jFrame.print.PrintText;

import javax.sql.DataSource;
import java.sql.*;

import static egor.jFrame.library.LibraryJFrame.*;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    private final DataSource dataSource;
    private final String SQL_CHECK_PASSWORD = "SELECT * FROM library.treaty WHERE Номер_сотрудника = ?";
    private final String SQL_SELECT_TICKET = "SELECT * FROM library.ticket WHERE Читательский_билет = ";
    private final String SQL_SEARCH_TICKET_FOR_SURNAME = "SELECT * FROM library.ticket WHERE Фамилия = ";
    private final String SQL_SELECT_DELIVERY = "SELECT * FROM library.delivery";
    private final String SQL_CHECK_ONE_EMPLOYEE = "SELECT * FROM library.treaty";
    private final String SQL_INSERT_DELIVERY = "INSERT INTO library.delivery (Чит_билет, Код_книги, Дата_выдачи, Дата_возврата) VALUES (";
    private final String SQL_CHECK_NUMBER_BOOKS = "SELECT Количество_книг FROM library.books WHERE Номер_книги = ";
    private final String SQL_SEARCH_FOR_AUTHOR = "SELECT * library.books WHERE Автор = ";
    private final String SQL_SEARCH_FOR_NUMBER = "SELECT * FROM library.books WHERE Номер_книги = ";
    private final String SQL_ADD_BOOKS = "INSERT INTO library.books (Название_книги, Количество_книг, Автор, Год_издания) VALUES ";
    private final String SQL_ADD_EMPLOYEE = "INSERT INTO library.treaty (Пароль, Должность, Номер_телефона, Фамилия, Имя, Отчество, ИНН, СНИЛС, Дата_рождения, Дата_начала_работы) VALUES (";
    private final String SQL_DELETE_TICKET = "DELETE FROM library.ticket WHERE Читательский_билет = ";
    private final String SQL_SAVE_TICKET = "INSERT INTO library.ticket (Фамилия, Имя, Отчество, Адрес, Номер_телефона, Абонемент_на_количество_книг, Абонемент) VALUES (";

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
            String passwordEmployee = resultSet.getString(3);
            employeeOrDirectory = resultSet.getString(4);
            if (!passwordEmployee.equals(password))
                return false;

        } catch (SQLException e) {
            e.getMessage();
            return false;
        }
        return true;
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
    public void addBooks(String nameBook, String amount, String author, String theYearOfPublishing) {
        connection(SQL_ADD_BOOKS + "('" + nameBook + "', '" + amount + "', '" + author + "', '" + theYearOfPublishing + "')");
    }

    @Override
    public void addEmployee(String surname, String name, String middleName, String INN, String SNILS, String dateOfBirth,
                            String startDate, String password, String post, String phoneNumber, int index) {

        String sqlAddEmployee = SQL_ADD_EMPLOYEE + "'" + password + "', '" + post + "', '"
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
    public void addTicket(String surname, String name, String middleName, String phoneNumber, String address, int sub) {
        connection(SQL_SAVE_TICKET + "'" + surname + "', '" + name + "', '"
                + middleName + "', '" + address + "', '" + phoneNumber + "', '" + sub + "', 'Открытый')");
    }

    @Override
    public String[][] listForBooks() {
        String message = "";
        int i = 0;
        int j = 0;
        int k = 0;
        String sqlReadingEmployeeData = "SELECT * FROM library.books ORDER BY Номер_книги";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlReadingEmployeeData);

            while (resultSet.next())
                j++;
            ResultSet resultSet1 = statement.executeQuery(sqlReadingEmployeeData);
            String[][] arg = new String[j][j * 5];
            j = 1;
            while (resultSet1.next()) {
                arg[i][k++] = String.valueOf(resultSet1.getInt(j++));
                arg[i][k++] = resultSet1.getString(j++);
                arg[i][k++] = String.valueOf(resultSet1.getInt(j++));
                arg[i][k++] = resultSet1.getString(j++);
                arg[i][k] = resultSet1.getString(j);
                j = 1;
                i++;
                k = 0;
            }
            return arg;
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
                return false;
            }
        } catch (SQLException ignored) {
        }
        return true;
    }

    @Override
    public boolean updateRenewalTicket(String text, int renewalNumber, int number) {
        int numberRenewal = 0;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_TICKET + text);

            if (!resultSet.next()) {
                return false;
            }
            numberRenewal = resultSet.getInt(7);
            if (numberRenewal == 0) {
                connection("UPDATE library.ticket SET Абонемент = 'Закрыт' WHERE Читательский_билет = " + text);
                return false;
            }
            if (resultSet.getString(8).equals("Закрыт"))
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (number == 1) {
            connection("UPDATE library.ticket SET Абонемент_на_количество_книг = " + (numberRenewal + renewalNumber) + " WHERE Читательский_билет = " + text);
        } else {
            numberRenewal -= 1;
            connection("UPDATE library.ticket SET Абонемент_на_количество_книг = " + numberRenewal + " WHERE Читательский_билет = " + text);

        }
        return true;
    }

    @Override
    public String searchTicketForSurname(String text) {
        String message = SQL_SEARCH_TICKET_FOR_SURNAME + text;
        System.out.println(message);
        message = "";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_SEARCH_TICKET_FOR_SURNAME + "'" + text + "'");

            while (resultSet.next()) {
                message += "Номер чит-билета: " + String.valueOf(resultSet.getInt(1));
                message += "\nФамилия: " + resultSet.getString(2);
                message += "\nИмя: " + resultSet.getString(3);
                message += "\nОтчество: " + resultSet.getString(4);
                message += "\n--------------------------------------------------\n";
            }
            return message;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void closeSubscription(String text, String textOne) {
        connection("UPDATE library.ticket SET Абонемент = '" + textOne + "' WHERE Читательский_билет = " + text);
    }

    @Override
    public String[][] listForDelivery() {
        String message = "";
        int i = 0;
        int j = 0;
        int k = 0;
        String sqlReadingEmployeeData = "SELECT * FROM library.delivery";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlReadingEmployeeData);

            while (resultSet.next())
                j++;
            resultSet.close();
            if (j == 0) {
                return null;
            }
            ResultSet resultSet1 = statement.executeQuery(sqlReadingEmployeeData);
            String[][] arg = new String[j][j * 5];
            j = 1;
            while (resultSet1.next()) {
                arg[i][k++] = String.valueOf(resultSet1.getInt(j++));
                arg[i][k++] = String.valueOf(resultSet1.getInt(j++));
                arg[i][k++] = String.valueOf(resultSet1.getInt(j++));
                arg[i][k++] = resultSet1.getString(j++);
                arg[i][k] = resultSet1.getString(j);
                j = 1;
                i++;
                k = 0;
            }
            resultSet1.close();
            i = 0;
            k = 1;
            while (i != arg.length) {
                ResultSet resultSet2 = statement.executeQuery(
                        "SELECT * FROM library.ticket WHERE Читательский_билет = " + arg[i][k]);
                resultSet2.next();
                message = arg[i][k] + " (";
                message += resultSet2.getString(2) + " ";
                message += resultSet2.getString(3) + " ";
                message += resultSet2.getString(4) + " " + ")";
                arg[i][k] = message;
                i++;
                resultSet2.close();
            }
            i = 0;
            k = 2;
            while (i != arg.length) {
                ResultSet resultSet2 = statement.executeQuery("SELECT Название_книги FROM library.books WHERE Номер_книги = " + arg[i][k]);
                resultSet2.next();
                message = arg[i][k] + " (";
                message += resultSet2.getString(1) + ")";
                arg[i][k] = message;
                resultSet2.close();
                i++;
            }

            return arg;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String[][] listForTicketTable() {
        String message = "";
        int i = 0;
        int j = 0;
        int k = 0;
        String sqlReadingEmployeeData = "SELECT * FROM library.ticket ORDER BY Читательский_билет";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlReadingEmployeeData);

            while (resultSet.next())
                j++;
            resultSet.close();
            if (j == 0) {
                return null;
            }
            ResultSet resultSet1 = statement.executeQuery(sqlReadingEmployeeData);
            String[][] arg = new String[j][j * 5];
            j = 1;
            while (resultSet1.next()) {
                arg[i][k++] = String.valueOf(resultSet1.getInt(j++));
                arg[i][k++] = resultSet1.getString(j++);
                arg[i][k++] = resultSet1.getString(j++);
                arg[i][k++] = resultSet1.getString(j++);
                arg[i][k++] = resultSet1.getString(j++);
                arg[i][k++] = resultSet1.getString(j++);
                arg[i][k++] = String.valueOf(resultSet1.getInt(j++));
                arg[i][k] = resultSet1.getString(j);
                j = 1;
                i++;
                k = 0;
            }
            return arg;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int checkingHowManyBooksWereTaken(String s) {
        int length = 0;
        String sqlReadingEmployeeData = "SELECT * FROM library.delivery WHERE Чит_билет = " + s;

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlReadingEmployeeData);

            while (resultSet.next()) {
                length++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return length;
    }

    @Override
    public String[][] listOfBooksTicket(String s) {
        String message = "";
        int i = 0;
        int j = 0;
        int k = 0;
        String sqlReadingEmployeeData = "SELECT * FROM library.delivery WHERE Чит_билет = " + s;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlReadingEmployeeData);

            while (resultSet.next())
                j++;
            resultSet.close();
            if (j == 0) {
                return null;
            }
            ResultSet resultSet1 = statement.executeQuery(sqlReadingEmployeeData);
            String[][] arg = new String[j][j * 5];
            j = 1;
            while (resultSet1.next()) {
                arg[i][k++] = String.valueOf(resultSet1.getInt(j++));
                arg[i][k++] = String.valueOf(resultSet1.getInt(j++));
                arg[i][k++] = String.valueOf(resultSet1.getInt(j++));
                arg[i][k++] = resultSet1.getString(j++);
                arg[i][k] = resultSet1.getString(j);
                j = 1;
                i++;
                k = 0;
            }
            for (int l = 0; l < arg.length; l++) {
                ResultSet resultSet2 = statement.executeQuery("SELECT Название_книги FROM library.books WHERE Номер_книги = " + arg[l][2]);
                resultSet2.next();
                arg[l][2] += " " + resultSet2.getString(1);
            }
            return arg;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String[][] thereIsASubcription() {
        String message = "";
        int i = 0;
        int j = 0;
        int k = 0;
        String sqlReadingEmployeeData = "SELECT * FROM library.ticket WHERE Абонемент = 'Открытый'";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlReadingEmployeeData);

            while (resultSet.next()) {
                if (resultSet.getString(7).equals("0")) {
                    connection("UPDATE library.ticket SET Абонемент = 'Закрыт' WHERE Читательский_билет = " + resultSet.getString(1));
                } else {
                    j++;
                }
            }
            resultSet.close();
            if (j == 0) {
                return null;
            }
            ResultSet resultSet1 = statement.executeQuery(sqlReadingEmployeeData);
            String[][] arg = new String[j][j * 5];
            j = 1;
            while (resultSet1.next()) {
                arg[i][0] = resultSet1.getString(1);
                arg[i][1] = resultSet1.getString(2);
                arg[i][2] = resultSet1.getString(3);
                arg[i][3] = resultSet1.getString(4);
                arg[i][4] = resultSet1.getString(7);
                arg[i][5] = resultSet1.getString(8);
                i++;
            }
            return arg;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String[][] noSubcription() {
        String message = "";
        int i = 0;
        int j = 0;
        int k = 0;
        String sqlReadingEmployeeData = "SELECT * FROM library.ticket WHERE Абонемент = 'Закрыт'";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlReadingEmployeeData);

            while (resultSet.next())
                j++;
            System.out.println(j);
            resultSet.close();
            if (j == 0) {
                return null;
            }
            ResultSet resultSet1 = statement.executeQuery(sqlReadingEmployeeData);
            String[][] arg = new String[j][j * 5];
            j = 1;
            while (resultSet1.next()) {
                arg[i][0] = resultSet1.getString(1);
                arg[i][1] = resultSet1.getString(2);
                arg[i][2] = resultSet1.getString(3);
                arg[i][3] = resultSet1.getString(4);
                arg[i][4] = resultSet1.getString(8);
                i++;
            }
            return arg;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String[][] listForEmployee() {
        String message = "";
        int i = 0;
        int j = 0;
        int k = 0;
        String sqlReadingEmployeeData = "SELECT * FROM library.treaty ORDER BY Номер_договора";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlReadingEmployeeData);

            while (resultSet.next())
                j++;
            resultSet.close();
            if (j == 0) {
                return null;
            }
            ResultSet resultSet1 = statement.executeQuery(sqlReadingEmployeeData);
            String[][] arg = new String[j][j * 11];
            j = 1;
            while (resultSet1.next()) {
                arg[i][0] = resultSet1.getString(1);
                arg[i][1] = resultSet1.getString(2);
                arg[i][2] = resultSet1.getString(4);
                arg[i][3] = resultSet1.getString(5);
                arg[i][4] = resultSet1.getString(6);
                arg[i][5] = resultSet1.getString(7);
                arg[i][6] = resultSet1.getString(8);
                arg[i][7] = resultSet1.getString(9);
                arg[i][8] = resultSet1.getString(10);
                arg[i][9] = resultSet1.getString(11);
                arg[i][10] = resultSet1.getString(12);
                i++;
            }
            return arg;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String listForTicket(int check) {
        String message = "";
        String sqlReadingEmployeeData = "SELECT * FROM library.ticket";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlReadingEmployeeData);

            if (check == 1) {
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
            } else {
                while (resultSet.next()) {
                    message += String.valueOf(resultSet.getInt(1)) + " : " + resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4);
                    message += "\n";
                }
                return message;
            }
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
            System.out.println(ea);
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
