/**
 * This class defines the cell object, which contains all the properties needed to create a single space
 * in the MineField
 *
 * boolean hasMine - true if the cell has a mine, otherwise false
 * boolean isVisited - true if the SgtRock object has been to that cell, false otherwise
 * int adjMines - the number of mines touching the indicated cell
 */
package mineField;

import java.awt.*;

public class Cell {
    public boolean hasMine; //true if the cell has a mine
    public boolean seen; //true if cell has been seen by Sgt Rock, false otherwise
    public int adjMines; //number of mines touching the indicated cell

    public Color color; //grey for unvisited, white for visited, green for win

    public Cell(){
        hasMine = false;
        seen = false;
        adjMines = 0;
        color = Color.gray;
    }
}
