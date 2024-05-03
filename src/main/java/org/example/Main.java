package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private final static List<JButton> buttons = new ArrayList<>();
    private static boolean isNull = true;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Крестики-нолики");
        setButton(frame);
        frame.show();
    }

    private static void setButton(JFrame frame) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));
        frame.add(panel);

        for (int i = 0; i < 9; i++) {
            buttons.add(createButton(panel));
        }
    }

    private static JButton createButton(JPanel panel) {
        JButton button = new JButton(" ");
        button.setMinimumSize(new Dimension(100, 100));
        button.setFont(new Font("Arial", Font.BOLD, 30));
        button.addActionListener(e -> {
            setButtonsName(button);
            checkButtons();
        });
        panel.add(button);
        return button;
    }

    private static void setButtonsName(JButton button) {
        button.setText(isNull ? "O" : "X");
        isNull = !isNull;
    }

    private static boolean checkButtons() {
        String pathPython = "./test.py";
        String[] cmd = new String[5];
        cmd[0] = "python";
        cmd[1] = pathPython;
        cmd[2] = "arg1";
        cmd[3] = "arg2";
        cmd[4] = "arg3";
        Runtime r = Runtime.getRuntime();

        try {
            Process p = r.exec(cmd);
            String s;
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((s = in.readLine()) != null) {
                System.out.println(s);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}