package egor.jFrame.utils;

import egor.jFrame.book.AddBooks;
import egor.jFrame.book.DeliveryBooks;
import egor.jFrame.book.ReturnOfTheBook;
import egor.jFrame.employee.AddEmployee;
import egor.jFrame.library.LibraryJFrame;
import egor.jFrame.statistics.Statistics;
import egor.jFrame.ticket.*;
import egor.jFrame.window.WindowJFrame;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.awt.*;

import static egor.app.Main.context;

@Component
@Scope("prototype")
public class LaunchingANewWindow {
    private final static GridLayout layout = new GridLayout(3, 3, 3, 3);
    static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    public static int screenWidth = dim.width;
    public static int screenHeight = dim.height - 45;

    public static void startWindowJFrame() {
        WindowJFrame windowJFrame = context.getBean("windowJFrame", WindowJFrame.class);
        windowJFrame.setBounds(0, 0, screenWidth, screenHeight);
        windowJFrame.setVisible(true);
    }

    public static void startLibraryJFrame() {
        LibraryJFrame libraryJFrame = context.getBean("libraryJFrame", LibraryJFrame.class);
        libraryJFrame.setBounds(500, 300, screenWidth, screenHeight);
        libraryJFrame.pack();
        libraryJFrame.setVisible(true);
    }

    public static void startAddBooks() {
        AddBooks addBooks = context.getBean("addBooks", AddBooks.class);
        addBooks.setBounds(0, 0, screenWidth, screenHeight);
        addBooks.setVisible(true);
    }

    public static void startReturnOfTheBooks() {
        ReturnOfTheBook returnOfTheBook = context.getBean("returnOfTheBook", ReturnOfTheBook.class);
        returnOfTheBook.setBounds(0, 0, screenWidth, screenHeight);
        returnOfTheBook.setVisible(true);
    }

    public static void startDelivery() {
        DeliveryBooks deliveryJFrame = context.getBean("deliveryBooks", DeliveryBooks.class);
        deliveryJFrame.setBounds(0, 0, screenWidth, screenHeight);
        deliveryJFrame.setVisible(true);
    }


    public static void startAddEmployee() {
        AddEmployee addEmployee = context.getBean("addEmployee", AddEmployee.class);
        addEmployee.setBounds(0, 0, screenWidth, screenHeight);
        addEmployee.setVisible(true);
    }

    public static void startSearchForTicket() {
        SearchForTicket search = context.getBean("searchForTicket", SearchForTicket.class);
        search.setBounds(0, 0, screenWidth, screenHeight);
        search.setVisible(true);
    }

    public static void startTicket() {
        Ticket ticket = context.getBean("ticket", Ticket.class);
        ticket.setBounds(0, 0, screenWidth, screenHeight);
        ticket.setVisible(true);
    }

    public static void startAddTicket() {
        AddTicket addTicket = context.getBean("addTicket", AddTicket.class);
        addTicket.setBounds(0, 0, screenWidth, screenHeight);
        addTicket.setVisible(true);
    }

    public static void startDeleteTicket() {
        DeleteTicket deleteTicket = context.getBean("deleteTicket", DeleteTicket.class);
        deleteTicket.setBounds(0, 0, screenWidth, screenHeight);
        deleteTicket.setLayout(layout);
        deleteTicket.setVisible(true);
    }

    public static void startSubscriptionRenewal() {
        SubscriptionRenewal subscriptionRenewal = context.getBean("subscriptionRenewal", SubscriptionRenewal.class);
        subscriptionRenewal.setBounds(0, 0, screenWidth, screenHeight);
        subscriptionRenewal.setVisible(true);
    }

    public static void startCloseSubscription() {
        SubscriptionClose subscriptionClose = context.getBean("subscriptionClose", SubscriptionClose.class);
        subscriptionClose.setBounds(0, 0, screenWidth, screenHeight);
        subscriptionClose.setVisible(true);
    }

    public static void startSubscription() {
        Subscription subscription = context.getBean("subscription", Subscription.class);
        subscription.setBounds(0, 0, screenWidth, screenHeight);
        subscription.setVisible(true);
    }

    public static void startStatistics() {
        Statistics statistics = context.getBean("statistics", Statistics.class);
        statistics.setBounds(0, 0, screenWidth, screenHeight);
        statistics.setVisible(true);
    }
}
