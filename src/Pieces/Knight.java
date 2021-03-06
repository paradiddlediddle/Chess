package Pieces;

import Game.ChessBoard;
import Game.ChessPiece;

public class Knight extends ChessPiece {

    public Knight(Color color, String nameOnBoard, int row, int column) {
        super(color, nameOnBoard, row, column);
        super.setTypeOfPiece("Knight");
    }

    /**
     * There are totally 16 different permutations in which the knight can be moved
     * However, out of those 16, 8 - permutations land up on the same position even though it travels in a different direction
     * Since only the possible moves at which the knight can move is required, we don't generate each permutation as its move
     */


    public void availableMoves(ChessBoard chessBoard, int row, int column, boolean kingSearch) {

        search(chessBoard, row-2, column+1, "shortRightTop",false, kingSearch);
        search(chessBoard, row+2, column+1, "shortRightBottom",false, kingSearch);
        search(chessBoard, row-1, column+2, "longRightTop",false, kingSearch);
        search(chessBoard, row+1, column+2, "longRightBottom",false, kingSearch);
        search(chessBoard, row-1, column-2, "longLeftTop",false, kingSearch);
        search(chessBoard, row+1, column-2, "longLeftBottom",false, kingSearch);
        search(chessBoard, row-2, column-1, "shortLeftTop",false, kingSearch);
        search(chessBoard, row+2, column-1, "shortLeftBottom",false, kingSearch);

    }

}
