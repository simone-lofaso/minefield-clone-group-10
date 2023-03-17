/**
 * This class creates and populates the minefield grid using a 2D array of Cell objects.
 *
 * Cell[][] field - the 2D array of cells representing the MineField
 *
 * NOTE FOR TEAM: Please see line 23 for next steps
 */
package mineField;

import java.util.Random;

public class MineField {
    private static final int percentMined = 5;
    public Cell[][] field = new Cell[20][20];
    public MineField(){ //should be a 20x20 grid of Cells
        Random mineGen = new Random();
        for(int i = 0; i < field.length; i++){
            for (int j = 0; j < field[0].length; j++){
                int placeMine = mineGen.nextInt(100) + 1;
                if(placeMine <= percentMined){
                    field[i][j].hasMine = true;
                }
            }
        }
        /*use another nested for loop here to iterate through the grid after mines are generated and count
        neighboring mines to set adjMines for each cell :)
         */
    }
}
