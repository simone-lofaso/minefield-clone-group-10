package mineField;

import javax.swing.*;
import java.awt.*;

/**
 * Note for team: this should probably extend view instead of jpanel, but I am not sure. Please
 * investigate and let me know when/if you change it
 */
public class MinefieldView extends JPanel {
    public MinefieldView() {

        setBackground(Color.GRAY);
        setLayout(new GridLayout(20, 20));
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                JLabel cell = new JLabel();
                cell.setBackground(Color.gray);
                cell.setBorder(BorderFactory.createLineBorder(Color.black));
                cell.setText("?");

                //exit cell has a green border
                if (i == 19 && j == 19){
                    cell.setBorder(BorderFactory.createLineBorder(Color.green));
                }
                add(cell);
            }
        }
    }
}
