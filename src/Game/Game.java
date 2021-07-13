package Game;


import java.util.Scanner;


public class Game {


    public enum Status {
        BLACK_WIN, WHITE_WIN, DRAW, BLACK_FORFEIT, WHITE_FORFEIT
    }


    private Status gameResult;
    private boolean isGameActive = true;
    private ChessBoard chessBoard = new ChessBoard();
    private Player player1 = new Player(false);
    private Player player2 = new Player(true);


    public Game () {
        while (isGameActive) {

          if (isGameActive) playerMove(player1, chessBoard);

           if (isGameActive) playerMove(player2, chessBoard);

        }

    }
    

public void playerMove (Player player, ChessBoard chessBoard) {

        // Even after the game is forfeited, "generateMovesAndGetInput" & "movePiece" will be called
        // Need to find a way to exit the game without any error

    ChessPiece userSelectedPiece = choosePiece(player, chessBoard);
    int[] newPosition = generateMovesAndGetInput(userSelectedPiece);
    Move playerMove = movePiece(chessBoard, userSelectedPiece, newPosition);
    // Add the players move to the player's move list
    player.addPlayerMoves(playerMove);
}



// Provides the user with options to select a piece and returns the piece
private ChessPiece choosePiece (Player player, ChessBoard chessBoard) {
    Scanner pieceScanner = new Scanner(System.in);
    boolean isInvalidEntry = true;
    ChessPiece selectedPiece = null;

    while (isInvalidEntry) {


        System.out.println("\n\nPlayer-"+ player.getPlayerNumber() +"\nPlease select a piece: ");
        String userPieceSelection = pieceScanner.nextLine();
                userPieceSelection.trim().toLowerCase();

        // If user types "Print"
        if (userPieceSelection.equalsIgnoreCase("print")) {
            chessBoard.displayChessboard();
            continue;
        }

        // If user types "exit"
        if (userPieceSelection.equalsIgnoreCase("exit")) {
            setGameActive(false);
            if (player.isBlack()) { setGameResult(Status.BLACK_FORFEIT);
                System.out.println("White wins"); }
            else { setGameResult(Status.WHITE_FORFEIT);
                System.out.println("Black wins");
            }
           break;
        }


        // The user has to select a piece within the board
        if (!(userPieceSelection.charAt(0) >= 97 && userPieceSelection.charAt(0) <= 104) ||
                !(userPieceSelection.charAt(1) >= 49 && userPieceSelection.charAt(1) <= 56) ||
                !(userPieceSelection.length() == 2) ) {
            System.out.println("Please select a spot within the board.\nColumn range: a - h\nRow range 1 - 7\n");
            continue;
        }

        // if the selection is within the board
        else {

            int row = 8 - Character.getNumericValue(userPieceSelection.charAt(1));
            int column = userPieceSelection.charAt(0) - 'a';


            // If selected tile is empty
            if (chessBoard.getBoard()[row][column].getColor() == ChessPiece.Color.NULL){
                System.out.println("You've selected an empty tile, please select the tile which contains a piece.\n");
                continue;
            }


            // If selected tile is valid and contains a piece
            else {
                selectedPiece = chessBoard.getBoard()[row][column];

                   if (player.isBlack() != selectedPiece.isPieceBlack()) {
                    System.out.println("\nPlease select a piece from your side.\nPlayer-1: White\nPlayer-2: Black");
                    continue;
                }

                // need to generate moves for the selected piece and see if it is valid
                selectedPiece.availableMoves(chessBoard, selectedPiece.getCurrentPosition()[0], selectedPiece.getCurrentPosition()[1]);

                // Selected piece has moves, (Only exit during this condition.)
                if (selectedPiece.getMove().size() >= 1 || selectedPiece.getMoveAndCapture().size() >= 1) {
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

private int[] generateMovesAndGetInput (ChessPiece selectedPiece) {

    System.out.println("\nMove to an empty spot: ");
    if (selectedPiece.getMove().size() < 1) { System.out.println("None"); }
    // Just moves
    else {

        for (int i=0; i<selectedPiece.getMove().size(); i++){
          String row =  Integer.toString(8 - selectedPiece.getMove().get(i)[0]);
          String column = "" + (char)(97 + selectedPiece.getMove().get(i)[1]);
          // To print the last element without ","
            if (i == selectedPiece.getMove().size()-1) { System.out.print( column+row ); }
            else { System.out.print( column+row+", "); }
        }
    }

    // Move and Capture
    System.out.println("\nMove and Capture: ");
    if (selectedPiece.getMoveAndCapture().size() < 1) { System.out.println("None"); }
    else {
        for (int i=0; i<selectedPiece.getMoveAndCapture().size(); i++){
            String row =  Integer.toString(8 - selectedPiece.getMoveAndCapture().get(i)[0]);
            String column = "" + (char)(97 + selectedPiece.getMoveAndCapture().get(i)[1]);
            // To print the last element without ","
            if (i == selectedPiece.getMoveAndCapture().size()-1) { System.out.print( column+row ); }
            else { System.out.print( column+row+", "); }
        }
    }


    // Receive the input from user

    Scanner selectedMove = new Scanner(System.in);
    String userInput = null;
    boolean isInvalidInput = true;

    while (isInvalidInput) {
        int playerNumber;
        if (selectedPiece.isPieceBlack()) {playerNumber = 2;  }
        else { playerNumber = 1; }

        System.out.print("\n\nPlayer-"+ playerNumber +"\nPlease enter your move: ");
        
        userInput = selectedMove.nextLine();
        userInput.trim().toLowerCase();

        // If user types "Print"
        if (userInput.equalsIgnoreCase("print")) {
            chessBoard.displayChessboard();
            continue;
        }

        // If user types "exit"
        if (userInput.equalsIgnoreCase("exit")) {
            setGameActive(false);
            if (selectedPiece.isPieceBlack() ) { setGameResult(Status.BLACK_FORFEIT);
                System.out.println("White wins");
            }
            else { setGameResult(Status.WHITE_FORFEIT);
                System.out.println("Black wins");
            }
            break;
        }


        // The user has to select a piece within the board
        if (!(userInput.charAt(0) >= 97 && userInput.charAt(0) <= 104) ||
                !(userInput.charAt(1) >= 49 && userInput.charAt(1) <= 56) ||
                !(userInput.length() == 2) ) {
            System.out.println("Please select a spot within the board.\nColumn range: a - h\nRow range 1 - 7");
            continue;
        }

        // if the selection is within the board
        else {

            boolean hasAtLeastOneMatch = false;

            //Loop to check if the user has selected within the available "move" positions
            for (int i = 0; i < selectedPiece.getMove().size(); i++) {
                String row = Integer.toString(8 - selectedPiece.getMove().get(i)[0]);
                String column = "" + (char) (97 + selectedPiece.getMove().get(i)[1]);
                String positionInString = column+row;

                if (userInput.equalsIgnoreCase(positionInString)) hasAtLeastOneMatch = true;


            }

            //Loop to check if the user has selected within the available "moveAndCapture" positions
            for (int i = 0; i < selectedPiece.getMoveAndCapture().size(); i++) {
                String row = Integer.toString(8 - selectedPiece.getMoveAndCapture().get(i)[0]);
                String column = "" + (char) (97 + selectedPiece.getMoveAndCapture().get(i)[1]);
                String positionInString = column+row;

                if (userInput.equalsIgnoreCase(positionInString)) hasAtLeastOneMatch = true;

            }

            if (hasAtLeastOneMatch) {
                hasAtLeastOneMatch = false;
                break;
            } else {
                System.out.println("Please enter a value from the available moves");
                continue;
            }

        }
    } // While loop closes here
    int targetColumn =  userInput.charAt(0) - 'a';
    int targetRow = 8 - Character.getNumericValue(userInput.charAt(1));
    return new int[] {targetRow, targetColumn};
}


private Move movePiece ( ChessBoard chessBoard, ChessPiece selectedPiece, int[] targetPosition ) {

    //(move piece needs to handle pawn promotion not king castling)

    //1. Move the piece from currentPosition to target position then set the old position to null
    int oldRow = selectedPiece.getCurrentPosition()[0];
    int oldColumn = selectedPiece.getCurrentPosition()[1];
    int newRow = targetPosition[0];
    int newColumn = targetPosition[1];

    Move move = new Move(selectedPiece, targetPosition);

    // Update winning condition
    if (chessBoard.getBoard()[newRow][newColumn].getNameOnBoard().equalsIgnoreCase("W_K")){
        setGameResult(Status.BLACK_WIN);
        setGameActive(false);
        System.out.println("Black wins");
    }
    else if (chessBoard.getBoard()[newRow][newColumn].getNameOnBoard().equalsIgnoreCase("B_K")){
        setGameResult(Status.WHITE_WIN);
        setGameActive(false);
        System.out.println("White wins");
    }

    //MOVE
    //If the target Position is empty
    if (chessBoard.getBoard()[newRow][newColumn].getColor() == ChessPiece.Color.NULL) {

        selectedPiece.setUntouchedToFalse();
        chessBoard.updateBoardPiece(selectedPiece, newRow, newColumn); // Update the board pieces to these values
        selectedPiece.setCurrentPosition(targetPosition);              // Update the current position of the piece to its new position
        chessBoard.updateBoardPieceToNull(oldRow, oldColumn);// Update the old position to null
        selectedPiece.clearList();
    }

    //CAPTURE
    // If the target Position has a piece which needs to be taken down
    else {
        // setting the value of the piece to not be on board and making the target spot null.
        selectedPiece.setUntouchedToFalse();
        chessBoard.updateBoardPieceToNull (newRow, newColumn); // Clearing up the target

        chessBoard.updateBoardPiece(selectedPiece, newRow, newColumn);
        selectedPiece.setCurrentPosition(targetPosition);
        chessBoard.updateBoardPieceToNull (oldRow, oldColumn);// Update the old position to null
        selectedPiece.clearList();
    }


    //Black Pawn promotion
    if (selectedPiece.getNameOnBoard().equalsIgnoreCase("B_P") && newRow == 7) {
        selectedPiece.blackPawnPromotion(chessBoard, newRow, newColumn);
    }

    //White Pawn Promotion
    else if (selectedPiece.getNameOnBoard().equalsIgnoreCase("W_P") && newRow == 0) {
        selectedPiece.whitePawnPromotion(chessBoard, newRow, newColumn);
    }



    return move;

}

// Getters and Setters


    public boolean isGameActive() { return isGameActive; }

    public void setGameActive(boolean gameActive) { isGameActive = gameActive; }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Status getGameResult() {
        return gameResult;
    }

    public void setGameResult(Status gameResult) {
        this.gameResult = gameResult;
    }
}
