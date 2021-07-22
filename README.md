# Chess
A Multiplayer Chess game in command line terminal

There are 2 Folders inside "src" folder,

1. Game: The game folder contains classes for the chessboard, chesspiece, player, move and the logic of the game in the "Game" class.

2. Pieces: The pieces folder contains an individual class for each type of piece. These pieces are a subclass of the chessPiece, with each piece contatining its own move set and rules. The individual pieces are then imported into the game class to represent a piece on the board.

At the end of each game a txt file containing a list of all the moves that were made during the game along with the games result, will be exported. The date and time at which the game ends will the be the default name for the exported file.
