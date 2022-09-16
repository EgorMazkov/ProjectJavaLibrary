//import java.awt.BorderLayout;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.JTable;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Wydatki extends JFrame {
//
//    private static final long serialVersionUID = 6846421395193283567L;
//
//    private final static String username = "root";
//    private final static String password = "QWERTY123";
//    private final static String connectionURL = "jdbc:mysql://localhost:3306/centus_database";
//    private final static String sql = "select dzien,Zywnosc,Mieszkianie,Media,Transport,Ubrania,Rachunki,"
//            + "Rozrywka,Suma_wydatkow from wydatki where NickName = ? and rok = ? and miesiac = ?";
//    private final static Object[] columnsHeader = new String[]{"Dzien", "Zywnosc", "Mieszkanie", "Media",
//            "Transport", "Ubrania", "Rachunki", "Rozrywka", "Suma"};
//    private final static Connection connection;
//    static {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            connection = DriverManager.getConnection(connectionURL, username, password);
//        } catch (ClassNotFoundException | SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//    }
//
//    public Wydatki(String nickName, String rok, String miesiac) {
//        try {
//            init(nickName, rok, miesiac);
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//    }
//
//    private void init(String nickName, String rok, String miesiac) throws SQLException {
//
//        //это МАКСИМАЛЬНО странная строка кода
//        StartInterface.StartJMenu(this, nickName);
//
//        JTable jtable = new JTable(findData(nickName, rok, miesiac), columnsHeader);
//        jtable.setFillsViewportHeight(true);
//        JPanel jpanel = new JPanel();
//        jpanel.setLayout(new BorderLayout());
//        jpanel.add(jtable.getTableHeader(), BorderLayout.PAGE_START);
//        jpanel.add(jtable, BorderLayout.CENTER);
//        this.add(jpanel);
//        this.setTitle("Wydatki");
//        this.setVisible(true);
//    }
//
//    private Object[][] findData(String nickName, String rok, String miesiac) throws SQLException {
//
//        PreparedStatement prestmt = connection.prepareStatement(sql);
//        prestmt.setString(1, nickName);
//        prestmt.setString(2, rok);
//        prestmt.setString(3, miesiac);
//        ResultSet resultSet = prestmt.executeQuery();
//
//        final List<Object[]> tableContent = new ArrayList<>();
//        while (resultSet.next()) {
//            final Object[] data = new Object[]{
//                    resultSet.getString("dzien"), resultSet.getString("Zywnosc"),
//                    resultSet.getString("Mieszkianie"), resultSet.getString("Media"),
//                    resultSet.getString("Transport"), resultSet.getString("Ubrania"),
//                    resultSet.getString("Rachunki"), resultSet.getString("Rozrywka"),
//                    resultSet.getString("Suma_wydatkow")
//            };
//            tableContent.add(data);
//        }
//        return tableContent.toArray(new Object[tableContent.size()][]);
//
//    }
//
//}