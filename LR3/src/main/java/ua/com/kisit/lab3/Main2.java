package ua.com.kisit.lab3;

import javax.swing.*;

public class Main2 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ClockFrame2();
        });
    }
}