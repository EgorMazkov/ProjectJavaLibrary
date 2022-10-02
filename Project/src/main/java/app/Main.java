package app;

import jFrame.itils.repositories.JdbcDataSource;
import jFrame.itils.repositories.MessagesRepository;
import jFrame.itils.repositories.MessagesRepositoryJdbcImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static jFrame.utils.LaunchingANewWindow.*;
// TODO сделать читебельным окно "Добавть новую книгу" и "Добавить сотрудника" и "Добавить читателя"
// TODO если это директор то у него должно быть книги, сотрудники, выход
// TODO если сотрудник то у него книги читатели выход
// TODO абонемент на чит билет ( то есть на какое то определенные книги)
// TODO вывод информации о наличие этой книги

public class Main {
    public static final JdbcDataSource DATA_SOURCE = new JdbcDataSource();
    public static final MessagesRepository MESSAGES_REPOSITORY = new MessagesRepositoryJdbcImpl(DATA_SOURCE.getDataSource());
    public static final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    public static void main(String[] args) {
        updateData("src/main/resources/data.sql", DATA_SOURCE);
        updateData("src/main/resources/schema.sql", DATA_SOURCE);

        if (!MESSAGES_REPOSITORY.checkOneEmployee()) {
            startOneEmployee();
        } else {
            startLibraryJFrame();
        }
    }

    public static void updateData(String file, JdbcDataSource dataSource) {
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            Scanner scanner = new Scanner(file).useDelimiter(";");

            scanner.next();
            while (scanner.hasNext()) {
                st.executeUpdate(scanner.next().trim());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
