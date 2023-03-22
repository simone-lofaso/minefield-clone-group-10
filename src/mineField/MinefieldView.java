package mineField;

import mvc.View;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;


public class MinefieldView extends View {

    public MinefieldView(Minefield minefield){
        super(minefield);
        propertyChange(null);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt){

        setLayout(new GridLayout(20,20));
        setBackground(Color.GRAY);
        removeAll();

        Minefield minefield = (Minefield) model;
        for(int i = 0; i < minefield.getField().length; i++){
            for(int j = 0; j < minefield.getField()[i].length; j++){

                // sets up field 
                JLabel c = new JLabel();
                c.setBorder(BorderFactory.createLineBorder(Color.black));
                c.setText("?");

                // if already seen, border is white and displays number of mines nearby
                mineField.Cell cell = minefield.getField()[i][j];
                if(cell.seen && !cell.hasVisited){
                    c.setBorder(BorderFactory.createLineBorder(Color.gray));
                    c.setText(String.valueOf(minefield.getField()[i][j].adjMines));
                }
                if (cell.hasVisited){
                    c.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                }
                // exit cell has a green border
                if(i == 19 && j == 19){
                    c.setBorder(BorderFactory.createLineBorder(Color.green));
                }
                if (cell == minefield.getField()[0][0]){
                    c.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                }
                // cells with mines will update to show a red border after sgt rock is on it
                if(cell == minefield.getField()[minefield.userX()][minefield.userY()]){
                    c.setBorder(BorderFactory.createLineBorder(Color.blue));
                    if(cell.hasMine && !minefield.isGameDone()){
                        c.setBorder(BorderFactory.createLineBorder(Color.red));
                        JOptionPane.showMessageDialog(null, "You lose :(");
                        minefield.setGameDone(true);
                    }
                }
                if (minefield.getField()[minefield.userX()][minefield.userY()] == minefield.getField()[19][19] && !minefield.isGameDone()){
                    JOptionPane.showMessageDialog(null, "You win! :)");
                    minefield.setGameDone(true);
                }
                add(c);
            }
        }
    }
}