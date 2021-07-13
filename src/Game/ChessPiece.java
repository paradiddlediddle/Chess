package Game;

import java.util.ArrayList;
import java.util.List;

public abstract class ChessPiece {

    public enum Color {
        BLACK, WHITE, NULL
    }
    private String typeOfPiece;
    private boolean isUntouched = true;
    private String nameOnBoard;
    private Color color;
    private boolean pieceIsBlack;
    private boolean onTheBoard;
    private int [] currentPosition = new int[2];
    // move and moveAndCapture will contain a list of possible moves that can be performed by the piece
    private List<int[]> move = new ArrayList<>();
    private List<int[]> moveAndCapture = new ArrayList<>();

    // Constructor
    public ChessPiece (Color color, String nameOnBoard, int row, int column) {
        this.nameOnBoard = nameOnBoard;
        this.onTheBoard = true;
        this.color = color;
        this.currentPosition[0] = row;
        this.currentPosition[1] = column;
        if (this.color == Color.BLACK) {pieceIsBlack = true;}
        else  {pieceIsBlack = false;}
    }









    // Functions

    public void availableMoves (ChessBoard chessBoard, int row, int column) {

        // will determine how the piece moves
        // Conditions:
        // 1.The player color and the piece color should match
        // 2. Try all moves and add it to the appropriate list.
        // 3.When the piece is selected, it should check if there is at least one possible move, otherwise throw an exception error
        // There should be 2 sub options
        // a) - List of spots the piece can move
        // b) - List of Pieces it can capture.
   }

   public void whitePawnPromotion (ChessBoard chessBoard, int row, int column) {

   }

   public void blackPawnPromotion (ChessBoard chessBoard, int row, int column) {


   }



    // Getters and Setters


    public Color getColor() { return color; }

    public void setColor(Color color) { this.color = color; }

    public boolean isPieceBlack() { return pieceIsBlack; }

    public void setBlack(boolean black) { pieceIsBlack = black; }

    public boolean isOnTheBoard() { return onTheBoard; }

    public void setOnTheBoard(boolean onTheBoard) { this.onTheBoard = onTheBoard; }

    public int[] getCurrentPosition() { return currentPosition; }

    public void setCurrentPosition(int[] currentPosition) { this.currentPosition = currentPosition; }

    public List<int[]> getMove() { return move; }

    public void setMove(int[] position) { this.move.add(position); }

    public List<int[]> getMoveAndCapture() { return moveAndCapture; }

    public void setMoveAndCapture(int[] position) { moveAndCapture.add(position); }

    public String getNameOnBoard() { return nameOnBoard; }

    public void setNameOnBoard(String nameOnBoard) { this.nameOnBoard = nameOnBoard; }

    public boolean isUntouched() { return isUntouched; }

    public void setUntouchedToFalse () { this.isUntouched = false; }

    public void clearList ( ) { this.move.clear(); this.moveAndCapture.clear(); }

    public String getTypeOfPiece() { return typeOfPiece; }

    public void setTypeOfPiece(String typeOfPiece) { this.typeOfPiece = typeOfPiece; }
}
