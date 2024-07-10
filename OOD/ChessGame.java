/**
 * Functional Requirement
 * 1. Two player compete with each other by choosing piece to move through the board.
 * 2. If 1 player captures a piece, remove the piece.
 * 3. If the piece is a pawn reaching the back rank, promote it.
 * 4. If the move is a castling, set the new position of the rook accordingly.
 *    But a king and rook can only castle if they haven't moved, so you need to keep track of that.
 *    And if the king moves through a check to castle, that's disallowed, too.
 * 5. If the move results in a stalemate or checkmate, the game is over.
 */

public class ChessGame {
    /**
     * Game
     * 1) Contains the Board and 2 Players
     * 2) Process player's moves via the Commands List (for history tracking)
     */
    public class Game{
        static Board board; // singleton
        Player p1;
        Player p2;

        public Game() {
            board = new Board();
        }

        public boolean enterPlayer(Player p) {
            if(p1 == null)
                this.p1 = p;
            else if(p2 == null)
                this.p2 = p;
            else
                return false;

            board.initialize(p);
            return true;
        }

        public void processTurn(Player p) {
            // Player make a command and until it is valid
            // System input
            do{
                Command cmd = new Command(input);
                p.addCommand(cmd);
            }while(!board.executeMove(p));
        }

        public startGame(){
            // player enter the game:
            enterPlayer(new ComputerPlayer("Computer"));
            enterPlayer(new HumanPlayer("Bill"));

            while(true) {
                processTurn(p1);
                if(this.board.getWin()) {
                    System.out.println("P1 win!");
                    break;
                }
                processTurn(p2);
                if(this.board.getWin()) {
                    System.out.println("P2 win!");
                    break;
                }
            }
        }
    }

    /**
     * Board (Singleton):
     * 1) Hold spots with 8*8
     * 2) Initialize the piece when game start
     * 3) Move Piece
     * 4) Remove Piece
     */

    public class Board{

        private Spot[][] spots;
        private boolean win; // mark the win or not

        public Board(){
            win = false;
            spots = new Spot[8][8];
        }

        public void initialize(Player p){
            // put the pieces with initial status
            for(int i=0; i<p.getPieces().size(); i++){
                spots[p.getPieces().get(i).getX()][p.getPieces().get(i).getY()].occupySpot(p.getPieces().get(i));
            }
        }

        public boolean executeMove(Player p) {
            Command cmd = p.getCurrentCmd();
            Piece piece = cmd.getPiece();

            // check the move step is valid for piece
            if(!piece.validMove(this, cmd.curX, cmd.curY, cmd.desX, cmd.desY)) {
                // if not valid cmd remove the command and return false
                p.removeCurrentCmd();
                return false;
            }

            // check the two pieces side
            if(spots[cmd.desX][cmd.desY] != null && spots[cmd.desX][cmd.desY].color == piece.color)
                return false;

            // check and change the state on spot
            Piece taken = spots[cmd.desX][cmd.desY].occupySpot(piece);
            if(taken != null &&taken.getClass().getName().equals("King"))
                board.win = true;
            spots[cmd.curX][cmd.curY].releaseSpot;
            return true;
        }

        public boolean getWin() {
            return win;
        }
    }

    /**
     * Spot:
     * 1) Hold Pieces
     */
    public class Spot {
        int x;
        int y;
        Piece piece;

        public Spot(int x, int y) {
            super();
            this.x = x;
            this.y = y;
            piece = null;
        }

        // return original piece
        public Piece occupySpot(Piece piece){
            Piece origin = this.piece;
            //if piece already here, delete it, i. e. set it dead
            if(this.piece != null) {
                this.piece.setAvailable(false);
            }
            //place piece here
            this.piece = piece;
            return origin;
        }

        public boolean isOccupied() {
            if(piece != null)
                return true;
            return false;
        }

        public Piece releaseSpot() {
            Piece releasedPiece = this.piece;
            this.piece = null;
            return releasedPiece;
        }

        public Piece getPiece() {
            return this.piece;
        }
    }

    /**
     * Player (Abstract):
     * 1) Has a list of piece reference it owns.
     * 2) Concreted classes for Human and Computer players
     */
    public class Player {

        public int color;

        private List<Piece> pieces = new ArrayList<>();

        private List<Command> cmds = new ArrayList<>();

        public Player(int color) {
            super();
            this.color = color;
            initializePieces();
        }

        public List<Piece> getPieces() {
            return pieces;
        }


        public void initializePieces(){
            if(this.color == 1){
                for(int i=0; i<8; i++){ // draw pawns
                    pieces.add(new Pawn(true,i,2, 1));
                }
                pieces.add(new Rook(true, 0, 0, 1));
                pieces.add(new Rook(true, 7, 0, 1));
                pieces.add(new Bishop(true, 2, 0, 1));
                pieces.add(new Bishop(true, 5, 0, 1));
                pieces.add(new Knight(true, 1, 0, 1));
                pieces.add(new Knight(true, 6, 0, 1));
                pieces.add(new Queen(true, 3, 0, 1));
                pieces.add(new King(true, 4, 0, 1));
            }
            else{
                for(int i=0; i<8; i++){ // draw pawns
                    pieces.add(new Pawn(true,i,6, 0));
                }
                pieces.add(new Rook(true, 0, 7, 0));
                pieces.add(new Rook(true, 7, 7, 0));
                pieces.add(new Bishop(true, 2, 7, 0));
                pieces.add(new Bishop(true, 5, 7, 0));
                pieces.add(new Knight(true, 1, 7, 0));
                pieces.add(new Knight(true, 6, 7, 0));
                pieces.add(new Queen(true, 3, 7, 0));
                pieces.add(new King(true, 4, 7, 0));
            }

        }
    }

    public class HumanPlayer extends Player {
        public HumanPlayer(int color) {
            super(color);
            //TODO 
        }
    }

    /**
     * Piece (Abstract):
     * 1) Hold the color to represent the affiliation.
     * 2) Extended by concreted classes with 8 Pawns, 2 Rooks, 2 Bishops, 2 Knights, 1 Queen, 1 King
     * 3) Concreted classes define the detail step approach
     */
    public class Piece {
        private int x;
        private int y;

        private boolean available; // mark the live or dead
        private int color; // mark the owner

        public Piece(boolean available, int x, int y, int color) {
            super();
            this.available = available;
            this.x = x;
            this.y = y;
            this.color = color;
        }


        public boolean isAvailable() {
            return available;
        }
        public void setAvailable(boolean available) {
            this.available = available;
        }
        public int getX() {
            return x;
        }
        public void setX(int x) {
            this.x = x;
        }
        public int getY() {
            return y;
        }
        public void setY(int y) {
            this.y = y;
        }

        public boolean isValid(Board board, int fromX, int fromY, int toX, int toY){
            // different by character of piece
        }

    }

    public class King extends Piece{
        public King(boolean available, int x, int y, int color) {
            super(available, x, y, color);
        }

        @Override
        public boolean isValid(Board board, int fromX, int fromY, int toX, int toY) {
            //TODO
            return true;
        }
    }
    // ..... for Queen, Rook, Bishop, Pawn

    /**
     * Command
     * Piece
     * Destination x, y
     */
    public class Command {
        Piece piece;
        int curX, curY, desX, desY;
        public Command(Piece piece, int curX, int curY, int desX, int desY) {
            this.piece = piece;
            this.curX = curX;
            this.curY = curY;
            this.desX = desX;
            this.desY = desY;
        }
    }
}
