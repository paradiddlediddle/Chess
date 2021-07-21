package Game;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

import static Game.Game.Status.BLACK_FORFEIT;

public class Game {

// The Status determines the outcome of the game.
    public enum Status {
        BLACK_WIN, WHITE_WIN, STALE_MATE, BLACK_FORFEIT, WHITE_FORFEIT
    }

    private Formatter recordingFile;
    private static Status gameResult;
    private static boolean isGameActive = true;
    private ChessBoard chessBoard = new ChessBoard();
    private Player player1 = new Player(false);
    private Player player2 = new Player(true);
    private static List<String> playerMoves = new ArrayList<>();
    private int[] whiteKingPosition = {7,4};
    private int[] blackKingPosition = {0,4};


    /**
     * The "game" public constructor is the heart of the program
     * isGameActive is a boolean variable which keeps the program running.
     * Since when a game is started, it always starts with the "White" (Player-1),
     * the playerMove functionality for the player-1 will get executed right away.
     *
     * The player-2's move using the "PlayerMove" function is called only if the game is active.
     * If the game reaches any one of the statuses mentioned above, the game would have ended by setting the "isGameActive" to false.
     * If the game ends, the data export with the list of moves will take place.
     */


    public Game () {
        while (isGameActive) {

            playerMove(player1, chessBoard);
            if (isGameActive) playerMove(player2, chessBoard);

          if (!isGameActive()) {
              exportData();
          }

        }
    }

public void playerMove (Player player, ChessBoard chessBoard) {

    int[] newPosition = null;
    String playerMove = null;
    ChessPiece userSelectedPiece = choosePiece(player, chessBoard);
    // If statements are added so that the game doesn't stop when the game is exited.
   if (isGameActive()){ newPosition = generateMovesAndGetInput(userSelectedPiece); }
   if (isGameActive()){ movePiece(chessBoard, userSelectedPiece, newPosition);}
}


    /**CHOOSE PIECE:
     * Asks the user to select a piece from the board and returns a "ChessPiece" object if the selection is valid
     *
     * The selected piece has to have a valid set of moves, so the function also generates the moves for the user selection
     * and see if it has at least one valid move or moveAndCapture.
     *
     * Also, other restrictions such as selecting the opponents piece, empty spot or invalid entries are all filtered out.
     *
     * Instead of choosing a piece, if the user enter "print" - the chessboard will be printed
     * if the user enters "exit" - the game will exit and the player who exits would lose the game.
     * The game status will also end when the user exits.
     */



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
            if (player.isBlack()) { setGameResult(BLACK_FORFEIT);
                System.out.println("White wins");
                addPlayerMove("White wins by forfeit!");
            }
            else { setGameResult(Status.WHITE_FORFEIT);
                System.out.println("Black wins");
                addPlayerMove("Black wins by forfeit!");
            }
           break;
        }

        // The user has to select a piece within the board
        if (userPieceSelection.length() != 2 || !(userPieceSelection.charAt(0) >= 97 && userPieceSelection.charAt(0) <= 104) ||
                !(userPieceSelection.charAt(1) >= 49 && userPieceSelection.charAt(1) <= 56)) {
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
                selectedPiece.availableMoves(chessBoard, selectedPiece.getCurrentPosition()[0], selectedPiece.getCurrentPosition()[1], false);

                // Selected piece has moves, (Only exit during this condition.)
                if (selectedPiece.getMove().size() >= 1 || selectedPiece.getMoveAndCapture().size() >= 1) {
                    isInvalidEntry = false;
                    break;
                }
                // If the piece is selected, but there are no available moves
                else {
                    if (gameResult == Status.STALE_MATE) {
                        break;
                    }
                    else {
                        System.out.println("The selected piece can't be moved, please select another coin.");
                        continue;
                    }
                }


            }
        }

    }
    return selectedPiece;
    // Need to see if the selected piece can generate moves and then return it
}

    /**FUNCTION TO DISPLAY MOVES AVAILABLE FOR A PIECE AND RECEIVE INPUT FROM THE USER
     *
     * The function will check if the user has selected the correct move and if it's valid. If the move is valid, it will
     * return the target position to which the piece needs to be moved. The "targetPosition" is returned in the form of int[] array.
     *
     * @param selectedPiece - The piece selected by the user in "choosePiece" functionality is passed in to this
     *                      function as an argument. The function generate the move and "moveAndCapture" list for
     *                      the selected piece.
     *
     * Instead of choosing a piece, if the user enter "print" - the chessboard will be printed
     * if the user enters "exit" - the game will exit and the player who exits would lose the game.
     * The game status will also end when the user exits.
     */


