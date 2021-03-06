package Pieces;

import Game.ChessBoard;
import Game.ChessPiece;

import java.util.List;
import java.util.Scanner;

public class Pawn extends ChessPiece {


    public Pawn(Color color, String nameOnBoard, int row, int column) {
        super(color, nameOnBoard, row, column);
        super.setTypeOfPiece("Pawn");
    }


    // The Pawn can attack if the king moves, need to incorporate this


    public void availableMoves(ChessBoard chessBoard, int row, int column, boolean kingSearch) {

        // check if it is black or a white first
        // then check if it is already moved or not

        // black piece moves towards the bottom of the board
        if (isPieceBlack()) {

            if (kingSearch) {
                diagonalCapture(chessBoard, row+1, column+1, true);
                diagonalCapture(chessBoard, row+1, column-1, true);
            }
            // Normal set of moves
            else {

                moveForward(chessBoard, row+1, column); // 1 Step
                diagonalCapture(chessBoard, row+1, column+1, false); // Bottom right;
                diagonalCapture(chessBoard, row+1, column-1, false); // Bottom left;

                //Can move 2 Steps only if it is untouched
                if (isUntouched()) {  moveForward(chessBoard, row+2, column); } // 2 Steps

            }
        }

        // white piece moves towards the top of the board
        else {

            if (kingSearch) {

                diagonalCapture(chessBoard, row-1, column+1, true);
                diagonalCapture(chessBoard, row-1, column+1, true);

            }
            // Normal set of moves, (move forward, attack on sides)
            else{

                moveForward(chessBoard, row-1, column); // 1 Step
                diagonalCapture(chessBoard, row-1, column+1, false); // Bottom right;
                diagonalCapture(chessBoard, row-1, column-1, false); // Bottom left;

                //Can move 2 Steps only if it is untouched
                if (isUntouched()) {  moveForward(chessBoard, row-2, column); } // 2 Steps

            }
        }

    }

    /**PAWN MOVEMENT FUNCTIONS
     *
     * The pawn can only move towards the opponents side
     * The pawn can only attack in diagonal
     * Since the moving and capturing is different for the pawn from the other pieces.
     * Separate functions are written for moving and capturing the pawn.
     */


    // Just Moves Forward as long as there is an empty spot
    private void moveForward (ChessBoard chessBoard, int row, int column) {

        // Base Case: If the row/column is out of bounds or there is anyPiece in front of it.
        if (row < 0 || row > 7 || column < 0 || column > 7 ||
                chessBoard.getBoard()[row][column].getColor() != Color.NULL  ) { return; }

        else {
            setMove(new int[] {row, column});
        }

    }

    /**DIAGONAL CAPTURE:
     * The function works differently with kingSearch argument set to "True" and "False".
     * If the argument is set as true. The the function will determine if a piece can move diagonally and add it to its moves
     * In case if a same colored piece is present on its diagonal, it will try to add that spot to its moves List.
     *
     * If the king search argument is set to false it will only use this move to find if it can attack
     * an opposite colored piece and add it to its list of "moveAndCapture" List.
     */



    // If there are any opponent pieces on the searching row and column it add it to the moveAndCaptures list
    private void diagonalCapture (ChessBoard chessBoard, int row, int column, boolean kingSearch) {

        if (kingSearch) {

            //out of bounds return
            if (row < 0 || row > 7 || column < 0 || column > 7) {
                return;
            }
            // if (the diagonal side of the pawn is empty or contains a same colored piece, it should be added
            // to the available list of moves
            else if ( chessBoard.getBoard()[row][column].getColor() == Color.NULL   ||
                    chessBoard.getBoard()[row][column].isPieceBlack() == isPieceBlack()) {

                setMove(new int[] {row, column});
                return;
            }

        }

        // If it is an ordinary search diagonal search
        else {

            // Base Case: if there is no opposite color pieces on the adjacent side or if there are no pieces at all
            // We can't capture any, so we simply return. (Also considering out of bounds).
            if (row < 0 || row > 7 || column < 0 || column > 7 ||
                    chessBoard.getBoard()[row][column].getColor() == Color.NULL   ||
                    chessBoard.getBoard()[row][column].isPieceBlack() == isPieceBlack()) { return; }

            else {
                // There is an adjacent opposite color piece
                setMoveAndCapture(new int[] {row, column});
            }

        }

    }


    /** PAWN PROMOTION: ONCE THE USER MOVES THE PAWN TO THE FINAL SPOT, THE PROMOTION SHOULD BE TRIGGERED
     *
     * The White and black pawn promotion will be triggered by the "move" function in the game class.
     * Once the user move a pawn to the opponents last row, the pawn promotion functionality is triggered to
     * promote the pawn to the desired piece.
     *
     * Since, the promotion will involve creating a new piece and inserting it on the board. We have two separate functions
     * for black and white. The respective function will be called by the "move" function in the game class.
     *
     */

    public void whitePawnPromotion (ChessBoard chessBoard, int row, int column ) {

        ChessPiece promotedPawn = null;
        Scanner whitePawnScan = new Scanner(System.in);
        int pawnSelection = 0;
        boolean isInvalidInput = true;

        while (isInvalidInput) {

            System.out.println("\n\nPromote the pawn to:\n1.Queen 2.Knight 3.Rook 4.Bishop");
            System.out.println("\nEnter the number: ");
            pawnSelection = whitePawnScan.nextInt();

            if (pawnSelection > 0 && pawnSelection < 5) { isInvalidInput = false; break; }
            else {  System.out.println("Please enter the number from the available options, select a number from (1 - 4)"); continue; }

        }

        switch (pawnSelection){
            case 1: promotedPawn = new Queen(ChessPiece.Color.WHITE,"W_Q", 0, column); break;
            case 2: promotedPawn = new Knight(ChessPiece.Color.WHITE,"W_N",0, column); break;
            case 3: promotedPawn = new Rook(ChessPiece.Color.WHITE,"W_R", 0, column); break;
            case 4: promotedPawn = new Bishop(ChessPiece.Color.WHITE,"W_B",0, column); break;

        }

        chessBoard.updateBoardPiece(promotedPawn, row, column);
    }

    public void blackPawnPromotion (ChessBoard chessBoard,  int row, int column ) {

        ChessPiece promotedPawn = null;
        Scanner blackPawnScan = new Scanner(System.in);
        int pawnSelection = 0;
        boolean isInvalidInput = true;

        while (isInvalidInput) {

            System.out.println("\n\nPromote the pawn to:\n1.Queen 2.Knight 3.Rook 4.Bishop");
            System.out.println("\nEnter the number: ");
            pawnSelection = blackPawnScan.nextInt();

            if (pawnSelection > 0 && pawnSelection < 5) { isInvalidInput = false; break; }
            else {  System.out.println("Please enter the number from the available options"); continue; }

        }

        switch (pawnSelection){
            case 1: promotedPawn = new Queen(ChessPiece.Color.BLACK,"B_Q", 7, column); break;
            case 2: promotedPawn = new Knight(ChessPiece.Color.BLACK,"B_N", 7, column); break;
            case 3: promotedPawn = new Rook(ChessPiece.Color.BLACK,"B_R", 7, column); break;
            case 4: promotedPawn = new Bishop(ChessPiece.Color.BLACK,"B_B", 7, column); break;
        }

        chessBoard.updateBoardPiece(promotedPawn, row, column);
    }

}
