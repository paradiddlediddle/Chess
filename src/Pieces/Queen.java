package Pieces;

import Game.ChessBoard;
import Game.ChessPiece;

public class Queen extends ChessPiece {

    public Queen(Color color, String nameOnBoard, int row, int column) {
        super(color, nameOnBoard, row, column);
        super.setTypeOfPiece("Queen");
    }


    public void availableMoves(ChessBoard chessBoard, int row, int column, boolean kingSearch) {

        search(chessBoard, row, column+1, "right", true, kingSearch);
        search(chessBoard, row, column-1, "left", true, kingSearch);
        search(chessBoard, row -1, column, "top", true, kingSearch);
        search(chessBoard, row +1, column, "bottom", true, kingSearch);
        search(chessBoard, row - 1, column +1, "topRight", true, kingSearch);
        search(chessBoard, row -1, column -1, "topLeft", true, kingSearch);
        search(chessBoard, row +1, column +1, "bottomRight", true, kingSearch);
        search(chessBoard, row +1, column -1, "bottomLeft", true, kingSearch);

    }

}