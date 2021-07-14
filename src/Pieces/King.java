package Pieces;

import Game.ChessBoard;
import Game.ChessPiece;
import Game.Game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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

        // Check for castling
        checkForCastling(chessBoard, row);

        // Call subtractMoves finally
        // subtractMovesThatCanBeAttacked(chessBoard);

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


    private void subtractMovesThatCanBeAttacked (ChessBoard chessBoard) {

        // Storing all the kings moves in a Hashset
        HashSet<int[]> kingsMoves = new HashSet<>(this.getMove());
        HashSet<int[]> kingCaptures = new HashSet<>(this.getMoveAndCapture());

        // check for all the pieces which are not null and opposite color
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {

                if (this.getColor() != chessBoard.getBoard()[i][j].getColor()
                        && chessBoard.getBoard()[i][j].getColor() != Color.NULL ) {


                    ChessPiece opponentPiece = chessBoard.getBoard()[i][j];

                    //Generate moves for each opponent piece
                   opponentPiece.availableMoves(chessBoard, i, j);
                    List<int []> opponentsMoveList = opponentPiece.getMove();

                    // Just in case / to avoid opponent piece's generated moves from being retained


                    //
                    for (int k = 0; k < opponentPiece.getMove().size(); k++) {

                        // In case if the opponents move is present in the hashset, the kings move / captureMove will get removed
                       //  kingsMoves.remove(opponentPiece.getMove().get(i));
                        if (kingsMoves.contains(new int[]{opponentPiece.getMove().get(i)[0], opponentPiece.getMove().get(i)[1]})) {
                            kingsMoves.remove(new int[]{opponentPiece.getMove().get(i)[0], opponentPiece.getMove().get(i)[1]});
                        }


                       // kingCaptures.remove(opponentPiece.getMove().get(i));
                        if (kingCaptures.contains(new int[]{opponentPiece.getMove().get(i)[0], opponentPiece.getMove().get(i)[1]})) {
                            kingCaptures.remove(new int[]{opponentPiece.getMove().get(i)[0], opponentPiece.getMove().get(i)[1]});
                        }
                    }
                    opponentPiece.clearList();
                }
            }
        }

        // If the king had moves initially and then doesn't have any moves after subtracting other pieces possible moves, it is a staleMate

        if (this.getMove().size() > 0 && this.getMoveAndCapture().size() > 0
                && kingCaptures.size() == 0 && kingsMoves.size() == 0) {

            // StaleMate
            // How to stop the game?
            Game.setGameActive(false);
            Game.setGameResult(Game.Status.STALE_MATE);
            System.out.println("Stale Mate! No valid moves available");
            Game.addPlayerMove("Stale Mate! No valid moves available");

        }

        // Clear the kings existing moves list to avoid duplicates
        this.getMove().clear();

        // Add remaining kings move in the hashset back into the kings actual list
        for (int [] array : kingsMoves) { this.setMove(array); }

    }




    // Only generate the move for castling
    // Just row is received as a parameter, since the column can't be different if it is untouched.
    private void checkForCastling (ChessBoard chessBoard, int row) {

        //if the king is untouched, the row and column will be the same, so we can use "row" in the if statement.
        if (isUntouched() && chessBoard.getBoard()[row][0].isUntouched() && chessBoard.getBoard()[row][1].getColor() == Color.NULL
        && chessBoard.getBoard()[row][2].getColor() == Color.NULL && chessBoard.getBoard()[row][3].getColor() == Color.NULL) {

            // Queen side castling can be done since both the king and queen side rook is untouched
            this.setMove(new int[] {row, 2});
            this.setCanCastle(true);
        }




        if (isUntouched() && chessBoard.getBoard()[row][7].isUntouched() && chessBoard.getBoard()[row][6].getColor() == Color.NULL
                && chessBoard.getBoard()[row][5].getColor() == Color.NULL) {

            // King side castling can be done since both the king and king side rook is untouched
            this.setMove(new int[] {row, 6});
            this.setCanCastle(true);
        }
    }


}
