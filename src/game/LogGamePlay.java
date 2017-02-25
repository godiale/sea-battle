package game;

import java.text.MessageFormat;

public class LogGamePlay extends GamePlay {

    private class PlayerState {
        private final String ID;
        private final int MAX_MOVE;
        private IPlayer player;
        private IBoard  board;
        private boolean isWinner;
        private boolean lastHit;
        private int moveNumber;

        public PlayerState(String ID, IPlayer player, IBoard board) {
         // Must win after hitting all squares on the board!
            MAX_MOVE = board.getXSize() * board.getYSize();
            this.ID     = ID;
            this.player = player;
            this.board  = board;
        }

        public void placeShips() {
            player.placeShips(board, ships); // ships are common
            System.out.println(ID + ": ships");
            board.printShips();
        }

        public void strike(IBoard board) {
            moveNumber++;
            Point strike = player.nextStrike();
            Answer answer = board.makeStrike(strike);
            player.recordMove(strike, answer);
            System.out.println(MessageFormat.format("{0}({1}): >{2} --> {3}",
                                                     ID, moveNumber, strike, answer));
            if (answer == Answer.FINISHED || answer == Answer.KILLED || answer == Answer.HIT) {
                lastHit = true;
                if (answer == Answer.FINISHED) {
                    isWinner = true;
                }
            } else {
                lastHit = false;
            }
        }

        boolean tooManyMoves() {
            return moveNumber >= MAX_MOVE;
        }
    }

    @Override
    public void run() {
        PlayerState state_A = new PlayerState("A", player_A, board_A);
        PlayerState state_B = new PlayerState("B", player_B, board_B);

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
