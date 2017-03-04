package game;

import java.text.MessageFormat;

public class LogGamePlay extends GamePlay {

    private boolean printShips;

    private class PlayerState {
        private final int MAX_MOVE;
        private IPlayer player;
        private IBoard  board;
        private boolean isWinner;
        private boolean lastHit;
        private int moveNumber;

        public PlayerState(IPlayer player, IBoard board) {
            // Must win after hitting all squares on the board!
            MAX_MOVE = board.getXSize() * board.getYSize();
            this.player = player;
            this.board  = board;
        }

        public void placeShips() {
            player.placeShips(board, ships);

            if (printShips) {
                System.out.println(player.getName() + ": ships");
                board.printShips();
            }
        }

        public void strike(IBoard board) {
            moveNumber++;
            Point strike = player.nextStrike();
            Answer answer = board.makeStrike(strike);
            player.recordMove(strike, answer);

            if (answer == Answer.FINISHED || answer == Answer.KILLED || answer == Answer.HIT) {
                lastHit = true;
                if (answer == Answer.FINISHED) {
                    isWinner = true;
                }
            } else {
                lastHit = false;
            }

            System.out.println(
                    MessageFormat.format(
                            "{0}({1}): >{2} --> {3}",
                             player.getName(), moveNumber, strike, answer));
        }

        boolean tooManyMoves() {
            return moveNumber >= MAX_MOVE;
        }
    }

    @Override
    public void run() {
        if (board_A.getXSize() != board_B.getXSize() ||
            board_A.getYSize() != board_B.getYSize()) {
            throw new IllegalStateException("Boards of players should be equal!");
        }

        if (player_A instanceof ConsolePlayer ||
            player_B instanceof ConsolePlayer) {
            printShips = false;
        }
        else {
            printShips = true;
        }

        System.out.println(MessageFormat.format(
                "Starting game on board {0} to {1}",
                new Point(0,0), new Point(board_A.getXSize()-1, board_A.getYSize()-1)));

        PlayerState state_A = new PlayerState(player_A, board_A);
        PlayerState state_B = new PlayerState(player_B, board_B);

        state_A.placeShips();
        state_B.placeShips();

        while (!state_A.isWinner && !state_B.isWinner) {
            if (state_A.tooManyMoves() && state_B.tooManyMoves()) {
                break; // Logic defect from both sides
            }
            if (!state_B.lastHit) {
                state_A.strike(board_B);
            }
            if (!state_A.lastHit) {
                state_B.strike(board_A);
            }
        }
        if (!state_A.isWinner && !state_B.isWinner) {
            System.out.println(MessageFormat.format("Forced draw after moves done: A={0}, B={1}",
                                                     state_A.moveNumber, state_B.moveNumber));
        }
    }
}
