package egor.jFrame.library;

import egor.jFrame.print.PrintText;
import egor.jFrame.utils.repositories.MessagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static egor.app.Main.*;
import static egor.jFrame.utils.LaunchingANewWindow.*;

@Component
@Scope("prototype")
public class LibraryJFrame extends JFrame {
    public static String employeeOrDirectory = "";
    private MessagesRepository messagesRepository;
    JLabel enterIdEmployee = new JLabel("id сотрудника");
    JTextField idEmployee = new JTextField(20);
    JLabel enterPasswordEmployee = new JLabel("Пароль сотрудника");
    JTextField passwordEmployee = new JTextField(20);
    JButton register = new JButton("Вход");
    JButton exitProgram = new JButton("Закрыть программу");

    @Autowired
    public LibraryJFrame(MessagesRepository mr) {
        super("Вход");
        messagesRepository = mr;
        JPanel jPanel = new JPanel(new GridLayout(3, 2));

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
        if (idEmployee.getText().equals(""))
            return;
        if (messagesRepository.checkUser(idEmployee.getText(), passwordEmployee.getText())) {
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