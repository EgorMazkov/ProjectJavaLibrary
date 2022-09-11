package jFrame.itils.repositories;

import javax.swing.*;

public interface MessagesRepository {
    boolean checkUser(String idEmployee, String password);
    void save(String libraryCard, String booksCode, String dateOfIssue,
                String dateOfDelivery);
    void updateDelivery(String booksCode);
}
