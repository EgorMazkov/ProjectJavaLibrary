package app;

import jFrame.itils.repositories.JdbcDataSource;
import jFrame.itils.repositories.MessagesRepository;
import jFrame.itils.repositories.MessagesRepositoryJdbcImpl;
import jFrame.library.LibraryJFrame;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Main {
    public static final JdbcDataSource DATA_SOURCE = new JdbcDataSource();
    public static final MessagesRepository MESSAGES_REPOSITORY = new MessagesRepositoryJdbcImpl(DATA_SOURCE.getDataSource());

    public static void main(String[] args) {

        updateData("src/main/resources/data.sql", DATA_SOURCE);
        updateData("src/main/resources/schema.sql", DATA_SOURCE);

        LibraryJFrame libraryJFrame = new LibraryJFrame();
        libraryJFrame.setVisible(true);
        libraryJFrame.pack();
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
