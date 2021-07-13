package Pieces;

import Game.ChessBoard;
import Game.ChessPiece;

public class King extends ChessPiece {

    public King(Color color, String nameOnBoard, int row, int column) {
        super(color, nameOnBoard, row, column);
        super.setTypeOfPiece("King");
    }

    @Override
    public void availableMoves(ChessBoard chessBoard, int row, int column) {

        right(chessBoard, row, column+1);
        left(chessBoard, row, column-1);
        top (chessBoard, row-1, column);
        bottom (chessBoard, row+1, column);
        topRightDiagonal (chessBoard, row-1, column+1);
        topLeftDiagonal (chessBoard, row-1, column-1);
        bottomLeftDiagonal (chessBoard, row+1, column-1);
        bottomRightDiagonal (chessBoard, row +1, column+1);

        // NEED TO IMPLEMENT KING CASTLING AS ON OF THE AVAILABLE MOVES

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
        }
    }

    private void top (ChessBoard chessBoard, int row, int column) {

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
            setMove(new int[] {row, column});
        }
    }


    private void topRightDiagonal (ChessBoard chessBoard, int row, int column) {


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
        }
    }


    private void topLeftDiagonal (ChessBoard chessBoard, int row, int column) {


        // Base Case: If the row/column is out of bounds or finds a same colour piece, return without adding the position into the list of available moves
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
        }
    }

    private void bottomLeftDiagonal (ChessBoard chessBoard, int row, int column) {



        // Base Case: If the row/column is out of bounds or finds a same colour piece, return without adding the position into the list of available moves
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
        }
    }

    private void bottomRightDiagonal (ChessBoard chessBoard, int row, int column) {


        // Base Case: If the row/column is out of bounds or finds a same colour piece, return without adding the position into the list of available moves
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
        }
    }


    private void castling (ChessBoard chessBoard, int row, int column) {
        if (isUntouched() && chessBoard.getBoard()[7][0].isUntouched() && chessBoard.getBoard()[7][1].getColor() == Color.NULL
        && chessBoard.getBoard()[7][2].getColor() == Color.NULL && chessBoard.getBoard()[7][3].getColor() == Color.NULL) {
            // Queen side castling can be done
        }
        if (isUntouched() && chessBoard.getBoard()[7][7].isUntouched() && chessBoard.getBoard()[7][6].getColor() == Color.NULL
                && chessBoard.getBoard()[7][5].getColor() == Color.NULL) {
            // King side castling can be done
        }
    }


}
