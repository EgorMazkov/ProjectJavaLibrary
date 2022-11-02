package egor.app;

import egor.jFrame.utils.repositories.JdbcDataSource;
import egor.jFrame.utils.repositories.MessagesRepository;
import egor.jFrame.utils.repositories.MessagesRepositoryJdbcImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static egor.jFrame.utils.LaunchingANewWindow.startLibraryJFrame;


public class Main {

    public static int ATotalOfBooksWereTaken = 0;
    public static final JdbcDataSource DATA_SOURCE = new JdbcDataSource();
    public static final MessagesRepository MESSAGES_REPOSITORY = new MessagesRepositoryJdbcImpl(DATA_SOURCE.getDataSource());
    public static final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");


    public static void main(String[] args) {
        updateData("src/main/resources/data.sql", DATA_SOURCE);
        updateData("src/main/resources/schema.sql", DATA_SOURCE);

            startLibraryJFrame();
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

