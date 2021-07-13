package Game;

public class Move {

   private int[] oldPosition = new int[2];
   private int[] newPosition = new int[2];
   private ChessPiece chessPiece;

   public Move (ChessPiece chessPiece, int [] newPosition) {
       this.oldPosition = chessPiece.getCurrentPosition();
       this.newPosition = newPosition;
       this.chessPiece = chessPiece;
   }



}
