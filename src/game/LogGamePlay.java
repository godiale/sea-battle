package game;

import java.text.MessageFormat;

public class LogGamePlay extends GamePlay {

    @Override
    public void run() {
        System.out.println(MessageFormat.format("Starting game: board={0}, ships={1}",
                                                                board_A,   ships));

        // Someone must win after hitting all squares on the board
        final int MAX_MOVE = board_A.getXSize() * board_A.getYSize();

        player_A.placeShips(board_A, ships);
        System.out.println("A: ships");
        board_A.printShips();

        player_B.placeShips(board_B, ships);
        System.out.println("B: ships");
        board_B.printShips();

        boolean winner_A = false;
        boolean winner_B = false;
        int moveNumber = 0;

        while (!winner_A && !winner_B) {
            if (moveNumber+1 > MAX_MOVE) {
                break; // Logic defect, no-one wins
            } else {
                ++moveNumber;
            }
            if (!winner_B) {
                Point strike = player_A.nextStrike();
                Answer answer = board_B.makeStrike(strike);
                player_A.recordMove(strike, answer);
                System.out.println(MessageFormat.format("A({0}): >{1} --> {2}",
                                                         moveNumber, strike, answer));
                if (answer == Answer.FINISHED) {
                    winner_A = true;
                }
            }
            if (!winner_A) {
                Point strike = player_B.nextStrike();
                Answer answer = board_A.makeStrike(strike);
                player_B.recordMove(strike, answer);
                System.out.println(MessageFormat.format("B({0}): >{1} --> {2}",
                                                         moveNumber, strike, answer));
                if (answer == Answer.FINISHED) {
                    winner_B = true;
                }
            }
        }
        if (moveNumber <= MAX_MOVE) {
            System.out.println(MessageFormat.format("Game over after {0} moves",
                                                     moveNumber));
        } else {
            System.out.println(MessageFormat.format("Forced draw after {0} moves",
                                                     moveNumber));
        }
    }
}
