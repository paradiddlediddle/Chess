package Pieces;

import Game.ChessBoard;
import Game.ChessPiece;

public class Rook extends ChessPiece {

    public Rook(Color color, String nameOnBoard, int row, int column) {
        super(color, nameOnBoard, row, column);
        super.setTypeOfPiece("Rook");
    }

    @Override
    public void availableMoves (ChessBoard chessBoard, int row, int column) {

        right(chessBoard, row, column+1);
        left(chessBoard, row, column-1);
        top (chessBoard, row-1, column);
        bottom (chessBoard, row+1, column);

    }


    private void right (ChessBoard chessBoard, int row, int column) {

        // Base Case: If the row/column is out of bounds or if the tile contains a piece of same colour, return without adding the position into the list of available moves
        if (row < 0 || row > 7 || column < 0 || column > 7  ||
                chessBoard.getBoard()[row][column].getColor() == getColor()) { return; }

        // If there is an opposite color piece available, we need to add it to the moveAndCapture list and return
        else if (chessBoard.getBoard()[row][column].getColor() != getColor()
                && chessBoard.getBoard()[row][column].getColor() != Color.NULL){
            setMoveAndCapture(new int[] {row, column});
            return;
        }

        else {
            setMove(new int[] {row, column});
            right(chessBoard, row, column+1);
        }
    }

    private void left (ChessBoard chessBoard, int row, int column) {

        // Base Case: If the row/column is out of bounds or if the tile contains a piece of same colour, return without adding the position into the list of available moves
        if (row < 0 || row > 7 || column < 0 || column > 7  ||
                chessBoard.getBoard()[row][column].getColor() == getColor()) { return; }

        // If there is an opposite color piece available, we need to add it to the moveAndCapture list and return
        else if (chessBoard.getBoard()[row][column].getColor() != getColor()
                && chessBoard.getBoard()[row][column].getColor() != Color.NULL){
            setMoveAndCapture(new int[] {row, column});
            return;
        }

        else {
            setMove(new int[] {row, column});
            left(chessBoard, row, column-1);
        }
    }

    private void top (ChessBoard chessBoard, int row, int column) {


        // Base Case: If the row/column is out of bounds or if the tile contains a piece of same colour, return without adding the position into the list of available moves
        if (row < 0 || row > 7 || column < 0 || column > 7  ||
                chessBoard.getBoard()[row][column].getColor() == getColor()) { return; }

        // If there is an opposite color piece available, we need to add it to the moveAndCapture list and return
        else if (chessBoard.getBoard()[row][column].getColor() != getColor()
                && chessBoard.getBoard()[row][column].getColor() != Color.NULL){
            setMoveAndCapture(new int[] {row, column});
            return;
        }

        else {
            setMove(new int[] {row, column});
            top(chessBoard, row-1, column);
        }
    }

    private void bottom (ChessBoard chessBoard, int row, int column) {


        // Base Case: If the row/column is out of bounds or if the tile contains a piece of same colour, return without adding the position into the list of available moves
        if (row < 0 || row > 7 || column < 0 || column > 7  ||
                chessBoard.getBoard()[row][column].getColor() == getColor()) { return; }

        // If there is an opposite color piece available, we need to add it to the moveAndCapture list and return
        else if (chessBoard.getBoard()[row][column].getColor() != getColor()
                && chessBoard.getBoard()[row][column].getColor() != Color.NULL){
            setMoveAndCapture(new int[] {row, column});
            return;
        }

        else {
            // Perform a DFS Search
            setMove(new int[] {row, column});
            bottom(chessBoard, row+1, column);
        }
    }


}
