package Pieces;

import Game.ChessBoard;
import Game.ChessPiece;

public class Queen extends ChessPiece {

    public Queen(Color color, String nameOnBoard, int row, int column) {
        super(color, nameOnBoard, row, column);
        super.setTypeOfPiece("Queen");
    }

    @Override
    public void availableMoves(ChessBoard chessBoard, int row, int column) {

        search(chessBoard, row, column+1, "right", true);
        search(chessBoard, row, column-1, "left", true);
        search(chessBoard, row -1, column, "top", true);
        search(chessBoard, row +1, column, "bottom", true);
        search(chessBoard, row - 1, column +1, "topRight", true);
        search(chessBoard, row -1, column -1, "topLeft", true);
        search(chessBoard, row +1, column +1, "bottomRight", true);
        search(chessBoard, row +1, column -1, "bottomLeft", true);

    }

}