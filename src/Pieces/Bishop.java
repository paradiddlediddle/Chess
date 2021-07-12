package Pieces;

import Game.ChessBoard;
import Game.ChessPiece;
import Game.Player;

public class Bishop extends ChessPiece {

    public Bishop(Color color, String nameOnBoard, int row, int column) {
        super(color, nameOnBoard, row, column);
    }


    // Override
    public void availableMoves (ChessBoard chessBoard, int row, int column ) {

            topRightDiagonal (chessBoard, row-1, column+1); // Top right
            topLeftDiagonal (chessBoard, row-1, column-1); // Top Left
            bottomLeftDiagonal (chessBoard, row+1, column-1); // Bottom Left
            bottomRightDiagonal (chessBoard, row +1, column+1); // Bottom Right

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
