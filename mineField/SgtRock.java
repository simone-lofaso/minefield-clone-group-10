package mineField;

import mvc.Model;

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
                }
            }
            case S -> {
                if(yPos != 19){
                    yPos++;
                }
            }
            case W -> {
                if(xPos != 0){
                    xPos--;
                }
            }
            case E -> {
                if(xPos != 19){
                    xPos++;
                }
            }
            case NW -> {
                if(yPos != 0 && xPos != 0){
                    xPos--;
                    yPos--;
                }
            }
            case NE -> {
                if(yPos != 0 && xPos != 19){
                    yPos--;
                    xPos++;
                }
            }
            case SE -> {
                if(yPos != 19 && xPos != 19){
                    yPos++;
                    xPos++;
                }
            }
            case SW -> {
                if(yPos != 19 && xPos != 0){
                    yPos++;
                    xPos--;
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
}
