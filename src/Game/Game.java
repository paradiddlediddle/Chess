package Game;

import java.util.Scanner;

public class Game {

ChessBoard chessBoard = new ChessBoard();
Player player1 = new Player(false);
Player player2 = new Player(true);





public ChessPiece choosePiece () {
    Scanner pieceScanner = new Scanner(System.in);
    boolean isInvalidEntry = true;
    ChessPiece selectedPiece = null;

    while (isInvalidEntry) {

        // try and catch
        System.out.println("Please enter your selection");
        String userPieceSelection = pieceScanner.nextLine();
                userPieceSelection.trim().toLowerCase();

        // The user has to select a piece within the board
        if (!(userPieceSelection.charAt(0) >= 97 && userPieceSelection.charAt(0) <= 104) ||
                !(userPieceSelection.charAt(1) >= 49 && userPieceSelection.charAt(1) <= 56) ||
                !(userPieceSelection.length() == 2) ) {
            System.out.println("Please select a spot within the board.\nColumn range: a - h\nRow range 1 - 7");
            continue;
        }

        // if the selection is within the board
        else {

            int row = 8 - Character.getNumericValue(userPieceSelection.charAt(1));
            int column = userPieceSelection.charAt(0) - 'a';


            // If selected tile is empty
            if (chessBoard.getBoard()[row][column] == null){
                System.out.println("You've selected an empty tile, please select the tile which contains a piece.");
                continue;
            }
            // If selected tile is valid and contains a piece
            else {
                selectedPiece = chessBoard.getBoard()[row][column];
                // need to generate moves for the selected piece and see if it is valid
                selectedPiece.availableMoves(chessBoard, selectedPiece.getCurrentPosition()[0], selectedPiece.getCurrentPosition()[1]);

                // Selected piece has moves, (Only exit during this condition.)
                if (selectedPiece.getMove().size() > 1 || selectedPiece.getMoveAndCapture().size() > 1) {
                    isInvalidEntry = false;
                    break;
                }
                // If the piece is selected, but there are no available moves
                else {
                    System.out.println("The selected piece can't be moved, please select another coin.");
                    continue;
                }


            }
        }

    }
    return selectedPiece;
    // Need to see if the selected piece can generate moves and then return it
}

private void generateMoves (ChessPiece selectedPiece ) {
}

    // Conditions:
    // 1. Check
    // 2. Pawn Promotion

    //Game Result:
    // Should turn off the game to inactive by displaying the result and saving the game.
    //1. Check Mate
    //2. Draw


    // Player Interaction:




    public void movePiece (ChessPiece selectedPiece ) {

        // 1. Generate the possibleMoves for the selected Piece
        // 2. Receive user input and add it to the players move's list
        // 3. Next time when the board gets gene


        // create a move add it to the moves list

    }

    public void updateMoveOnBoard (ChessPiece chessPiece, String userInput, Player player ) {

        // Create Move
        int column = userInput.charAt(0) - 'a';
        int row = 8 -Character.getNumericValue(userInput.charAt(1));

        Move move = new Move(chessPiece, new int[] {row, column});

        // Add to players move



    }
}