private int[] generateMovesAndGetInput (ChessPiece selectedPiece) {

    // The moves would have already been generated by the "choosePiece" function. We just convert,
    // the 2D matrix indices into Algebraic notation, so that the user can relate to where he is moving the piece.

    System.out.println("\nMove to an empty spot: ");
    if (selectedPiece.getMove().size() < 1) { System.out.println("None"); }
    // Every move present in the "move" list in the int[] array format is retrieved and displayed as a string
    else {

        for (int i=0; i<selectedPiece.getMove().size(); i++){
          String row =  Integer.toString(8 - selectedPiece.getMove().get(i)[0]);
          String column = "" + (char)(97 + selectedPiece.getMove().get(i)[1]);
          // To print the last element without ","
            if (i == selectedPiece.getMove().size()-1) { System.out.print( column+row ); }
            else { System.out.print( column+row+", "); }
        }
    }

// Every capture move present in the "moveAndCapture" list in the int[] array format is retrieved and displayed as a string
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


    // Ask the user to enter a move from the provided list of moves.

    Scanner selectedMove = new Scanner(System.in);
    String userInput = null;
    boolean isInvalidInput = true;

    while (isInvalidInput) {
        int playerNumber;
        if (selectedPiece.isPieceBlack()) {playerNumber = 2;  }
        else { playerNumber = 1; }

        System.out.print("\n\nPlayer-"+ playerNumber +"\nPiece Selected: "+ selectedPiece.getTypeOfPiece()+"\nPlease enter your move: ");
        
        userInput = selectedMove.nextLine();
        userInput.trim().toLowerCase();

        // CHECKING IF THE USER HAS ENTERED 'PRINT' OR 'EXIT'

        // If user types "Print"
        if (userInput.equalsIgnoreCase("print")) {
            chessBoard.displayChessboard();
            continue;
        }

        // If user types "exit"
        if (userInput.equalsIgnoreCase("exit")) {
            setGameActive(false);
            if (selectedPiece.isPieceBlack() ) { setGameResult(BLACK_FORFEIT);
                System.out.println("White wins by forfeit!");
                addPlayerMove("White wins by forfeit");
            }
            else { setGameResult(Status.WHITE_FORFEIT);
                System.out.println("Black wins by forfeit!");
                addPlayerMove("Black wins by forfeit");
            }
            break;
        }

        // To weed out incorrect entries, to see if the entered input is of the right format and within the board.

        // The user has to select a piece within the board
        if ( !(userInput.length() == 2) || !(userInput.charAt(0) >= 97 && userInput.charAt(0) <= 104) ||
                !(userInput.charAt(1) >= 49 && userInput.charAt(1) <= 56) ) {
            System.out.println("Please select a spot within the board.\nColumn range: a - h\nRow range 1 - 7");
            continue;
        }

        // if the selection is within the board
        else {

            // 2 FOR LOOPS, 1 for checking the "move" list and the other for "moveAndCapture" list.
            // If the users entry matches with the list of available moves displayed, the boolean variable is set to false.

            boolean hasAtLeastOneMatch = false;

            //Loop to check if the user has selected within the available "move" positions
            // 2D matrix index array is converted as a string for easier comparison
            for (int i = 0; i < selectedPiece.getMove().size(); i++) {
                String row = Integer.toString(8 - selectedPiece.getMove().get(i)[0]);
                String column = "" + (char) (97 + selectedPiece.getMove().get(i)[1]);
                String positionInString = column+row;

                if (userInput.equalsIgnoreCase(positionInString)) hasAtLeastOneMatch = true;


            }

            //Loop to check if the user has selected within the available "moveAndCapture" positions
            // 2D matrix index array is converted as a string for easier comparison
            for (int i = 0; i < selectedPiece.getMoveAndCapture().size(); i++) {
                String row = Integer.toString(8 - selectedPiece.getMoveAndCapture().get(i)[0]);
                String column = "" + (char) (97 + selectedPiece.getMoveAndCapture().get(i)[1]);
                String positionInString = column+row;

                if (userInput.equalsIgnoreCase(positionInString)) hasAtLeastOneMatch = true;

            }

            // Once the loops are over if the users entry doesn't match with even one of the available moves present in the list
            // The loop won't break. Only when "hasAtLeastOneMatch" is true, the program will end.
            // The loop that is broken would be the "isInvalidInput" while loop at the very beginning of the function.

            if (hasAtLeastOneMatch) {
                break;
            } else {
                System.out.println("Please enter a value from the available moves");
                continue;
            }

        }
    } // While loop closes here

    // Once the while loop is over and when we find a valid selection, we convert the string back to a 2D matrix index
    // and return the int [] array

    int targetColumn =  userInput.charAt(0) - 'a';
    int targetRow = 8 - Character.getNumericValue(userInput.charAt(1));
    return new int[] {targetRow, targetColumn};
}

    /** MOVING THE PIECE FROM OLD POSITION TO NEW POSITION
     * The Job of this function is to move the selected piece to the target position on the board and update the old position's
     * value to null piece, which represents an empty spot.
     *
     * After the piece has been moved, we check for a few conditions to see if the game needs to stopped or trigger pawnPromotion
     * We check for, 1. If the opponentKing is in check?
     *               2. Generate opponent king's move, (in case if it doesn't have any moves the game would end in check mate or stale mate.)
     *               3. Pawn Promotion
     *
     * @param targetPosition - The target position index in the form of an array is passed as an argument to the move function
     *
     * At the end of every move, the moves list of the piece is cleared so that the list empty when it is selected next time.
     */



