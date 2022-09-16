package jFrame.employee;

import javax.swing.*;
import java.awt.*;

import static app.Main.MESSAGES_REPOSITORY;

public class EmployeeData extends JFrame {
    public EmployeeData() throws HeadlessException {
        super("Данные сотрудников");
        MESSAGES_REPOSITORY.readingEmployeeData();
    }
}
