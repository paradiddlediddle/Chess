package Game;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private boolean isBlack;
    private List<Move> playerMoves = new ArrayList<>();
    private int playerNumber;


    public Player (boolean isBlack) {
       this.isBlack = isBlack;
       if(isBlack) playerNumber = 2;
       else playerNumber = 1;
    }


    public boolean isBlack() { return isBlack; }

    public void setBlack(boolean black) { isBlack = black; }

    public List<Move> getPlayerMoves() { return playerMoves; }

    public void addPlayerMoves(Move move) { this.playerMoves.add(move); }

    public int getPlayerNumber() { return playerNumber; }

    public void setPlayerNumber(int playerNumber) { this.playerNumber = playerNumber; }
}
