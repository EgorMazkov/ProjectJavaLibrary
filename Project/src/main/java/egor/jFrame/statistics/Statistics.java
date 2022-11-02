package egor.jFrame.statistics;

import egor.jFrame.print.PrintReaderStatistics;
import egor.jFrame.print.PrintTableBooks;
import egor.jFrame.utils.repositories.MessagesRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static egor.jFrame.utils.LaunchingANewWindow.startWindowJFrame;

@Component
@Scope("prototype")
public class Statistics extends JFrame {
    MessagesRepository messagesRepository;
    JButton readerStatistics = new JButton("Статистика читателей");
    JButton statisticsBooks = new JButton("Статистика книг");
    JButton exit = new JButton("Вернуться назад");

    public Statistics(MessagesRepository mr) throws HeadlessException {
        super("Статистика");

        messagesRepository = mr;

        JPanel jPanel = new JPanel(new GridLayout(2, 2));

        jPanel.add(readerStatistics);
        jPanel.add(statisticsBooks);
        jPanel.add(exit);

        add(jPanel, BorderLayout.CENTER);

        readerStatistics.addActionListener(this::readerStat);
        statisticsBooks.addActionListener(this::statBooks);
        exit.addActionListener(this::exit);
    }

    private void exit(ActionEvent actionEvent) {
        exit.addActionListener(e -> {
            setVisible(false);
            startWindowJFrame();
        });
    }

    private void statBooks(ActionEvent actionEvent) {
        statisticsBooks.addActionListener(e -> {
            setVisible(false);
            String[][] arg = new String[0][];
            PrintTableBooks printTableBooks = new PrintTableBooks(messagesRepository, arg, false);
        });
    }

    private void readerStat(ActionEvent actionEvent) {
        readerStatistics.addActionListener(e -> {
            setVisible(false);
            PrintReaderStatistics print = new PrintReaderStatistics(messagesRepository);
        });
    }
}
