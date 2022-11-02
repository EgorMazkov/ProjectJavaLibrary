package egor.jFrame.print;

import egor.jFrame.utils.repositories.MessagesRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static egor.jFrame.utils.LaunchingANewWindow.*;

public class PrintEmployee extends JFrame {
    MessagesRepository messagesRepository;
    JButton addEmployee = new JButton("Добавить сотрудника");
    JButton exit = new JButton("Вернуться назад");

    public PrintEmployee(MessagesRepository mr) throws HeadlessException {
        super("Информация о сотрудниках");
        messagesRepository = mr;

        String[] nameColumns = new String[]{
                "Номер договора",
                "Номер сотрудника",
                "Должность",
                "Номер телефона",
                "Фамилия",
                "Имя",
                "Отчество",
                "ИНН",
                "СНИЛС",
                "Дата рождения",
                "Дата начала работы"
        };

        String[][] arg = messagesRepository.listForEmployee();
        Box contents = new Box(BoxLayout.Y_AXIS);
        JPanel jPanel = new JPanel();

        jPanel.add(addEmployee);
        jPanel.add(exit);

        contents.add(new JScrollPane(new JTable(arg, nameColumns)));
        contents.add(jPanel);

        setContentPane(contents);
        setBounds(0, 0, screenWidth, screenHeight);
        setVisible(true);

        addEmployee.addActionListener(this::addEmployee);
        exit.addActionListener(this::exit);
    }

    private void addEmployee(ActionEvent actionEvent) {
        addEmployee.addActionListener(e -> {
            setVisible(false);
            startAddEmployee();
        });
    }

    private void exit(ActionEvent actionEvent) {
        exit.addActionListener(e -> {
            setVisible(false);
            startWindowJFrame();
        });
    }
}