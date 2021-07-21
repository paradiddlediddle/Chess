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

     // Currently not being used anywhere, but if we store all the moves of the game in "Move" format,
    // We can simulate the game once again.
    // Leaving this class for future development of the application.

}
