package Pieces;

import Game.ChessPiece;

public class NullPiece extends ChessPiece {

    /**
     * A null piece represents an empty spot on the board
     * Whenever a piece is moved on the board to a new position
     * A new null piece is created and set on the old position to represent an empty spot
     */


    public NullPiece (Color color, String nameOnBoard, int row, int column) {
        super(color, nameOnBoard, row, column);
        super.setTypeOfPiece("Empty Spot");
        this.setOnTheBoard(false); // Null piece is imaginary and it is not on the board
    }


}
