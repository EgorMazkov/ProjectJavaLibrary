package jFrame.library;

import javax.swing.*;
import java.awt.*;

public class MyWindow extends JFrame {

    public MyWindow() {
        setTitle("first frame");
        setBounds(100, 100, 250, 100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3, 2, 2, 2));
        JLabel label = new JLabel("Label");
        container.add(label);
        JButton button = new JButton("YES");
        container.add(button);
        JButton button2 = new JButton("NO");
        container.add(button2);
        button.addActionListener(e -> {
            setVisible(false);


            JFrame frame = new JFrame();
            frame.setTitle("second frame");
            frame.setBounds(100, 100, 250, 100);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

    public static void main(String[] args) {
        MyWindow myWindow = new MyWindow();
        myWindow.setVisible(true);
    }}
