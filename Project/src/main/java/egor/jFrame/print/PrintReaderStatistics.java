package egor.jFrame.print;

import egor.jFrame.utils.repositories.MessagesRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static egor.jFrame.utils.LaunchingANewWindow.*;

public class PrintReaderStatistics extends JFrame {
    MessagesRepository messagesRepository;
    JButton exit = new JButton("Вернуться назад");

    public PrintReaderStatistics(MessagesRepository messagesRepository) throws HeadlessException {
        super("Статистика читателей");

        this.messagesRepository = messagesRepository;

        String[] nameColumnsTableThereIsASubscription = new String[]{
                "Читательский билет",
                "Фамилия",
                "Имя",
                "Отчество",
                "Абонемент на количество книг",
                "Абонемент"
        };

        String[] nameColumnsTableNoSubscription = new String[]{
                "Читательский билет",
                "Фамилия",
                "Имя",
                "Отчество",
                "Абонемент"
        };

        Box contents = new Box(BoxLayout.Y_AXIS);
        contents.add(new JLabel("Читатели с открытым абонементом"), BorderLayout.CENTER);
        contents.add(new JScrollPane(new JTable(messagesRepository.thereIsASubcription(), nameColumnsTableThereIsASubscription)));
        contents.add(new JLabel("Читатели с закрытым абонементом"), BorderLayout.CENTER);
        contents.add(new JScrollPane(new JTable(messagesRepository.noSubcription(), nameColumnsTableNoSubscription)));
        contents.add(new JPanel().add(exit));

        setContentPane(contents);
        setBounds(0, 0, screenWidth, screenHeight);
        setVisible(true);

        exit.addActionListener(this::exit);
    }

    private void exit(ActionEvent actionEvent) {
        exit.addActionListener(e -> {
            setVisible(false);
            startStatistics();
        });
    }
}