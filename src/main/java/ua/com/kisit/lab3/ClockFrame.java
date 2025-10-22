package ua.com.kisit.lab3;

import javax.swing.*;
import java.awt.*;

public class ClockFrame {

    JFrame frame;
    JPanel panel;
    JLabel label;

    public ClockFrame() {
        frame = new JFrame("My Clock");
        panel = new JPanel();
        label = new JLabel();

        frame.setSize(500,80);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel.add(label, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.CENTER);

        label.setFont(new Font("Arial", Font.BOLD, 20));
        panel.setBackground(Color.CYAN);

        ClockExt clockExt = new ClockExt(12,06,30);

        Timer timer = new Timer(1000,
//        new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        }
        e -> {
            clockExt.nextSeconds();
            label.setText(clockExt.toString());
        });

        timer.start();

        frame.setVisible(true);

    }
}
