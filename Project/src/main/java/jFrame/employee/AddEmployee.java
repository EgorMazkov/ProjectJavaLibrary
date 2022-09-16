package jFrame.employee;

import jFrame.window.WindowJFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static app.Main.MESSAGES_REPOSITORY;
import static app.Main.newWindowJFrame;

public class AddEmployee extends JFrame {
    public static int indexForNumberEmployee = 2;
    JLabel enterSurnameEmployee = new JLabel("Введите Фамилию :");
    JTextField surnameEmployee = new JTextField();

    JLabel enternameEmployee = new JLabel("Введите Имя :");
    JTextField nameEmployee = new JTextField();

    JLabel enterMiddleNameEmployee = new JLabel("Введите Отчество :");
    JTextField middleNameEmployee = new JTextField();

    JLabel enterINNEmployee = new JLabel("Введите ИНН:");
    JTextField INNEmployee = new JTextField();

    JLabel enterSNILSEmployee = new JLabel("Введите СНИЛС :");
    JTextField SNILSEmployee = new JTextField();

    JLabel enterDateOfBirthEmployee = new JLabel("Введите Дата рождения :");
    JTextField dateOfBirthEmployee = new JTextField();

    JLabel enterStartDateEmployee = new JLabel("Введите Дача начала работы :");
    JTextField startDateEmployee = new JTextField();

    JLabel enterPostEmployee = new JLabel("Введите должность сотрудника: ");
    JTextField postEmployee = new JTextField();

    JLabel enterPhoneNumberEmployee = new JLabel("Введите номер телефона: ");
    JTextField phoneNumberEmployee = new JTextField();

    JLabel enterPasswordEmployee = new JLabel("Введите новый пароль сотрудника: ");
    JTextField passwordEmployee = new JTextField();

    JButton addEmployee = new JButton("Добавить");
    JButton back = new JButton("Вернуться назад");

    public AddEmployee() throws HeadlessException {
        JPanel jPanel = new JPanel(new FlowLayout());

        jPanel.add(enterSurnameEmployee, BorderLayout.CENTER);
        jPanel.add(surnameEmployee);
        jPanel.add(enternameEmployee);
        jPanel.add(nameEmployee);
        jPanel.add(enterMiddleNameEmployee);
        jPanel.add(middleNameEmployee);
        jPanel.add(enterINNEmployee);
        jPanel.add(INNEmployee);
        jPanel.add(enterSNILSEmployee);
        jPanel.add(SNILSEmployee);
        jPanel.add(enterDateOfBirthEmployee);
        jPanel.add(dateOfBirthEmployee);
        jPanel.add(enterStartDateEmployee);
        jPanel.add(startDateEmployee);
        jPanel.add(enterPostEmployee);
        jPanel.add(postEmployee);
        jPanel.add(enterPhoneNumberEmployee);
        jPanel.add(phoneNumberEmployee);
        jPanel.add(enterPasswordEmployee);
        jPanel.add(passwordEmployee);
        jPanel.add(addEmployee);
        jPanel.add(back);

        add(jPanel, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        addEmployee.addActionListener(this::addEmployeeFordb);
        back.addActionListener(this::backForWindowJFrame);
    }

    private void backForWindowJFrame(ActionEvent actionEvent) {
        back.addActionListener(e -> {
            setVisible(false);
            Employee employee = new Employee();
            employee.setBounds(300, 300, 1000, 500);
            employee.setLayout(new GridLayout(3, 2, 2, 2));
            employee.setVisible(true);
        });
    }

    private void addEmployeeFordb(ActionEvent actionEvent) {
        MESSAGES_REPOSITORY.addEmployee(
                surnameEmployee.getText(),
                nameEmployee.getText(),
                middleNameEmployee.getText(),
                INNEmployee.getText(),
                SNILSEmployee.getText(),
                dateOfBirthEmployee.getText(),
                startDateEmployee.getText(),
                passwordEmployee.getText(),
                postEmployee.getText(),
                phoneNumberEmployee.getText(),
                indexForNumberEmployee++
        );
        addEmployee.addActionListener(e -> {
            setVisible(false);
            Employee employee = new Employee();
            employee.setBounds(300, 300, 1000, 500);
            employee.setLayout(new GridLayout(3, 2, 2, 2));
            employee.setVisible(true);
        });
    }
}
