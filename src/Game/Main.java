package Game;

public class Main {

    public static void main(String[] args) {
	// write your code here
       Game game = new Game();
    }
}


// Will need a chessBoard 8x8


// Pieces
// Should be able to move the pieces around
// Generate all the possible moves available. Even if a piece can reach somewhere,
// if there is another piece of that same color that should bot be considered as a possible move.


// Obstruction of other pieces:
//1. Check whether the slot is empty or not
//2. If the slot is not empty
// (if the piece is of the opposite color, you should be able to remove the piece and place the current players piece.)


/**
 * Task - 1: (GAMEPLAY)
 * 1. The player needs to choose a piece that he needs to move.
 * 2. Once the piece is selected, we need to display all the possible moves and let the user make the move.
 * (There should two types of moves, a) need to move the piece  b) need to move and eliminate the piece.)
 * 3. Remaining pieces left should be updates and check if winning condition is met
 * 4. Pass the move to the next player
 *
 * Task - 2: (Printing the Board / Exiting the Game )
 *
 * Task - 3: (Recording the gameplay)
 *
 */