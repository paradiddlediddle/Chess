package Pieces;

import Game.ChessBoard;
import Game.ChessPiece;

public class Knight extends ChessPiece {

    public Knight(Color color, String nameOnBoard, int row, int column) {
        super(color, nameOnBoard, row, column);
        super.setTypeOfPiece("Knight");
    }

    @Override
    public void availableMoves(ChessBoard chessBoard, int row, int column) {

        addIfValidMove(chessBoard, row-2, column+1); // shortRight top
        addIfValidMove(chessBoard, row+2, column+1); // shortRight bottom
        addIfValidMove(chessBoard, row-1, column+2); // longRight Top
        addIfValidMove(chessBoard, row+1, column+2); // longRight bottom
        addIfValidMove(chessBoard, row-1, column-2); // longLeft top
        addIfValidMove(chessBoard, row+1, column-2); // longLeft bottom
        addIfValidMove(chessBoard, row-2, column-1); // shortLeft top
        addIfValidMove(chessBoard, row+2, column-1); // shortLeft bottom
    }

    private void addIfValidMove (ChessBoard chessBoard, int row, int column) {

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
            return;
        }

    }





}
