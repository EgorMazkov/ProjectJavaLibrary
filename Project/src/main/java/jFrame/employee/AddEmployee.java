package jFrame.employee;

import org.springframework.security.crypto.password.PasswordEncoder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static app.Main.MESSAGES_REPOSITORY;
import static app.Main.passwordEncoder;
import static jFrame.utils.LaunchingANewWindow.*;

public class AddEmployee extends JFrame {

    public static int indexForNumberEmployee = 2;
    JLabel enterSurnameEmployee = new JLabel("Введите Фамилию :");
    JTextField surnameEmployee = new JTextField(20);
    JLabel enterNameEmployee = new JLabel("Введите Имя :");
    JTextField nameEmployee = new JTextField(20);
    JLabel enterMiddleNameEmployee = new JLabel("Введите Отчество :");
    JTextField middleNameEmployee = new JTextField(20);
    JLabel enterINNEmployee = new JLabel("Введите ИНН:");
    JTextField INNEmployee = new JTextField(20);
    JLabel enterSNILSEmployee = new JLabel("Введите СНИЛС :");
    JTextField SNILSEmployee = new JTextField(20);
    JLabel enterDateOfBirthEmployee = new JLabel("Введите Дата рождения :");
    JTextField dateOfBirthEmployee = new JTextField(20);
    JLabel enterStartDateEmployee = new JLabel("Введите Дача начала работы :");
    JTextField startDateEmployee = new JTextField(20);
    JLabel enterPostEmployee = new JLabel("Введите должность сотрудника: ");
    JTextField postEmployee = new JTextField(20);
    JLabel enterPhoneNumberEmployee = new JLabel("Введите номер телефона: ");
    JTextField phoneNumberEmployee = new JTextField(20);
    JButton addEmployee = new JButton("Добавить сотрудника");
    JLabel enterPasswordEmployee = new JLabel("Пароль сотрудника");
    JTextField passwordEmployee = new JTextField(20);
    JButton back = new JButton("Вернуться назад");

    public AddEmployee() throws HeadlessException {
        super("Добавление сотрудника");
        JPanel jPanel = new JPanel(new FlowLayout());

        jPanel.add(enterSurnameEmployee, BorderLayout.CENTER);
        jPanel.add(surnameEmployee, BorderLayout.CENTER);
        jPanel.add(enterNameEmployee, BorderLayout.CENTER);
        jPanel.add(nameEmployee, BorderLayout.CENTER);
        jPanel.add(enterMiddleNameEmployee, BorderLayout.CENTER);
        jPanel.add(middleNameEmployee, BorderLayout.CENTER);
        jPanel.add(enterINNEmployee, BorderLayout.CENTER);
        jPanel.add(INNEmployee, BorderLayout.CENTER);
        jPanel.add(enterSNILSEmployee, BorderLayout.CENTER);
        jPanel.add(SNILSEmployee, BorderLayout.CENTER);
        jPanel.add(enterDateOfBirthEmployee, BorderLayout.CENTER);
        jPanel.add(dateOfBirthEmployee, BorderLayout.CENTER);
        jPanel.add(enterStartDateEmployee, BorderLayout.CENTER);
        jPanel.add(startDateEmployee, BorderLayout.CENTER);
        jPanel.add(enterPostEmployee, BorderLayout.CENTER);
        jPanel.add(postEmployee, BorderLayout.CENTER);
        jPanel.add(enterPhoneNumberEmployee, BorderLayout.CENTER);
        jPanel.add(phoneNumberEmployee, BorderLayout.CENTER);
        jPanel.add(enterPasswordEmployee, BorderLayout.CENTER);
        jPanel.add(passwordEmployee, BorderLayout.CENTER);
        jPanel.add(addEmployee, BorderLayout.CENTER);
        jPanel.add(back, BorderLayout.CENTER);

        add(jPanel, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        addEmployee.addActionListener(this::addEmployeeFordb);
        back.addActionListener(this::backForWindowJFrame);
    }

    private void backForWindowJFrame(ActionEvent actionEvent) {
        back.addActionListener(e -> {
            setVisible(false);
            startEmployee();
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
            startEmployee();
        });
    }
}
