package jFrame.utils;

import jFrame.book.*;
import jFrame.employee.*;
import jFrame.library.*;
import jFrame.ticket.*;
import jFrame.window.*;

import static app.Main.*;

import java.awt.*;

public class LaunchingANewWindow {
    private final static GridLayout layout = new GridLayout(3, 3, 3, 3);

    public static void startWindowJFrame() {
        WindowJFrame windowJFrame = context.getBean("windowJFrame", WindowJFrame.class);
        windowJFrame.setBounds(700, 300, 400, 500);
        windowJFrame.setLayout(layout);
        windowJFrame.setVisible(true);
    }

    public static void startLibraryJFrame() {
        LibraryJFrame libraryJFrame = context.getBean("libraryJFrame", LibraryJFrame.class);
        libraryJFrame.setBounds(700, 300, 400, 500);
        libraryJFrame.setLayout(layout);
        libraryJFrame.setVisible(true);
    }

    public static void startBook() {
        Books searchBooks = context.getBean("books", Books.class);
        searchBooks.setBounds(700, 300, 400, 500);
        searchBooks.setLayout(layout);
        searchBooks.setVisible(true);
    }

    public static void startAddBooks() {
        AddBooks addBooks = context.getBean("addBooks", AddBooks.class);
        addBooks.setBounds(700, 300, 400, 500);
        addBooks.setLayout(layout);
        addBooks.setVisible(true);
    }

    public static void startSearchForNumber() {
        SearchForNumber searchForNumber = context.getBean("searchForNumber", SearchForNumber.class);
        searchForNumber.setBounds(700, 300, 400, 500);
        searchForNumber.setLayout(layout);
        searchForNumber.setVisible(true);
    }

    public static void startSearchForAuthor() {
        SearchForAuthor searchForAuthor = context.getBean("searchForAuthor", SearchForAuthor.class);
        searchForAuthor.setBounds(700, 300, 400, 500);
        searchForAuthor.setLayout(layout);
        searchForAuthor.setVisible(true);
    }

    public static void startReturnOfTheBooks() {
        ReturnOfTheBook returnOfTheBook = context.getBean("returnOfTheBook", ReturnOfTheBook.class);
        returnOfTheBook.setBounds(700, 300, 400, 500);
        returnOfTheBook.setLayout(layout);
        returnOfTheBook.setVisible(true);
    }

    public static void startDelivery() {
        DeliveryBooks deliveryJFrame = context.getBean("delivery", DeliveryBooks.class);
        deliveryJFrame.setBounds(700, 300, 400, 500);
        deliveryJFrame.setLayout(layout);
        deliveryJFrame.setVisible(true);
    }

    public static void startEmployee() {
        Employee employee = context.getBean("employee", Employee.class);
        employee.setBounds(700, 300, 400, 500);
        employee.setLayout(layout);
        employee.setVisible(true);
    }

    public static void startOneEmployee() {
        AddEmployee addEmployee = context.getBean("addOneEmployee", AddEmployee.class);
        addEmployee.setBounds(700, 300, 400, 500);
        addEmployee.setLayout(new GridLayout(30, 30, 2, 2));
        addEmployee.setVisible(true);
    }
    public static void startAddEmployee() {
        AddEmployee addEmployee = context.getBean("addEmployee", AddEmployee.class);
        addEmployee.setBounds(700, 300, 400, 500);
        addEmployee.setLayout(new FlowLayout());
        addEmployee.setVisible(true);
    }

    public static void startTicket() {
        Ticket ticket = context.getBean("ticket", Ticket.class);
        ticket.setBounds(700, 300, 400, 500);
        ticket.setLayout(layout);
        ticket.setVisible(true);
    }
    public static void startAddTicket() {
        AddTicket addTicket = context.getBean("addTicket", AddTicket.class);
        addTicket.setBounds(700, 300, 400, 500);
        addTicket.setLayout(layout);
        addTicket.setVisible(true);
    }

    public static void startDeleteTicket() {
        DeleteTicket deleteTicket = context.getBean("deleteTicket", DeleteTicket.class);
        deleteTicket.setBounds(700, 300, 400, 500);
        deleteTicket.setLayout(layout);
        deleteTicket.setVisible(true);
    }

    public static void startListOfBooksThatReadersHaveNotReturned() {
        ListOfBooksThatReadersHaveNotReturned list = context.getBean("listOfBooksThatReadersHaveNotReturned", ListOfBooksThatReadersHaveNotReturned.class);
        list.setBounds(700, 300, 400, 500);
        list.setLayout(layout);
        list.setVisible(true);
    }
}
