package Pieces;

import Game.ChessBoard;
import Game.ChessPiece;
import Game.Game;

import java.util.ArrayList;
import java.util.Arrays;
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

        // Call subtractMoves finally, need to work on the implementation
         subtractMovesThatCanBeAttacked(chessBoard);

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

    // This functions checks if the King can be taken in the next step by any of its opponent pieces
    // If any of its opponents can take the king after its moved, that particular move is removed from the List.
    private void subtractMovesThatCanBeAttacked (ChessBoard chessBoard) {


        // List to store the kings moves and captures in the form of a string
        ArrayList<String> kingsMoves = new ArrayList<>();
        ArrayList<String> kingCaptures = new ArrayList<>();

        // Storing the List of kings moves in an Array List in the form of "Strings"
        for (int[] array : this.getMove()) {
            kingsMoves.add(Arrays.toString(array));
        }
        for (int[] array : this.getMoveAndCapture()) {
            kingCaptures.add(Arrays.toString(array));
        }


        //WHY DOES THIS LOOP KEEP REPEATING ITSELF ???

        // check for all the pieces which are not null and opposite color
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {

                if (this.getColor() != chessBoard.getBoard()[i][j].getColor()
                        && chessBoard.getBoard()[i][j].getColor() != Color.NULL) {

                    //Selects the opponent piece, generate the moves for it and stores it inside a list as strings
                    ChessPiece opponentPiece = chessBoard.getBoard()[i][j];
                    opponentPiece.availableMoves(chessBoard, i, j);
                    List<String> opponentsMoveListInString = new ArrayList<>();


                    // Opponent moves are converted into a string and added to the List
                    for (int[] array : opponentPiece.getMove()) {
                        opponentsMoveListInString.add(Arrays.toString(array));
                    }


                    for (int k = 0; k < opponentPiece.getMove().size(); k++) {

                        // In case if the opponents move is present in the hashset, the kings move / captureMove will get removed

                        if (kingsMoves.contains(opponentsMoveListInString.get(k))) {
                            kingsMoves.remove(opponentsMoveListInString.get(k));
                        }


                        if (kingCaptures.contains(opponentsMoveListInString.get(k))) {
                            kingCaptures.remove(opponentsMoveListInString.get(k));
                        }

                    }
                    // Clears the opponents available moves after checking all the opponent piece moves with king's
                    opponentPiece.clearList();
                }
            }
        }

        /**STALE MATE IMPLEMENTATION
         * If the king had moves initially and then doesn't have any moves after subtracting other pieces possible moves,
         * it is a staleMate
         */
        // If the king at least had one move or one capture
        if (this.getMove().size() > 0 || this.getMoveAndCapture().size() > 0) {
            // And doesn't have any after subtracting the opponents move, it is a stale mate

            if (kingCaptures.size() == 0 && kingsMoves.size() == 0) {

                Game.setGameActive(false);
                Game.setGameResult(Game.Status.STALE_MATE);
                Game.addPlayerMove("Match Draw - Stale Mate");
                System.out.println("Match Draw - Stale Mate!");
            }

        }


        // Clears the kings existing moves and "movesAndCaptures" list to avoid duplicates
        this.clearList();

        // Adding the updated kings move list back
        for (String array : kingsMoves) {

            //[8, 9] >> 0 = [; 1 = 8; 2 = ,; 3 = " "; 4 = 9; 5 = ];
            // Hence character 1 and 4 from the string are taken
            // String to number conversion

            int row = Character.getNumericValue(array.charAt(1));
            int column = Character.getNumericValue(array.charAt(4));

            this.setMove(new int[]{row, column});

        }

        // Adding back Move and capture list

        for (String array : kingCaptures) {

            //[8, 9] >> 0 = [; 1 = 8; 2 = ,; 3 = " "; 4 = 9; 5 = ];
            // Hence character 1 and 4 from the string are taken
            // String to number conversion

            int row = Character.getNumericValue(array.charAt(1));
            int column = Character.getNumericValue(array.charAt(4));

            this.setMoveAndCapture(new int[]{row, column});

        }

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
