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


    public void availableMoves(ChessBoard chessBoard, int row, int column, boolean kingSearch) {

        // Searches in all 8 direction to move. KeepSearching is set to "false", since the king just needs to move one step
        search(chessBoard, row, column + 1, "right", false, kingSearch);
        search(chessBoard, row, column - 1, "left", false, kingSearch);
        search(chessBoard, row - 1, column, "top", false, kingSearch);
        search(chessBoard, row + 1, column, "bottom", false, kingSearch);
        search(chessBoard, row - 1, column + 1, "topRight", false, kingSearch);
        search(chessBoard, row - 1, column - 1, "topLeft", false, kingSearch);
        search(chessBoard, row + 1, column + 1, "bottomRight", false, kingSearch);
        search(chessBoard, row + 1, column - 1, "bottomLeft", false, kingSearch);

        // Check for castling
        checkForCastling(chessBoard, row);

        // Call subtractMoves finally, need to work on the implementation
       if (!kingSearch) { subtractMovesThatCanBeAttacked(chessBoard);}
    }


    /**
     * Out of the possible positions in which the king can move, if an opponent can attack the king once it is moved
     * This functions checks if the King can be taken in the next step by any of its opponent pieces
     * If any of its opponents can take the king after its moved, that particular move is removed from the List.
     *
     *
     * CHECK MATE VS STALE MATE
     * When the king doesn't have any moves after subtracting the game will end in either check mate or stale mate
     * if the king is in check, it will be a check mate otherwise it is a stale mate
     */

    private void subtractMovesThatCanBeAttacked(ChessBoard chessBoard) {


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

        // check for all the pieces which are not null and opposite color
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if (this.getColor() != chessBoard.getBoard()[i][j].getColor()
                        && chessBoard.getBoard()[i][j].getColor() != Color.NULL) {

                    //Selects the opponent piece, generate the moves for it and stores it inside a list as strings
                    ChessPiece opponentPiece = chessBoard.getBoard()[i][j];

                    // Is the opponent piece a king we should just generate the opponent kings moves without excluding the vulnerable positions.
                    // Since excluding vulnerable positions would require the king to generate other pieces moves, if two kings come across each other
                    // They will keep generating moves for each other.
                    opponentPiece.availableMoves(chessBoard, i, j, true);
                    // else if pawn call a separate function


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

        // CHECK IF THE KING HAD MOVES BEFORE SUBTRACTING AND DOESN'T HAVE ANY MOVES AFTER SUBTRACTING

            // If the king had at least one move or one capture before subtracting the opponent moves
            if (this.getMove().size() > 0 || this.getMoveAndCapture().size() > 0) {

                // Doesn't have any move after subtracting
                if (kingCaptures.size() == 0 && kingsMoves.size() == 0) {

                    // GAME ENDS
                    // Set the game to false
                    // Game is going end either by "stale mate" or "check Mate"
                    Game.setGameActive(false);


                    // CHECK MATE IMPLEMENTATION:
                    // If the king is already in check, it is a checkMate

                    if (this.getKingIsInCheck()) {

                        // Black King taken down
                        if (this.isPieceBlack()) {

                            Game.setGameResult(Game.Status.WHITE_WIN);
                            Game.addPlayerMove("Check Mate! - White Wins!");
                            System.out.println("Check Mate! - White Wins!");

                        }
                        // White King taken down
                        else {

                            Game.setGameResult(Game.Status.BLACK_WIN);
                            Game.addPlayerMove("Check Mate! - Black Wins!");
                            System.out.println("Check Mate! - Black Wins!");

                        }
                    }

                    // STALE MATE IMPLEMENTATION
                    // If the king is not in check, it is a stale mate.

                    else {
                        Game.setGameResult(Game.Status.STALE_MATE);
                        Game.addPlayerMove("Stale Mate! - Draw!");
                        System.out.println("Stale Mate! - Draw!");
                    }

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

        // Adding back the updated moveAndCapture list

        for (String array : kingCaptures) {

            //[8, 9] >> 0 = [; 1 = 8; 2 = ,; 3 = " "; 4 = 9; 5 = ];
            // Hence character 1 and 4 from the string are taken
            // String to number conversion

            int row = Character.getNumericValue(array.charAt(1));
            int column = Character.getNumericValue(array.charAt(4));

            this.setMoveAndCapture(new int[]{row, column});

        }

    }


    /**
     * Only generates the move for castling, doesn't actually move the piece. The actual move is
     * done in the "movePiece" function in the game class.
     * Just row is received as a parameter, since the column can't be different if it is untouched.
     *
     * @param row - row of the king to determine whether it is a black or white king
     */

    private void checkForCastling(ChessBoard chessBoard, int row) {

        //if the king is untouched, the row and column will be the same, so we can use "row" in the if statement.
        if (isUntouched() && chessBoard.getBoard()[row][0].isUntouched() && chessBoard.getBoard()[row][1].getColor() == Color.NULL
                && chessBoard.getBoard()[row][2].getColor() == Color.NULL && chessBoard.getBoard()[row][3].getColor() == Color.NULL) {

            // Queen side castling can be done since both the king and queen side rook is untouched
            this.setMove(new int[]{row, 2});
            this.setCanCastle(true);
        }


        if (isUntouched() && chessBoard.getBoard()[row][7].isUntouched() && chessBoard.getBoard()[row][6].getColor() == Color.NULL
                && chessBoard.getBoard()[row][5].getColor() == Color.NULL) {

            // King side castling can be done since both the king and king side rook is untouched
            this.setMove(new int[]{row, 6});
            this.setCanCastle(true);
        }
    }



}