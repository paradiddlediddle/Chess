package Pieces;

import Game.ChessPiece;

public class NullPiece extends ChessPiece {

    public NullPiece (Color color, String nameOnBoard, int row, int column) {
        super(color, nameOnBoard, row, column);
        super.setTypeOfPiece("Empty Spot");
    }


}