private void movePiece ( ChessBoard chessBoard, ChessPiece selectedPiece, int[] targetPosition ) {



    //(move piece needs to handle pawn promotion not king castling)

    //1. Move the piece from currentPosition to target position then set the old position to null
    int oldRow = selectedPiece.getCurrentPosition()[0];
    int oldColumn = selectedPiece.getCurrentPosition()[1];
    int newRow = targetPosition[0];
    int newColumn = targetPosition[1];

    addMoveToList(selectedPiece, targetPosition);

    // UPDATE WINNING CONDITION, IF THE FINAL POSITION CONTAINS AN OPPONENT KING
    if (chessBoard.getBoard()[newRow][newColumn].getNameOnBoard().equalsIgnoreCase("W_K")){
        setGameResult(Status.BLACK_WIN);
        setGameActive(false);
        System.out.println("Check Mate!  Black wins");
        addPlayerMove("Check Mate! Black wins");
    }
    else if (chessBoard.getBoard()[newRow][newColumn].getNameOnBoard().equalsIgnoreCase("B_K")){
        setGameResult(Status.WHITE_WIN);
        setGameActive(false);
        System.out.println("Check Mate! White wins");
        addPlayerMove("Check Mate! White wins");
    }

    // IF KING IS BEING MOVED
    // a) update its position in the globalVariable
    // b) update the king's "kingIsInCheck" variable to false
    if(selectedPiece.getNameOnBoard().equalsIgnoreCase("W_K")) {
        whiteKingPosition = targetPosition;
        // In case if a king was in check earlier, we need to set it to false when moved
        // (The only reason why the king was able to move was because it was able to find a move in which no pieces could attack it.
        // Hence, there is no harm in setting the value to false even if it is already false.
        selectedPiece.setKingIsInCheck(false);
    }
    else if(selectedPiece.getNameOnBoard().equalsIgnoreCase("B_K")) {
        blackKingPosition = targetPosition;
        // In case if a king was in check earlier, we need to set it to false when moved
        // (The only reason why the king was able to move was because it was able to find a move in which no pieces could attack it.
        // Hence, there is no harm in setting the value to false even if it is already false.
        selectedPiece.setKingIsInCheck(false);
    }
    
    

    //1.CASTLING

    // Only the king will have the "canCastle" variable set to true. Since the "canCastle" variable will only be true
    // if the king hasn't been moved at all. So, if the king's new column is 2 or 6, the king is moving 2 steps, hence we need to castle
    if (selectedPiece.canCastle() && newColumn == 2 || selectedPiece.canCastle() && newColumn == 6 ) {

        //King is first moved and its old position is set to null
        selectedPiece.setUntouchedToFalse(); // King is moved
        chessBoard.updateBoardPiece(selectedPiece, newRow, newColumn); // Update the current position of the piece to its new position
        chessBoard.updateBoardPieceToNull(oldRow, oldColumn);// Update the old position to null
        selectedPiece.clearList();


        //Queen Side Castling
        if (selectedPiece.canCastle() && newColumn == 2){

            // White Piece
            if (selectedPiece.getColor() == ChessPiece.Color.WHITE) {
                ChessPiece rook = chessBoard.getBoard()[7][0];
                chessBoard.updateBoardPiece(rook, 7, 3);
                chessBoard.updateBoardPieceToNull(7,0);
            }

            else if (selectedPiece.getColor() == ChessPiece.Color.BLACK) {
                ChessPiece rook = chessBoard.getBoard()[0][0]; // Get rook piece
                chessBoard.updateBoardPiece(rook, 0, 3); // move rook
                chessBoard.updateBoardPieceToNull(0,0); // update rooks old position to null
            }
            selectedPiece.setCanCastle(false); // can't castle set to false to prevent doing it again
        }

        // King Side Castling
        else {

            // White Side
            if (selectedPiece.getColor() == ChessPiece.Color.WHITE) {
                ChessPiece rook = chessBoard.getBoard()[7][7]; // Get rook piece
                chessBoard.updateBoardPiece(rook, 7, 5); // move rook
                chessBoard.updateBoardPieceToNull(7,7); // update rooks old position to null
            }

            //Black Side
            else if (selectedPiece.getColor() == ChessPiece.Color.BLACK) {
                ChessPiece rook = chessBoard.getBoard()[0][7]; // Get rook piece
                chessBoard.updateBoardPiece(rook, 0, 5); // move rook
                chessBoard.updateBoardPieceToNull(0,7); // update rooks old position to null
            }

            selectedPiece.setCanCastle(false); // can't castle set to false to prevent doing it again
        }

    }



    //2.MOVE
    //If the target Position is empty
    else if (chessBoard.getBoard()[newRow][newColumn].getColor() == ChessPiece.Color.NULL) {

        selectedPiece.setUntouchedToFalse();
        chessBoard.updateBoardPiece(selectedPiece, newRow, newColumn); // Update the board pieces to these values
        selectedPiece.setCurrentPosition(targetPosition);              // Update the current position of the piece to its new position
        chessBoard.updateBoardPieceToNull(oldRow, oldColumn);// Update the old position to null
        selectedPiece.clearList();
    }

    //3.CAPTURE
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

    // THE PIECE HAS MOVED

    // functions and conditions that needs to be called after the piece is moved are written below.

    //1. Is King in Check?
    findIfKingIsInCheck(selectedPiece); // This method will update the kings variable directly

    //2. generate opponent king moves to see if there is a checkMate or stalemate condition
    generateOpponentKingMoves(selectedPiece);

    //3. a) Black Pawn promotion
    if (selectedPiece.getNameOnBoard().equalsIgnoreCase("B_P") && newRow == 7) {
        selectedPiece.blackPawnPromotion(chessBoard, newRow, newColumn);
    }

    //3. b)  White Pawn Promotion
    else if (selectedPiece.getNameOnBoard().equalsIgnoreCase("W_P") && newRow == 0) {
        selectedPiece.whitePawnPromotion(chessBoard, newRow, newColumn);
    }


}

    /**
     * This function is called withing the "move" function after the piece is moved
     *
     * @param selectedPiece - The piece which just got moved
     *
     *  Checks if the piece which got moved can attack the king in its next move
     *  If it can attack, then it displays a message in the terminal saying "Check"
     *  and updates the respective king's "kingIsInCheck" variable to true.
     */

    private void findIfKingIsInCheck (ChessPiece selectedPiece ) {
        
       int row = selectedPiece.getCurrentPosition()[0];
       int column = selectedPiece.getCurrentPosition()[1];
       
       selectedPiece.availableMoves(chessBoard, row, column, false);

       /* There are no chances for other pieces to be selected as the king
        since the kingPosition array only gets updated when the king piece is moved */
       
        for (int i=0; i<selectedPiece.getMoveAndCapture().size(); i++) {
           
           //If selected piece is white and can capture the king in its next move
           if (selectedPiece.getMoveAndCapture().get(i)[0] == blackKingPosition[0] 
                   && selectedPiece.getMoveAndCapture().get(i)[1] == blackKingPosition[1]) {
               
               ChessPiece king = chessBoard.getBoard()[blackKingPosition[0]][blackKingPosition[1]];
               king.setKingIsInCheck(true);
               System.out.println("Black king is in check!");
           }
           
           //If selected piece is black and can capture the king in its next move

           else if (selectedPiece.getMoveAndCapture().get(i)[0] == whiteKingPosition[0]
                    && selectedPiece.getMoveAndCapture().get(i)[1] == whiteKingPosition[1]) {

                ChessPiece king = chessBoard.getBoard()[whiteKingPosition[0]][whiteKingPosition[1]];
                king.setKingIsInCheck(true);
                System.out.println("White king is in check!");
            }

       }
        
        // Clear the generated moves of the selected piece so that
        // when the user selects the piece during next turn a fresh list is generated.
        // (avoids duplicates since when a piece is selected by the user, "availableMoves" function will be called to generate moves)
       selectedPiece.clearList();
    }

    /**
     * Simply generate moves for the opponent pieces' king piece
     * If the king had moves and doesn't have moves, the game will stop due to check or stale mate.
     * If the king had moves, clear the generated moves, we simply call this function to see if the king gets check mated or stale mated.
     */
    
    private void generateOpponentKingMoves (ChessPiece selectedPiece) {
        
        // Selected piece is black, check moves for white King
        if (selectedPiece.isPieceBlack()) {
            ChessPiece king = chessBoard.getBoard()[whiteKingPosition[0]][whiteKingPosition[1]];
            //1. Generate kings available move
            king.availableMoves(chessBoard, whiteKingPosition[0], whiteKingPosition[1],false); //0 - row, 1 - column
            //2. Clear the generated moves, just in case if the game didn't end to keep List empty.
            king.clearList();
        }
        // Selected piece is white, check moves for black King
        else  {
            ChessPiece king = chessBoard.getBoard()[blackKingPosition[0]][blackKingPosition[1]];
            //1. Generate kings available move
            king.availableMoves(chessBoard, blackKingPosition[0], blackKingPosition[1], false); //0 - row, 1 - column
            //2. Clear the generated moves, just in case if the game didn't end to keep List empty.
            king.clearList();
        }

    }


    /** Converts moves into readable sentence and adds it to the playerMoves List.
     * The purpose of this function is to convert the the moves from array into a string
     * and add it to the player moves list.
     * This is done so that the entire list of moves played during the game can be exported as a file.
     */

    
    private void addMoveToList (ChessPiece selectedPiece, int[] targetPosition) {

        int playerNumber;
       String oldRow = Integer.toString( (8- selectedPiece.getCurrentPosition()[0]) );
       String oldColumn = "" + (char) (97 + selectedPiece.getCurrentPosition()[1]);
       String oldPosition  = oldColumn + oldRow;
       String newRow = Integer.toString( (8- targetPosition[0]) );
       String newColumn = "" + (char) (97 + targetPosition[1]);
       String newPosition  = newColumn + newRow;
        
        
        if (selectedPiece.getColor() == ChessPiece.Color.BLACK) { playerNumber = 2; }
        else playerNumber = 1;
        
        String moveInString = "Player-"+playerNumber+" moved "+ selectedPiece.getTypeOfPiece()+" from "+oldPosition+" to "+ newPosition;
        addPlayerMove(moveInString);
    }


    /**
     * Exports the entire moves made during the game play in the form of a txt file
     * The date and time at which the game ends will be added as the file name.
     */

    private void exportData () {

        DateTimeFormatter dateTimeStamp = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        LocalDateTime now  = LocalDateTime.now();

        String uniqueID = dateTimeStamp.format(now);

        //Open File
        try{ recordingFile = new Formatter("GameRecording"+ uniqueID +".txt"); }
        catch (Exception exception ){ System.out.println(exception); }

        //Create File

        for (int i=0; i<getPlayerMoves().size(); i++) {
            recordingFile.format("%s %d %s %n", "Move", i ,": " + getPlayerMoves().get(i) + " ");
        }

        //Close File
        recordingFile.close();

    }





// Getters and Setters


    public static boolean isGameActive() { return isGameActive; }

    public static void setGameActive(boolean gameActive) { isGameActive = gameActive; }

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

    public static Status getGameResult() {
        return gameResult;
    }

    public static void setGameResult(Status gameResult) {
        Game.gameResult = gameResult;
    }

    public static List<String> getPlayerMoves() {
        return playerMoves;
    }

    public static void addPlayerMove(String move) {
        playerMoves.add(move);
    }


}
