package Game;

import Pieces.*;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {

    private ChessPiece[][] board = new ChessPiece[8][8];
    private List<ChessPiece> allPieces = new ArrayList<>();


    public ChessBoard () {
        // When a chess board is created the pieces automatically needs to be arranged
        arrangePieces();
        displayChessboard();

    }


    //Black Pieces
   private final King blackKing = new King(ChessPiece.Color.BLACK,"B_K", 0, 4);
   private final Queen blackQueen = new Queen(ChessPiece.Color.BLACK,"B_Q", 0, 3);
   private final Rook blackRook1 = new Rook(ChessPiece.Color.BLACK,"B_R", 0,0);
   private final Rook blackRook2 = new Rook(ChessPiece.Color.BLACK,"B_R",0, 7);
   private final Bishop blackBishop1 = new Bishop(ChessPiece.Color.BLACK,"B_B", 0,2);
   private final Bishop blackBishop2 = new Bishop(ChessPiece.Color.BLACK,"B_B", 0, 5);
   private final Knight darkKnight1 = new Knight(ChessPiece.Color.BLACK,"B_N", 0,1);
   private final Knight darkKnight2 = new Knight(ChessPiece.Color.BLACK,"B_N",0,6);
   private final Pawn blackPawn1 = new Pawn(ChessPiece.Color.BLACK,"B_P",1,0 );
   private final Pawn blackPawn2 = new Pawn(ChessPiece.Color.BLACK,"B_P",1,1 );
   private final Pawn blackPawn3 = new Pawn(ChessPiece.Color.BLACK,"B_P",1,2 );
   private final Pawn blackPawn4 = new Pawn(ChessPiece.Color.BLACK,"B_P",1,3 );
   private final Pawn blackPawn5 = new Pawn(ChessPiece.Color.BLACK,"B_P",1,4 );
   private final Pawn blackPawn6 = new Pawn(ChessPiece.Color.BLACK,"B_P",1,5 );
   private final Pawn blackPawn7 = new Pawn(ChessPiece.Color.BLACK,"B_P",1,6 );
   private final Pawn blackPawn8 = new Pawn(ChessPiece.Color.BLACK,"B_P",1,7 );

   
   //White Pieces
    
    private final King whiteKing = new King(ChessPiece.Color.WHITE,"W_K", 7, 4);
    private final Queen whiteQueen = new Queen(ChessPiece.Color.WHITE,"W_Q", 7, 3);
    private final Rook whiteRook1 = new Rook(ChessPiece.Color.WHITE,"W_R", 7,0);
    private final Rook whiteRook2 = new Rook(ChessPiece.Color.WHITE,"W_R",7, 7);
    private final Bishop whiteBishop1 = new Bishop(ChessPiece.Color.WHITE,"W_B",7,2);
    private final Bishop whiteBishop2 = new Bishop(ChessPiece.Color.WHITE,"W_B",7, 5);
    private final Knight whiteKnight1 = new Knight(ChessPiece.Color.WHITE,"W_N",7,1);
    private final Knight whiteKnight2 = new Knight(ChessPiece.Color.WHITE,"W_N",7,6);
    private final Pawn whitePawn1 = new Pawn(ChessPiece.Color.WHITE,"W_P",6,0 );
    private final Pawn whitePawn2 = new Pawn(ChessPiece.Color.WHITE,"W_P",6,1 );
    private final Pawn whitePawn3 = new Pawn(ChessPiece.Color.WHITE,"W_P",6,2 );
    private final Pawn whitePawn4 = new Pawn(ChessPiece.Color.WHITE,"W_P",6,3 );
    private final Pawn whitePawn5 = new Pawn(ChessPiece.Color.WHITE,"W_P",6,4 );
    private final Pawn whitePawn6 = new Pawn(ChessPiece.Color.WHITE,"W_P",6,5 );
    private final Pawn whitePawn7 = new Pawn(ChessPiece.Color.WHITE,"W_P",6,6 );
    private final Pawn whitePawn8 = new Pawn(ChessPiece.Color.WHITE,"W_P",6,7 );

    //Creates and adds the null pieces into the List
    private void setNullPieces () {
        for (int i=2; i<6; i++) {
            for (int j=0; j<8; j++){
                allPieces.add(new NullPiece(ChessPiece.Color.NULL, "   ", i, j ));
            }
        }
    }


    // Getters and Setters


    public ChessPiece[][] getBoard() { return board;}

    public void updateBoardPiece (ChessPiece chessPiece, int row,  int column) { this.board[row][column] = chessPiece; }

    public void updateBoardPieceToNull(int row, int column) { this.board[row][column] =  new NullPiece(ChessPiece.Color.NULL, "   ", row, column );   }

    public List<ChessPiece> getAllPieces() { return allPieces; }

    public void setAllPieces(List<ChessPiece> allPieces) { this.allPieces = allPieces; }




    // Adding Pieces to the list
    private void addPiecesToList (){
        // Add Black pieces to List
        allPieces.add(blackKing);allPieces.add(blackQueen);allPieces.add(blackRook1);allPieces.add(blackRook2);
        allPieces.add(blackBishop1);allPieces.add(blackBishop2);allPieces.add(darkKnight1);allPieces.add(darkKnight2);
        allPieces.add(blackPawn1);allPieces.add(blackPawn2);allPieces.add(blackPawn3);allPieces.add(blackPawn4);
        allPieces.add(blackPawn5);allPieces.add(blackPawn6);allPieces.add(blackPawn7);allPieces.add(blackPawn8);
        
        // Add White pieces to List
        allPieces.add(whiteKing);allPieces.add(whiteQueen);allPieces.add(whiteRook1);allPieces.add(whiteRook2);
        allPieces.add(whiteBishop1);allPieces.add(whiteBishop2);allPieces.add(whiteKnight1);allPieces.add(whiteKnight2);
        allPieces.add(whitePawn1);allPieces.add(whitePawn2);allPieces.add(whitePawn3);allPieces.add(whitePawn4);
        allPieces.add(whitePawn5);allPieces.add(whitePawn6);allPieces.add(whitePawn7);allPieces.add(whitePawn8);

    }


    // Arranging the pieces in the board
    private void arrangePieces () {

        // Adding pieces to the list
        addPiecesToList();
        setNullPieces();

        //Iterates through the list and gets the current position and
        for (int i=0; i < allPieces.size(); i++) {
          int row =  allPieces.get(i).getCurrentPosition()[0];
          int column = allPieces.get(i).getCurrentPosition()[1];


          board[row][column] = allPieces.get(i);
        }

    }


    // To get the "Letter Position" on the board, subtract the ASCII value from the 'a'


    // For everyMove, I'm planning to create a new chess

    public void displayChessboard () {
        System.out.println("     a     b     c     d     e     f     g     h");
        System.out.println("  -------------------------------------------------");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                String tile;
                if (board[i][j] == null) {
                    tile = "   ";
                } else {
                    tile = board[i][j].getNameOnBoard();
                }
                if (j == 0) System.out.print(board.length - i);
                System.out.print(" | " + tile);
            }
            System.out.print(" | " + (board.length - i));
            System.out.println();

            if (i != board.length - 1) {
                //| B_R | B_N | B_B | B_Q | B_K | B_B | B_N | B_R|
                System.out.print("  ------+-----+-----+-----+-----+-----+-----+------");
            } else {
                System.out.println("  -------------------------------------------------");
                System.out.println("     a     b     c     d     e     f     g     h");
            }
            System.out.println();
        }
    }









}
