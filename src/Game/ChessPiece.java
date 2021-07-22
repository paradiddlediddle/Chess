package Game;
import java.util.ArrayList;
import java.util.List;

public abstract class ChessPiece {

    public enum Color {
        BLACK, WHITE, NULL
    }
    private boolean kingIsInCheck;
    private String typeOfPiece;
    private boolean isUntouched = true;
    private final String nameOnBoard;
    private Color color;
    private final boolean pieceIsBlack;
    private boolean onTheBoard;
    private boolean canCastle = false;
    private int [] currentPosition = new int[2];

    // move and moveAndCapture will contain a list of possible moves that can be performed by the piece
    // We can still modify the list even if it is set as final. We simply can't re assign its reference i.e (move = new ArrayList<>("something"))
    private final List<int[]> move = new ArrayList<>();
    private final List<int[]> moveAndCapture = new ArrayList<>();

    // Constructor
    public ChessPiece (Color color, String nameOnBoard, int row, int column) {
        this.nameOnBoard = nameOnBoard;
        this.onTheBoard = true;
        this.color = color;
        this.currentPosition[0] = row;
        this.currentPosition[1] = column;
        this.pieceIsBlack = this.color == Color.BLACK;
        this.kingIsInCheck = false;
    }


    // Functions

    /** AVAILABLE MOVES
     * The available moves is overridden by each piece class separately since the moves of each and every piece vary.
     *

     * @param row - current row in which the piece is located on the board
     * @param column - current row in which the piece is located on the board
     */

    public void availableMoves (ChessBoard chessBoard, int row, int column, boolean kingSearch) {

        // will determine how the piece moves
        // Conditions:
        // 1.The player color and the piece color should match
        // 2. Try all moves and add it to the appropriate list.
        // 3.When the piece is selected, it should check if there is at least one possible move, otherwise throw an exception error
        // There should be 2 sub options
        // a) - List of spots the piece can move
        // b) - List of Pieces it can capture.


    }

    /** PAWN PROMOTION FUNCTIONS
     * The Pawn promotion is implemented in the parent class so that it can be access in the global context
     * when the pawn is moved to the very last column of the opponents side, an input needs to be received from the user
     * to promote the pawn to a piece of users liking
     */

   public void whitePawnPromotion (ChessBoard chessBoard, int row, int column) {
       // the functionality is implemented in the "Pawn" class.
   }
   public void blackPawnPromotion (ChessBoard chessBoard, int row, int column) {
       // the functionality is implemented in the "Pawn" class.
   }


// MOVE FUNCTIONS:

    /** SEARCH FUNCTION:
     *
     * When calling the function for the very first time, the direction at which it needs to proceed searching
     * has to be passed as "row" and "column", even though it receives a parameter called as "direction".
     * The direction parameter is to help the function determine the direction at which the recursive call needs to take place.
     *
     * @param row - row on which the search needs to be performed
     * @param column - column on which the search needs to be performed
     * @param direction - direction at which the recursive call needs to search
     * @param keepSearching - whether the function needs to keep searching or stop after searching a tile
     *
     * If keepSearching argument is set to "false", it will only search the specified row and column and stop.
     * Hence the "search" function can be used by any piece as long as it just need to search for one tile,
     * because, it will search the tile and see if a move can be generated / or a piece can be captured / or if the search is
     * out of bounds.
     *
     * @param kingsSearch - A boolean value which helps to find whether the search is done for finding kings vulnerable moves
     *
     *       if the kingSearch is "TRUE": when the moves are generated for an opponent piece,
     *       if it encounters a same colored piece along its path, it will also count the the same colored piece's spot
     *       as a move to which it can travel.
     *
     *       When the generating the opponent moves, every piece except for the king and pawn will use this same search function
     *       with the kingSearch argument as true.
     *       Since pawn attacks diagonally and king will check for other pieces' moves (in case if a king comes across
     *       another king it will cause an endless loop). So for pawn and King separate king's search functions are written
     *
     *       Note:
     *       This is done so that the king doesn't capture an opponent piece forcing itself into "Check Mate".
     *       Since the king will deliberately place itself in a "check" scenario by the end of its move AND
     *       it will be the opponents chance to move his piece, so it will be "CheckMate" by de facto.
     */

