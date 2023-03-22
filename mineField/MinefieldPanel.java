package mineField;

import mvc.AppFactory;
import mvc.AppPanel;

import javax.swing.*;
import java.awt.*;

public class MinefieldPanel extends AppPanel{

        public MinefieldPanel(AppFactory factory) {
            super(factory);
            controlPanel.setLayout(new GridLayout(4, 2, 50, 50));
            String[] buttonNames = {"NW","N","NE","W","E","SW","S","SE"};
            for(int i = 0; i < buttonNames.length; i++) {
                JButton b = new JButton(buttonNames[i]);
                b.addActionListener(this);
                controlPanel.add(b);
            }
        }

        public static void main(String[] args) {
            AppFactory factory = new MinefieldFactory();
            AppPanel panel = new MinefieldPanel(factory);
            panel.display();
        }
}
