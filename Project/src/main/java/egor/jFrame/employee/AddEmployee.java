package egor.jFrame.employee;

import egor.jFrame.print.PrintEmployee;
import egor.jFrame.utils.repositories.MessagesRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static egor.jFrame.utils.LaunchingANewWindow.*;

@Component
@Scope("prototype")
public class AddEmployee extends JFrame {

    MessagesRepository messagesRepository;
    public static int indexForNumberEmployee = 2;
    private String path;
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

    public AddEmployee(MessagesRepository mr) throws HeadlessException {
        super("Добавление сотрудника");

        messagesRepository = mr;

        this.path = path;

        JPanel jPanel = new JPanel(new GridLayout(11, 2));

        jPanel.add(enterSurnameEmployee);
        jPanel.add(surnameEmployee);
        jPanel.add(enterNameEmployee);
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
        jPanel.add(back);
        jPanel.add(addEmployee);

        add(jPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        addEmployee.addActionListener(this::addEmployeeFordb);
        back.addActionListener(this::backForWindowJFrame);
    }

    private void backForWindowJFrame(ActionEvent actionEvent) {
        back.addActionListener(e -> {
            setVisible(false);
            PrintEmployee printEmployee = new PrintEmployee(messagesRepository);
        });
    }

    private void addEmployeeFordb(ActionEvent actionEvent) {
        addEmployee.addActionListener(e -> {
            setVisible(false);
            messagesRepository.addEmployee(
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
            if (path.equals("main")) {
                startWindowJFrame();
            } else {
                setVisible(false);
                PrintEmployee printEmployee = new PrintEmployee(messagesRepository);
            }
        });
    }
}