    public void search (ChessBoard chessBoard, int row, int column, String direction, boolean keepSearching, boolean kingsSearch ) {
        
        
        //If out of bounds, return without doing anything
        if (row < 0 || row > 7 || column < 0 || column > 7 ) { return; }

        //SAME COLOR PIECE ENCOUNTERED
        else if (chessBoard.getBoard()[row][column].getColor() == getColor()) {

            // If the search is called by a king piece to avoid moving into vulnerable positions, the spot of a same
            // colored piece will also be considered as a move.
            // So, instead of just returning it will add the same colored piece's spot as a move and return
            if (kingsSearch) {
                setMove(new int[] {row, column});
            }

            // If it is just a regular search, i.e kingSearch = false.
            else {
                return;
            }

        }

        //OPPOSITE COLORED PIECE IS ENCOUNTERED
        //adds it to the moveAndCapture list and returns
        else if (chessBoard.getBoard()[row][column].getColor() != getColor()
                && chessBoard.getBoard()[row][column].getColor() != Color.NULL){
            setMoveAndCapture(new int[] {row, column});
            return;
        }

        //
        else {
            // Add valid spot to move list
            setMove(new int[] {row, column});
            
            // if keepSearching is true perform a recursive call, else stop
            if (keepSearching) {
                
                switch (direction) {

                    case "right": search(chessBoard, row, column +1, direction, true, kingsSearch); break;
                    case "left" : search(chessBoard, row, column -1, direction, true, kingsSearch); break;
                    case "top"  : search(chessBoard, row -1, column, direction, true, kingsSearch); break;
                    case "bottom": search(chessBoard, row +1, column, direction, true, kingsSearch); break;
                    case "topRight": search(chessBoard,row -1, column +1, direction, true, kingsSearch); break;
                    case "topLeft": search(chessBoard, row -1, column -1, direction, true, kingsSearch); break;
                    case "bottomRight": search(chessBoard, row +1, column+1, direction, true, kingsSearch); break;
                    case "bottomLeft": search(chessBoard, row +1, column-1, direction, true, kingsSearch); break;

                }
            }
        }
    }


    // Getters and Setters


    public Color getColor() { return color; }

    public void setColor(Color color) { this.color = color; }

    public boolean isPieceBlack() { return pieceIsBlack; }
    
    public void setOnTheBoard(boolean onTheBoard) { this.onTheBoard = onTheBoard; }

    public int[] getCurrentPosition() { return currentPosition; }

    public void setCurrentPosition(int[] currentPosition) { this.currentPosition = currentPosition; }

    public List<int[]> getMove() { return move; }

    public void setMove(int[] position) { this.move.add(position); }

    public List<int[]> getMoveAndCapture() { return moveAndCapture; }

    public void setMoveAndCapture(int[] position) { moveAndCapture.add(position); }

    public String getNameOnBoard() { return nameOnBoard; }

    public boolean isUntouched() { return isUntouched; }

    public void setUntouchedToFalse () { this.isUntouched = false; }

    public void clearList ( ) { this.move.clear(); this.moveAndCapture.clear(); }

    public String getTypeOfPiece() { return typeOfPiece; }

    public void setTypeOfPiece(String typeOfPiece) { this.typeOfPiece = typeOfPiece; }

    public boolean canCastle() { return canCastle; }

    public void setCanCastle(boolean canCastle) { this.canCastle = canCastle; }

    public boolean getKingIsInCheck() { return kingIsInCheck; }

    public void setKingIsInCheck(boolean kingIsInCheck) {
        /* There are no chances for other pieces to call this method
        since the kingPosition array only gets updated when the king piece is moved */
        this.kingIsInCheck = kingIsInCheck; }
}
