/**
 * This class creates and populates the minefield grid using a 2D array of Cell objects.
 *
 * Cell[][] field - the 2D array of cells representing the MineField
 *
 * NOTE FOR TEAM: Please see line 23 for next steps
 */
package mineField;

import mvc.*;

import java.awt.*;
import java.util.Random;

public class Minefield extends Model{

    // initially from SgtRock
    private int xPos;
    private int yPos;
    private boolean gameDone;

    private static final int percentMined = 5;
    public static Cell[][] field;
    public Minefield(){
        field = new Cell[20][20];
        Random mineGen = new Random();

        //from SgtRock
        xPos = 0;
        yPos = 0;
        gameDone = false;

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

        field[0][0].seen = true;
        field[0][1].seen = true;
        field[0][1].color = Color.gray;
        field[1][0].seen = true;
        field[1][0].color = Color.gray;
        field[1][1].seen = true;
        field[1][1].color = Color.gray;

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
    private void updateNeighbors(){
        Minefield.field[xPos][yPos].color = Color.white;
        if (yPos > 0){ //North tile
            Minefield.field[xPos][yPos - 1].seen = true;
            if(Minefield.field[xPos][yPos - 1].color != Color.gray) {
                Minefield.field[xPos][yPos - 1].color = Color.gray;
            }
        }
        if (yPos < 19){ //South Tile
            Minefield.field[xPos][yPos + 1].seen = true;
            if(Minefield.field[xPos][yPos + 1].color != Color.gray) {
                Minefield.field[xPos][yPos + 1].color = Color.gray;
            }
        }
        if (xPos > 0){//West Tile
            Minefield.field[xPos - 1][yPos].seen = true;
            if(Minefield.field[xPos - 1][yPos].color != Color.gray) {
                Minefield.field[xPos - 1][yPos].color = Color.gray;
            }
        }
        if (xPos < 19){//East Tile
            Minefield.field[xPos + 1][yPos].seen = true;
            if(Minefield.field[xPos + 1][yPos].color != Color.gray) {
                Minefield.field[xPos + 1][yPos].color = Color.gray;
            }
        }
        if (xPos > 0 && yPos > 0){ //NW
            Minefield.field[xPos - 1][yPos - 1].seen = true;
            if(Minefield.field[xPos - 1][yPos - 1].color != Color.gray) {
                Minefield.field[xPos - 1][yPos - 1].color = Color.gray;
            }
        }
        if (xPos < 19 && yPos > 0){ //NE
            Minefield.field[xPos + 1][yPos - 1].seen = true;
            if(Minefield.field[xPos + 1][yPos - 1].color != Color.gray) {
                Minefield.field[xPos + 1][yPos - 1].color = Color.gray;
            }
        }
        if (xPos > 0 && yPos < 19){ //SW
            Minefield.field[xPos - 1][yPos + 1].seen = true;
            if(Minefield.field[xPos - 1][yPos + 1].color != Color.gray) {
                Minefield.field[xPos - 1][yPos + 1].color = Color.gray;
            }
        }
        if (xPos < 19 && yPos < 19){ //SW
            Minefield.field[xPos + 1][yPos + 1].seen = true;
            if(Minefield.field[xPos + 1][yPos + 1].color != Color.gray) {
                Minefield.field[xPos + 1][yPos + 1].color = Color.gray;
            }
        }
    }

    public boolean isGameDone() {
        return gameDone;
    }

    public void move(String direction){
        if (gameDone){
            return;
        }
        switch(direction){
            case "N" -> {
                if(xPos != 0){
                    xPos--;
                    updateNeighbors();
                }
            }
            case "S" -> {
                if(xPos != 19){
                    xPos++;
                    updateNeighbors();
                }
            }
            case "W" -> {
                if(yPos != 0){
                    yPos--;
                    updateNeighbors();
                }
            }
            case "E" -> {
                if(yPos != 19){
                    yPos++;
                    updateNeighbors();
                }
            }//here
            case "NW" -> {
                if(yPos != 0 && xPos != 0){
                    yPos--;
                    xPos--;
                    updateNeighbors();
                }
            }
            case "NE" -> {
                if(xPos != 0 && yPos != 19){
                    xPos--;
                    yPos++;
                    updateNeighbors();
                }
            }
            case "SE" -> {
                if(xPos != 19 && yPos != 19){
                    xPos++;
                    yPos++;
                    updateNeighbors();
                }
            }
            case "SW" -> {
                if(xPos != 19 && yPos != 0){
                    xPos++;
                    yPos--;
                    updateNeighbors();
                }
            }
        }
        if (xPos != 19 && yPos != 19){
            Minefield.field[xPos][yPos].hasVisited = true;
        }
        //might remove
        changed(); //I THINK this updates the model, might not be needed
        //win condition
        if(xPos == 19 && yPos == 19){
            gameDone = true;
        }
        //lose condition
        if(Minefield.getHasMine(xPos, yPos)){
            gameDone = true;
        }
    }

    public void setGameDone(boolean gameDone) {
        this.gameDone = gameDone;
    }

    public static boolean getHasMine(int x, int y){
        return field[x][y].hasMine;
    }

    // from SgtRock
    /**private void updateNeighbors(){
        Minefield.field[xPos][yPos].color = Color.white;
        if (yPos > 0){ //North tile
            Minefield.field[xPos][yPos - 1].seen = true;
            if(Minefield.field[xPos][yPos - 1].color != Color.gray) {
                Minefield.field[xPos][yPos - 1].color = Color.gray;
            }
        }
        if (yPos < 19){ //South Tile
            Minefield.field[xPos][yPos + 1].seen = true;
            if(Minefield.field[xPos][yPos + 1].color != Color.gray) {
                Minefield.field[xPos][yPos + 1].color = Color.gray;
            }
        }
        if (xPos > 0){//West Tile
            Minefield.field[xPos - 1][yPos].seen = true;
            if(Minefield.field[xPos - 1][yPos].color != Color.gray) {
                Minefield.field[xPos - 1][yPos].color = Color.gray;
            }
        }
        if (xPos < 19){//East Tile
            Minefield.field[xPos + 1][yPos].seen = true;
            if(Minefield.field[xPos + 1][yPos].color != Color.gray) {
                Minefield.field[xPos + 1][yPos].color = Color.gray;
            }
        }
        if (xPos > 0 && yPos > 0){ //NW
            Minefield.field[xPos - 1][yPos - 1].seen = true;
            if(Minefield.field[xPos - 1][yPos - 1].color != Color.gray) {
                Minefield.field[xPos - 1][yPos - 1].color = Color.gray;
            }
        }
        if (xPos < 19 && yPos > 0){ //NE
            Minefield.field[xPos + 1][yPos - 1].seen = true;
            if(Minefield.field[xPos + 1][yPos - 1].color != Color.gray) {
                Minefield.field[xPos + 1][yPos - 1].color = Color.gray;
            }
        }
        if (xPos > 0 && yPos < 19){ //SW
            Minefield.field[xPos - 1][yPos + 1].seen = true;
            if(Minefield.field[xPos - 1][yPos + 1].color != Color.gray) {
                Minefield.field[xPos - 1][yPos + 1].color = Color.gray;
            }
        }
        if (xPos < 19 && yPos < 19){ //SW
            Minefield.field[xPos + 1][yPos + 1].seen = true;
            if(Minefield.field[xPos + 1][yPos + 1].color != Color.gray) {
                Minefield.field[xPos + 1][yPos + 1].color = Color.gray;
            }
        }
    }**/

    /*
    // some changes here so sgt rock moves in the right direction
    void move(String heading) {
        if(!gameDone){
            switch (heading) {
                case "S" ->{
                    xPos++;
                    //updateNeighbors();
                }
                case "W" -> {
                    yPos--;
                    //updateNeighbors();
                }
                case "E" -> {
                    yPos++;
                    //updateNeighbors();
                }
                case "N" -> {
                    xPos--;
                    //updateNeighbors();
                }
                case "NW" -> {
                    yPos--;
                    xPos--;
                    //updateNeighbors();
                }
                case "NE" -> {
                    yPos++;
                    xPos--;
                    //updateNeighbors();
                }
                case "SW" -> {
                    yPos--;
                    xPos++;
                    //updateNeighbors();
                }
                case "SE" -> {
                    yPos++;
                    xPos++;
                    //updateNeighbors();
                }
            }

            try{
                field[xPos][yPos].seen = true;
            }catch(Exception e){
                Utilities.error("Out of bounds");
            }

            // lose message
            if(field[xPos][yPos].hasMine){
                Utilities.error("You died :(");
                gameDone = true;
            }
            // win message
            if(xPos == 19 && yPos == 19){
                Utilities.inform("Congratulations! You win!");
            }
            changed();
        }
    }

     */

    public void test(){
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                System.out.print(field[i][j].adjMines + " ");
            }
            System.out.println();
        }
    }

    Cell[][] getField(){return field;}
    int userX(){return xPos;}
    int userY(){return yPos;}
}
