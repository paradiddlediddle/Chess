package Pieces;

import Game.ChessBoard;
import Game.ChessPiece;

import java.util.List;
import java.util.Scanner;

public class Pawn extends ChessPiece {


    public Pawn(Color color, String nameOnBoard, int row, int column) {
        super(color, nameOnBoard, row, column);
    }


    @Override
    public void availableMoves(ChessBoard chessBoard, int row, int column) {

        // check if it is black or a white first
        // then check if it is already moved or not

        // black piece moves towards the bottom of the board
        if (isPieceBlack()) {

                moveForward(chessBoard, row+1, column); // 1 Step
                diagonalCapture(chessBoard, row+1, column+1); // Bottom right;
                diagonalCapture(chessBoard, row+1, column-1); // Bottom left;

            //Can move 2 Steps only if it is untouched
            if (isUntouched()) {  moveForward(chessBoard, row+2, column); } // 2 Steps

            //Pawn Promotion

        }

        // white piece moves towards the top of the board
        else {

            moveForward(chessBoard, row-1, column); // 1 Step
            diagonalCapture(chessBoard, row-1, column+1); // Bottom right;
            diagonalCapture(chessBoard, row-1, column-1); // Bottom left;

            //Can move 2 Steps only if it is untouched
            if (isUntouched()) {  moveForward(chessBoard, row-2, column); } // 2 Steps

        }



    }

    private void moveForward (ChessBoard chessBoard, int row, int column) {

        // Base Case: If the row/column is out of bounds or there is anyPiece in front of it.
        if (row < 0 || row > 7 || column < 0 || column > 7 ||
                chessBoard.getBoard()[row][column] != null) { return; }


        else {
            setMove(new int[] {row, column});
        }

    }

    private void diagonalCapture (ChessBoard chessBoard, int row, int column) {

        // Base Case: if there is no opposite color pieces on the adjacent side or if there are no pieces at all
        // We can't capture any, so we simply return. (Also considering out of bounds).
        if (row < 0 || row > 7 || column < 0 || column > 7 ||
                chessBoard.getBoard()[row][column] == null ||
                chessBoard.getBoard()[row][column].isPieceBlack() == isPieceBlack()) { return; }

        else {
            // There is an adjacent opposite color piece
            setMoveAndCapture(new int[] {row, column});
        }

    }


    // ONCE THE USER MOVES THE PAWN TO THE FINAL SPOT, THE PROMOTION SHOULD BE TRIGGERED
    // Has to be down when the piece is actually moved
    // Once th Piece is moved to the last row, then provide options.
    // If the pawn is right before the last piece, just show the last spot as an option,show additional pawn promotion moves only when the user moves it.
    // Need to implement castling when king is selected
    // Are row and columns required as a parameter for king castling and pawn promotion?
    // Start to design actually moving the pieces
    // Alternate player turns
    // Initial conditions and statuses for the game
    // Winning abd draw conditions
    // Recording of games


    private void whitePawnPromotion (ChessBoard chessBoard, int row, int column ) {

        ChessPiece promotedPawn = null;
        Scanner whitePawnScan = new Scanner(System.in);
        int pawnSelection = 0;
        boolean isInvalidInput = true;

        while (isInvalidInput) {

            System.out.println("Promote the pawn to:\n1.Queen 2.Knight 3.Rook 4.Bishop");
            System.out.println("Enter the number: ");
            pawnSelection = whitePawnScan.nextInt();

            if (pawnSelection > 0 && pawnSelection < 5) { isInvalidInput = false; break; }
            else {  System.out.println("Please enter the number from the available options"); continue; }

        }

        switch (pawnSelection){
            case 1: promotedPawn = new Queen(ChessPiece.Color.WHITE,"W_Q", 0, column); break;
            case 2: promotedPawn = new Knight(ChessPiece.Color.WHITE,"W_N",0, column); break;
            case 3: promotedPawn = new Rook(ChessPiece.Color.WHITE,"W_R", 0, column); break;
            case 4: promotedPawn = new Bishop(ChessPiece.Color.WHITE,"W_B",0, column); break;

        }

        chessBoard.getBoard()[row][column] = promotedPawn;
    }

    private void blackPawnPromotion (ChessBoard chessBoard, int row, int column ) {

        ChessPiece promotedPawn = null;
        Scanner blackPawnScan = new Scanner(System.in);
        int pawnSelection = 0;
        boolean isInvalidInput = true;

        while (isInvalidInput) {

            System.out.println("Promote the pawn to:\n1.Queen 2.Knight 3.Rook 4.Bishop");
            System.out.println("Enter the number: ");
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

        chessBoard.getBoard()[row][column] = promotedPawn;
    }



    // Getters and Setters



}
