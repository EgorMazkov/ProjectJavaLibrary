package jFrame.employee;

import jFrame.print.PrintText;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static app.Main.MESSAGES_REPOSITORY;
import static jFrame.utils.LaunchingANewWindow.*;

public class Employee extends JFrame {
    JButton treaty = new JButton("Данные сотрудников");
    JButton back = new JButton("Вернуться назад");
    JButton addEmployee = new JButton("Добавить сотрудника");

    public Employee() throws HeadlessException {
        JPanel jPanel = new JPanel(new FlowLayout());

        jPanel.add(treaty);
        jPanel.add(addEmployee);
        jPanel.add(back);

        add(jPanel, BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        addEmployee.addActionListener(this::addEmployee);
        back.addActionListener(this::backForWindowJFrame);
        treaty.addActionListener(this::employeeData);
    }

    private void employeeData(ActionEvent actionEvent) {
        String message = MESSAGES_REPOSITORY.readingEmployeeData();
        PrintText printText = new PrintText(message);
    }

    private void backForWindowJFrame(ActionEvent actionEvent) {
        back.addActionListener(e -> {
            setVisible(false);
            startWindowJFrame();
        });
    }

    private void addEmployee(ActionEvent actionEvent) {
        addEmployee.addActionListener(e -> {
            setVisible(false);
            startAddEmployee();
        });
    }
}
