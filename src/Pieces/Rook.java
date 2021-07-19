package Pieces;

import Game.ChessBoard;
import Game.ChessPiece;

public class Rook extends ChessPiece {

    public Rook(Color color, String nameOnBoard, int row, int column) {
        super(color, nameOnBoard, row, column);
        super.setTypeOfPiece("Rook");
    }

    /**
     * The Rook only moves vertically or horizontally
     */


    @Override
    public void availableMoves (ChessBoard chessBoard, int row, int column) {

        search(chessBoard, row, column+1, "right", true);
        search(chessBoard, row, column-1, "left", true);
        search(chessBoard, row -1, column, "top", true);
        search(chessBoard, row +1, column, "bottom", true);

    }



}
