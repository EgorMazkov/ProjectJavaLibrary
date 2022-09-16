package jFrame.employee;

import jFrame.print.PrintText;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static app.Main.MESSAGES_REPOSITORY;
import static app.Main.newWindowJFrame;

public class Employee extends JFrame {
    JButton treaty = new JButton("Данные сотрудников");
    JButton addEmployee = new JButton("Добавить сотрудника");
    JButton back = new JButton("Вернуться назад");

    public Employee() throws HeadlessException {
        JPanel jPanel = new JPanel(new GridLayout());

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
            newWindowJFrame();
        });
    }

    private void addEmployee(ActionEvent actionEvent) {
        addEmployee.addActionListener(e -> {
            setVisible(false);
            AddEmployee addEmployee = new AddEmployee();
            addEmployee.setBounds(300, 300, 500, 500);
            addEmployee.setLayout(new GridLayout(12, 2, 2, 2));
            addEmployee.setVisible(true);
        });
    }
}
