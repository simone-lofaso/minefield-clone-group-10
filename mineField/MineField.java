/**
 * This class creates and populates the minefield grid using a 2D array of Cell objects.
 *
 * Cell[][] field - the 2D array of cells representing the MineField
 *
 * NOTE FOR TEAM: Please see line 23 for next steps
 */
package mineField;

import java.awt.*;
import java.util.Random;

public class MineField {
    private static final int percentMined = 5;
    public Cell[][] field = new Cell[20][20];
    public MineField(){ //should be a 20x20 grid of Cells
        Random mineGen = new Random();
        for(int i = 0; i < field.length; i++){
            for (int j = 0; j < field[0].length; j++){
                field[i][j] = new Cell();
                int placeMine = mineGen.nextInt(100) + 1;
                if(placeMine <= percentMined){
                    field[i][j].hasMine = true;
                }
            }
        }
        field[0][0].hasMine = false;
        field[19][19].hasMine = false;
        field[19][19].color = Color.green;
        /*use another nested for loop here to iterate through the grid after mines are generated and count
        neighboring mines to set adjMines for each cell :)
         */
        //for loop to check for adj mines - non-inclusive of border cells
        for(int i = 1; i < field.length - 1; i++){
            for(int j = 1; j < field.length - 1; j++){
                //check diagonals for mines
                if(field[i-1][j-1].hasMine){
                    field[i][j].adjMines++;
                }
                if(field[i+1][j-1].hasMine){
                    field[i][j].adjMines++;
                }
                if(field[i-1][j+1].hasMine){
                    field[i][j].adjMines++;
                }
                if(field[i+1][j+1].hasMine){
                    field[i][j].adjMines++;
                }
                //check cardinal points for mines
                if(field[i][j+1].hasMine){
                    field[i][j].adjMines++;
                }
                if(field[i][j-1].hasMine){
                    field[i][j].adjMines++;
                }
                if(field[i+1][j].hasMine){
                    field[i][j].adjMines++;
                }
                if(field[i-1][j].hasMine){
                    field[i][j].adjMines++;
                }
            }
        }
        //for loop to check top border cells for adj mines - non-inclusive of corners
        for(int i = 1; i < field.length - 1; i++){
            //check diagonals
            if(field[i-1][1].hasMine){
                field[i][0].adjMines++;
            }
            if(field[i+1][1].hasMine){
                field[i][0].adjMines++;
            }
            //check sides
            if(field[i+1][0].hasMine){
                field[i][0].adjMines++;
            }
            if(field[i-1][0].hasMine){
                field[i][0].adjMines++;
            }
            //check center
            if(field[i][1].hasMine){
                field[i][0].adjMines++;
            }
        }
        //for loop to check bottom row for adj mines
        for(int i = 1; i < field.length - 2; i++){
            //check diagonals
            if(field[i-1][field.length - 2].hasMine){
                field[i][field.length - 1].adjMines++;
            }
            if(field[i+1][field.length - 1].hasMine){
                field[i][field.length - 1].adjMines++;
            }
            //check sides
            if(field[i-1][field.length - 1].hasMine){
                field[i][field.length - 1].adjMines++;
            }
            if(field[i+1][field.length - 1].hasMine){
                field[i][field.length - 1].adjMines++;
            }
            //check center
            if(field[i][field.length - 2].hasMine){
                field[i][field.length - 1].adjMines++;
            }
        }
        //for loop to check west side for adj mines
        for(int i = 1; i < field.length - 2; i++){
            //check diagonals
            if(field[1][i+1].hasMine){
                field[0][i].adjMines++;
            }
            if(field[1][i-1].hasMine){
                field[0][i].adjMines++;
            }
            //check north and south
            if(field[0][i-1].hasMine){
                field[0][i].adjMines++;
            }
            if(field[0][i+1].hasMine){
                field[0][i].adjMines++;
            }
            //check center
            if(field[1][i].hasMine){
                field[0][i].adjMines++;
            }
        }
        //for loop to check east side for adj mines
        for(int i = 1; i < field.length - 2; i++){
            //check diagonals
            if(field[field.length - 1][i+1].hasMine){
                field[field.length - 1][i].adjMines++;
            }
            if(field[field.length - 2][i-1].hasMine){
                field[field.length - 1][i].adjMines++;
            }
            //check north and south
            if(field[field.length - 1][i-1].hasMine){
                field[field.length - 1][i].adjMines++;
            }
            if(field[field.length - 1][i+1].hasMine){
                field[field.length - 1][i].adjMines++;
            }
            //check center
            if(field[field.length - 2][i].hasMine){
                field[field.length - 1][i].adjMines++;
            }
        }
        //checks for nw corner
        if(field[1][0].hasMine){
            field[0][0].adjMines++;
        }
        if(field[1][1].hasMine){
            field[0][0].adjMines++;
        }
        if(field[0][1].hasMine){
            field[0][0].adjMines++;
        }
        //checks for ne corner
        if(field[field.length - 2][0].hasMine){
            field[field.length - 1][0].adjMines++;
        }
        if(field[field.length - 2][1].hasMine){
            field[field.length - 1][0].adjMines++;
        }
        if(field[field.length - 1][1].hasMine){
            field[field.length - 1][0].adjMines++;
        }
        //checks for SW corner
        if(field[1][field.length - 1].hasMine){
            field[0][field.length - 1].adjMines++;
        }
        if(field[1][field.length - 2].hasMine){
            field[0][field.length - 1].adjMines++;
        }
        if(field[0][field.length - 2].hasMine){
            field[0][field.length - 1].adjMines++;
        }
        //checks for SE corner
        if(field[field.length - 2][field.length - 2].hasMine){
            field[field.length - 1][field.length - 1].adjMines++;
        }
        if(field[field.length - 2][field.length - 1].hasMine){
            field[field.length - 1][field.length - 2].adjMines++;
        }
        if(field[field.length - 1][field.length - 2].hasMine){
            field[field.length - 1][field.length - 1].adjMines++;
        }
    }
    public void test(){
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                System.out.print(field[i][j].adjMines + " ");
            }
            System.out.println();
        }
    }
}
