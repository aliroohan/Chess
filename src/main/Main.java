package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame wnidow = new JFrame("Simple Chess");
        wnidow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wnidow.setResizable(false);

        GamePanel panel = new GamePanel();
        wnidow.add(panel);
        wnidow.pack();


        wnidow.setLocationRelativeTo(null);
        wnidow.setVisible(true);

        panel.launchGame();
    }
}
