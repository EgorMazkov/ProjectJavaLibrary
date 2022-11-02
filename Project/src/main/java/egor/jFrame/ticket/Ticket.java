package egor.jFrame.ticket;

import egor.jFrame.print.ListOfBooksTicket;
import egor.jFrame.print.PrintText;
import egor.jFrame.utils.repositories.MessagesRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

import static egor.jFrame.utils.LaunchingANewWindow.*;

@Component
@Scope("prototype")
public class Ticket extends JFrame {
    MessagesRepository messagesRepository;
    JButton addTicket = new JButton("Добавить читателя");
    JButton deleteTicket = new JButton("Удалить читателя");
    JButton searchForTicket = new JButton("Поиск читателя");
    JButton subscriptionRenewal = new JButton("Абонемент");
    JButton back = new JButton("Вернуться назад");
    JButton listOfBooksTaken = new JButton("Вывести таблицу");
    JTextField enterCodeTicket = new JTextField();
    JComboBox codeTicket;


    public Ticket(MessagesRepository mr) throws HeadlessException {
        super("Читатели");
        messagesRepository = mr;

        String[] nameColumnsTableTicket = new String[]{
                "Читательский билет",
                "Фамилия",
                "Имя",
                "Отчество",
                "Адрес",
                "Номер телефона",
                "Абонемент на количество книг",
                "Абонемент",
                "Кол-во взятых книг"
        };

        String[][] arg = messagesRepository.listForTicketTable();
        for (int i = 0; i < arg.length; i++) {
            arg[i][8] = String.valueOf(messagesRepository.checkingHowManyBooksWereTaken(arg[i][0]));
        }
        Box contents = new Box(BoxLayout.Y_AXIS);
        JPanel jPanel = new JPanel(new GridLayout(3, 2));
        JPanel jPanelTwo = new JPanel(new GridLayout(4, 1));
        String[] mes = new String[arg.length];
        for (int i = 0; i < arg.length; i++) {
            mes[i] = arg[i][0] + " - " + arg[i][1] + " " + arg[i][2] + " " + arg[i][3];
        }
        codeTicket = new JComboBox(mes);

        jPanel.add(addTicket);
        jPanel.add(subscriptionRenewal);
        jPanel.add(deleteTicket);
        jPanel.add(searchForTicket);
        jPanel.add(back);

        jPanelTwo.add(new JLabel("Введите номер чит-билета"));
        jPanelTwo.add(enterCodeTicket);
        jPanelTwo.add(new JLabel("или выберите из списка"));
        jPanelTwo.add(codeTicket);
        jPanelTwo.add(listOfBooksTaken);


        contents.add(new JLabel("Список читателей"));
        contents.add(new JScrollPane(new JTable(arg, nameColumnsTableTicket)));
        contents.add(jPanelTwo);
        contents.add(jPanel);

        setContentPane(contents);
        contents.setBounds(500, 300, 1100, 300);
        setVisible(true);

        addTicket.addActionListener(this::addTicket);
        deleteTicket.addActionListener(this::deleteTicket);
        back.addActionListener(this::backForWindowJFrame);
        searchForTicket.addActionListener(this::searchForTicket);
        subscriptionRenewal.addActionListener(this::subRenewal);
        listOfBooksTaken.addActionListener(this::listOfBooksTaken);
    }


    private void listOfBooksTaken(ActionEvent actionEvent) {
        listOfBooksTaken.addActionListener(e -> {
            if (enterCodeTicket.getText().equals("")) {
                String args = (String) codeTicket.getSelectedItem();
                String[] arg = Objects.requireNonNull(args).split(" ");
                String[][] table = messagesRepository.listOfBooksTicket(arg[0]);
                if (table != null) {
                    setVisible(false);
                    ListOfBooksTicket list = new ListOfBooksTicket(messagesRepository, table);
                } else {
                    PrintText printText = new PrintText("Выданных книг нет");
                    return;
                }
            } else {
                String[][] table = messagesRepository.listOfBooksTicket(enterCodeTicket.getText());
                setVisible(false);
                ListOfBooksTicket list = new ListOfBooksTicket(messagesRepository, table);
            }
        });
    }

    private void subRenewal(ActionEvent actionEvent) {
        subscriptionRenewal.addActionListener(e -> {
            setVisible(false);
            startSubscription();
        });
    }


    private void searchForTicket(ActionEvent actionEvent) {
        searchForTicket.addActionListener(e -> {
            setVisible(false);
            startSearchForTicket();
        });
    }

    private void listForTicket(ActionEvent actionEvent) {
        String message = messagesRepository.listForTicket(1);
        PrintText printText = new PrintText(message);
    }

    private void backForWindowJFrame(ActionEvent actionEvent) {
        back.addActionListener(e -> {
            setVisible(false);
            startWindowJFrame();
        });
    }

    private void addTicket(ActionEvent actionEvent) {
        addTicket.addActionListener(e -> {
            setVisible(false);
            startAddTicket();
        });
    }

    private void deleteTicket(ActionEvent actionEvent) {
        deleteTicket.addActionListener(e -> {
            setVisible(false);
            startDeleteTicket();
        });
    }
}
