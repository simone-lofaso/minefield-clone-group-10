package mineField;

/**
 * This class creates the SgtRock object, which the player will use to navigate the gameboard
 *
 * int xPos - the x coordinate of SgtRock's current position
 * int yPos - the y coordinate of SgtRock's current position
 *
 * NOTE FOR TEAM: SgtRock should just store its x and y coords since everything else should be handled in
 * MineField
 */
public class SgtRock {
    private int xPos;
    private int yPos;
    public SgtRock(){
        xPos = 0;
        yPos = 0;
    }
}
