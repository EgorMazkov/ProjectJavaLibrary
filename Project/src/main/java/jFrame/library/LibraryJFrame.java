package jFrame.library;

import jFrame.window.WindowJFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static app.Main.MESSAGES_REPOSITORY;

public class LibraryJFrame extends JFrame {
    String idEmployees;
    String password;
    JTextField idEmployee = new JTextField(20);
    JTextField passwordEmployee = new JTextField(20);
    JButton register = new JButton("Вход");


    public LibraryJFrame() {
        // подготавливаем компоненты обьекта
        JLabel countLabel = new JLabel("label");
        countLabel.setBounds(400, 400, 1000, 500);
        JLabel enterIdEmployee = new JLabel("Enter id employee");
        JLabel enterPasswordEmployee = new JLabel("Enter password employee");

        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(countLabel, BorderLayout.SOUTH); // размещение

        buttonsPanel.add(enterIdEmployee);
        buttonsPanel.add(idEmployee);
        buttonsPanel.add(enterPasswordEmployee);
        buttonsPanel.add(passwordEmployee);
        buttonsPanel.add(register);

        add(buttonsPanel, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        register.addActionListener(this::enterIdEmployee);

    }

    private void enterIdEmployee(ActionEvent actionEvent) {
        idEmployees = idEmployee.getText();
        password = passwordEmployee.getText();
        if (!MESSAGES_REPOSITORY.checkUser(idEmployees, password)) {
            register.addActionListener(e -> {
                setVisible(false);
                WindowJFrame windowJFrame = new WindowJFrame();
                windowJFrame.setBounds(100, 100, 1000, 500);
                windowJFrame.setVisible(true);
            });


        }
    }


}


