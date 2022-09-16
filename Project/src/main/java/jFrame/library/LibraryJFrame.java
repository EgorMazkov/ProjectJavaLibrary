package jFrame.library;

import jFrame.print.PrintText;
import jFrame.utils.WindowError;
import jFrame.window.WindowJFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static app.Main.MESSAGES_REPOSITORY;
import static app.Main.newWindowJFrame;


public class LibraryJFrame extends JFrame {
    JLabel enterIdEmployee = new JLabel("id сотрудника");
    JTextField idEmployee = new JTextField(20);
    JLabel enterPasswordEmployee = new JLabel("Пароль сотрудника");
    JTextField passwordEmployee = new JTextField(20);
    JButton register = new JButton("Вход");



    public LibraryJFrame() {
        super("LibraryJFrame");
        // подготавливаем компоненты обьекта

        JPanel buttonsPanel = new JPanel(new FlowLayout());

        buttonsPanel.add(enterIdEmployee, BorderLayout.CENTER);
        buttonsPanel.add(idEmployee, BorderLayout.CENTER);
        buttonsPanel.add(enterPasswordEmployee, BorderLayout.CENTER);
        buttonsPanel.add(passwordEmployee, BorderLayout.CENTER);
        buttonsPanel.add(register, BorderLayout.CENTER);

        add(buttonsPanel, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        register.addActionListener(this::enterIdEmployee);

    }

    private void enterIdEmployee(ActionEvent actionEvent) {
        if (!MESSAGES_REPOSITORY.checkUser(idEmployee.getText(), passwordEmployee.getText())) {
            register.addActionListener(e -> {
                setVisible(false);
                newWindowJFrame();
            });
        } else {
            setVisible(false);
            PrintText printText = new PrintText("Введены неправильные данные");
            LibraryJFrame libraryJFrame = new LibraryJFrame();
            libraryJFrame.setBounds(300, 300, 1000, 500);
            libraryJFrame.setLayout(new GridLayout(3, 2, 2, 2));
            libraryJFrame.setVisible(true);
        }
    }
}


