package jFrame.itils.repositories;

import javax.sql.DataSource;
import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MessagesRepositoryJdbcImpl implements MessagesRepository{
    private DataSource dataSource;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean checkUser(String idEmployee, String password) {
        String sqlCheck = "SELECT Пароль FROM library.employee WHERE Номер_сотрудника = " + idEmployee;

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlCheck);

            if (!resultSet.next()) {
                return true;
            }
            String passwordEmployee = resultSet.getString(1);
            if (passwordEmployee.equals(password))
                return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public void save(String libraryCard, String booksCode, String dateOfIssue, String dateOfDelivery) {
        String sqlInsert = "INSERT INTO library.delivery (Код_выдачи, Чит_билет, Код_книги, Дата_выдачи, Дата_сдачи) " +
                "VALUES (default, '1', '1', '12.11.2011', '12.12.2011')";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlInsert);

            resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDelivery(String booksCode) {
        int lengthBooks = 0;
        String sqlCheck = "SELECT Количество_книг FROM library.employee WHERE Номер_книги = " + booksCode;

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlCheck);

            if (!resultSet.next()) {
                return;
            }
            lengthBooks = resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sqlUpdate = "UPDATE library.books SET Количество = " + lengthBooks + " WHERE Номер_книги = " + booksCode;

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlUpdate);

            resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}


