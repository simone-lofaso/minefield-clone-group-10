/**
 * This class defines the cell object, which contains all the properties needed to create a single space
 * in the MineField
 *
 * boolean hasMine - true if the cell has a mine, otherwise false
 * boolean isVisited - true if the SgtRock object has been to that cell, false otherwise
 * int adjMines - the number of mines touching the indicated cell
 */
package mineField;

public class Cell {
    public boolean hasMine; //true if the cell has a mine
    public boolean isVisited; //true if cell has been seen by Sgt Rock, false otherwise
    public int adjMines; //number of mines touching the indicated cell

    public Cell(){
        hasMine = false;
        isVisited = false;
        adjMines = 0;
    }
}
