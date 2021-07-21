package Game;

public class Player {

    // Player class represents the color of the player's piece.
    // Player number 1 or number 2


    private boolean isBlack;
    private int playerNumber;


    public Player (boolean isBlack) {
       this.isBlack = isBlack;
       if(isBlack) playerNumber = 2;
       else playerNumber = 1;
    }


    public boolean isBlack() { return isBlack; }

    public void setBlack(boolean black) { isBlack = black; }

    public int getPlayerNumber() { return playerNumber; }

    public void setPlayerNumber(int playerNumber) { this.playerNumber = playerNumber; }
}
