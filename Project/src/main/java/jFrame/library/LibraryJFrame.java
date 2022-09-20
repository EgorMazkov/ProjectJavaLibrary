package jFrame.library;

import jFrame.print.PrintText;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static app.Main.*;
import static jFrame.utils.LaunchingANewWindow.*;


public class LibraryJFrame extends JFrame {

    JLabel enterIdEmployee = new JLabel("id сотрудника");
    JTextField idEmployee = new JTextField(20);
    JLabel enterPasswordEmployee = new JLabel("Пароль сотрудника");
    JTextField passwordEmployee = new JTextField(20);
    JButton register = new JButton("Вход");
    JButton exitProgram = new JButton("Закрыть программу");

    public LibraryJFrame() {
        super("Вход");
        JPanel jPanel = new JPanel(new FlowLayout());

        jPanel.add(enterIdEmployee);
        jPanel.add(idEmployee);
        jPanel.add(enterPasswordEmployee);
        jPanel.add(passwordEmployee);
        jPanel.add(register);
        jPanel.add(exitProgram);

        add(jPanel, BorderLayout.CENTER);
        pack();
        register.addActionListener(this::enterIdEmployee);
        exitProgram.addActionListener(this::exit);
    }

    private void exit(ActionEvent actionEvent) {
        context.close();
        System.exit(0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void enterIdEmployee(ActionEvent actionEvent) {
        if (!MESSAGES_REPOSITORY.checkUser(idEmployee.getText(), passwordEmployee.getText())) {
            register.addActionListener(e -> {
                setVisible(false);
                startWindowJFrame();
            });
        } else {
            setVisible(false);
            PrintText printText = new PrintText("Введены неправильные данные");
            startLibraryJFrame();
        }
    }
}


