package Pieces;

import Game.ChessBoard;
import Game.ChessPiece;
import Game.Player;

public class Bishop extends ChessPiece {

    public Bishop(Color color, String nameOnBoard, int row, int column) {
        super(color, nameOnBoard, row, column);
        super.setTypeOfPiece("Bishop");
    }

    /**
     * The Bishop Keeps searching on all the diagonals to see if it can attack an opponent piece
     * Keeps searching is set as true to perform recursive search
     */
    

    // Override
    public void availableMoves (ChessBoard chessBoard, int row, int column, boolean kingSearch ) {

        search(chessBoard, row - 1, column +1, "topRight", true, false);
        search(chessBoard, row -1, column -1, "topLeft", true, false);
        search(chessBoard, row +1, column +1, "bottomRight", true, false);
        search(chessBoard, row +1, column -1, "bottomLeft", true, false);

    }


}
