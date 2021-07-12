package Game;

public class Move {

   private int[] oldPosition = new int[2];
   private int[] newPosition = new int[2];

   public Move (ChessPiece piece, int [] newPosition) {
       this.oldPosition = piece.getCurrentPosition();
       this.newPosition = newPosition;
   }



}
