package Pieces;

import Game.ChessBoard;
import Game.ChessPiece;

public class Queen extends ChessPiece {

    public Queen(Color color, String nameOnBoard, int row, int column) {
        super(color, nameOnBoard, row, column);
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


    }


    private void right (ChessBoard chessBoard, int row, int column) {

        // Base Case: If the row/column is out of bounds or if the tile contains a piece of same colour, return without adding the position into the list of available moves
        if (row < 0 || row > 7 || column < 0 || column > 7 ||
                chessBoard.getBoard()[row][column] != null && chessBoard.getBoard()[row][column].isPieceBlack() == isPieceBlack()) { return; }

        // If there is an opposite color piece available, we need to add it to the moveAndCapture list and return
        else if (chessBoard.getBoard()[row][column] != null && chessBoard.getBoard()[row][column].isPieceBlack() != isPieceBlack()){
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
        if (row < 0 || row > 7 || column < 0 || column > 7 ||
                chessBoard.getBoard()[row][column] != null && chessBoard.getBoard()[row][column].isPieceBlack() == isPieceBlack()) { return; }

        // If there is an opposite color piece available, we need to add it to the moveAndCapture list and return
        else if (chessBoard.getBoard()[row][column] != null && chessBoard.getBoard()[row][column].isPieceBlack() != isPieceBlack()){
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
        if (row < 0 || row > 7 || column < 0 || column > 7 ||
                chessBoard.getBoard()[row][column] != null && chessBoard.getBoard()[row][column].isPieceBlack() == isPieceBlack()) { return; }

        // If there is an opposite color piece available, we need to add it to the moveAndCapture list and return
        else if (chessBoard.getBoard()[row][column] != null && chessBoard.getBoard()[row][column].isPieceBlack() != isPieceBlack()){
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
        if (row < 0 || row > 7 || column < 0 || column > 7 ||
                chessBoard.getBoard()[row][column] != null && chessBoard.getBoard()[row][column].isPieceBlack() == isPieceBlack()) { return; }

        // If there is an opposite color piece available, we need to add it to the moveAndCapture list and return
        else if (chessBoard.getBoard()[row][column] != null && chessBoard.getBoard()[row][column].isPieceBlack() != isPieceBlack()){
            setMoveAndCapture(new int[] {row, column});
            return;
        }

        else {
            setMove(new int[] {row, column});
            bottom(chessBoard, row+1, column);
        }
    }


    private void topRightDiagonal (ChessBoard chessBoard, int row, int column) {


        // Base Case: If the row/column is out of bounds or if the tile contains a piece of same colour, return without adding the position into the list of available moves
        if (row < 0 || row > 7 || column < 0 || column > 7 ||
                chessBoard.getBoard()[row][column] != null && chessBoard.getBoard()[row][column].isPieceBlack() == isPieceBlack()) { return; }

        // If there is an opposite color piece available, we need to add it to the moveAndCapture list and return
        else if (chessBoard.getBoard()[row][column] != null && chessBoard.getBoard()[row][column].isPieceBlack() != isPieceBlack()){
            setMoveAndCapture(new int[] {row, column});
            return;
        }

        else {
            setMove(new int[] {row, column});
            topRightDiagonal(chessBoard, row-1, column+1);
        }
    }


    private void topLeftDiagonal (ChessBoard chessBoard, int row, int column) {


        // Base Case: If the row/column is out of bounds or finds a same colour piece, return without adding the position into the list of available moves
        if (row < 0 || row > 7 || column < 0 || column > 7  ||
                chessBoard.getBoard()[row][column] != null && chessBoard.getBoard()[row][column].isPieceBlack() == isPieceBlack()) { return; }

        // If there is an opposite color piece available, we need to add it to the moveAndCapture list and return
        else if (chessBoard.getBoard()[row][column] != null && chessBoard.getBoard()[row][column].isPieceBlack() != isPieceBlack()){
            setMoveAndCapture(new int[] {row, column});
            return;
        }

        else {
            setMove(new int[] {row, column});
            topLeftDiagonal(chessBoard, row-1, column-1);
        }
    }

    private void bottomLeftDiagonal (ChessBoard chessBoard, int row, int column) {



        // Base Case: If the row/column is out of bounds or finds a same colour piece, return without adding the position into the list of available moves
        if (row < 0 || row > 7 || column < 0 || column > 7  ||
                chessBoard.getBoard()[row][column] != null && chessBoard.getBoard()[row][column].isPieceBlack() == isPieceBlack()) { return; }

        // If there is an opposite color piece available, we need to add it to the moveAndCapture list and return
        else if (chessBoard.getBoard()[row][column] != null && chessBoard.getBoard()[row][column].isPieceBlack() != isPieceBlack()){
            setMoveAndCapture(new int[] {row, column});
            return;
        }

        else {
            setMove(new int[] {row, column});
            bottomLeftDiagonal(chessBoard, row+1, column-1);
        }
    }

    private void bottomRightDiagonal (ChessBoard chessBoard, int row, int column) {


        // Base Case: If the row/column is out of bounds or finds a same colour piece, return without adding the position into the list of available moves
        if (row < 0 || row > 7 || column < 0 || column > 7  ||
                chessBoard.getBoard()[row][column] != null && chessBoard.getBoard()[row][column].isPieceBlack() == isPieceBlack()) { return; }

        // If there is an opposite color piece available, we need to add it to the moveAndCapture list and return
        else if (chessBoard.getBoard()[row][column] != null && chessBoard.getBoard()[row][column].isPieceBlack() != isPieceBlack()){
            setMoveAndCapture(new int[] {row, column});
            return;
        }

        else {
            setMove(new int[] {row, column});
            bottomRightDiagonal(chessBoard, row+1, column+1);
        }
    }

}