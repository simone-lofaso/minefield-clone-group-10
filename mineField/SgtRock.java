package mineField;

import mvc.Model;

import java.awt.*;

/**
 * This class creates the SgtRock object, which the player will use to navigate the gameboard
 *
 * int xPos - the x coordinate of SgtRock's current position
 * int yPos - the y coordinate of SgtRock's current position
 *
 * NOTE FOR TEAM: SgtRock should just store its x and y coords since everything else should be handled in
 * MineField
 */
public class SgtRock extends Model {
    private int xPos;
    private int yPos;

    private boolean gameDone;
    public SgtRock(){
        xPos = 0;
        yPos = 0;
        gameDone = false;
    }

    //updates board for every change
    private void updateNeighbors(){
        MineField.field[xPos][yPos].color = Color.white;
        if (yPos > 0){ //North tile
            MineField.field[xPos][yPos - 1].seen = true;
            if(MineField.field[xPos][yPos - 1].color != Color.gray) {
                MineField.field[xPos][yPos - 1].color = Color.gray;
            }
        }
        if (yPos < 19){ //South Tile
            MineField.field[xPos][yPos + 1].seen = true;
            if(MineField.field[xPos][yPos + 1].color != Color.gray) {
                MineField.field[xPos][yPos + 1].color = Color.gray;
            }
        }
        if (xPos > 0){//West Tile
            MineField.field[xPos - 1][yPos].seen = true;
            if(MineField.field[xPos - 1][yPos].color != Color.gray) {
                MineField.field[xPos - 1][yPos].color = Color.gray;
            }
        }
        if (xPos < 19){//East Tile
            MineField.field[xPos + 1][yPos].seen = true;
            if(MineField.field[xPos + 1][yPos].color != Color.gray) {
                MineField.field[xPos + 1][yPos].color = Color.gray;
            }
        }
        if (xPos > 0 && yPos > 0){ //NW
            MineField.field[xPos - 1][yPos - 1].seen = true;
            if(MineField.field[xPos - 1][yPos - 1].color != Color.gray) {
                MineField.field[xPos - 1][yPos - 1].color = Color.gray;
            }
        }
        if (xPos < 19 && yPos > 0){ //NE
            MineField.field[xPos + 1][yPos - 1].seen = true;
            if(MineField.field[xPos + 1][yPos - 1].color != Color.gray) {
                MineField.field[xPos + 1][yPos - 1].color = Color.gray;
            }
        }
        if (xPos > 0 && yPos < 19){ //SW
            MineField.field[xPos - 1][yPos + 1].seen = true;
            if(MineField.field[xPos - 1][yPos + 1].color != Color.gray) {
                MineField.field[xPos - 1][yPos + 1].color = Color.gray;
            }
        }
        if (xPos < 19 && yPos < 19){ //SW
            MineField.field[xPos + 1][yPos + 1].seen = true;
            if(MineField.field[xPos + 1][yPos + 1].color != Color.gray) {
                MineField.field[xPos + 1][yPos + 1].color = Color.gray;
            }
        }
    }



    public boolean isGameDone() {
        return gameDone;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void move(Heading direction){
        switch(direction){
            case N -> {
                if(yPos != 0){
                    yPos--;
                    updateNeighbors();
                }
            }
            case S -> {
                if(yPos != 19){
                    yPos++;
                    updateNeighbors();
                }
            }
            case W -> {
                if(xPos != 0){
                    xPos--;
                    updateNeighbors();
                }
            }
            case E -> {
                if(xPos != 19){
                    xPos++;
                    updateNeighbors();
                }
            }
            case NW -> {
                if(yPos != 0 && xPos != 0){
                    xPos--;
                    yPos--;
                    updateNeighbors();
                }
            }
            case NE -> {
                if(yPos != 0 && xPos != 19){
                    yPos--;
                    xPos++;
                    updateNeighbors();
                }
            }
            case SE -> {
                if(yPos != 19 && xPos != 19){
                    yPos++;
                    xPos++;
                    updateNeighbors();
                }
            }
            case SW -> {
                if(yPos != 19 && xPos != 0){
                    yPos++;
                    xPos--;
                    updateNeighbors();
                }
            }
        }
        changed(); //I THINK this updates the model, might not be needed
        //win condition
        if(xPos == 19 && yPos == 19){
            gameDone = true;
        }
        //lose condition
        if(MineField.getHasMine(xPos, yPos)){
            gameDone = true;
        }
    }

    //resetting player position to use for new game
    public void resetSgt() {
        xPos=0;
        yPos=0;
        gameDone=false;
    }
}
