package game;

import java.text.MessageFormat;

public class LogGamePlay extends GamePlay {

    @Override
    public void run() {
        System.out.println(MessageFormat.format("Starting game: board={0}, ships={1}",
                                                                attack_A,  ships));

        // Someone must win after hitting all squares on the board
        final int MAX_MOVE = attack_A.getXSize() * attack_A.getYSize();

        player_A.placeShips(defend_A, ships);
        player_B.placeShips(defend_B, ships);

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
                Point strike = player_A.nextStrike(defend_A, attack_A);
                Answer answer = defend_B.checkStrike(strike);
                attack_A.recordStrike(strike);
                defend_B.recordStrike(strike);
                System.out.println(MessageFormat.format("A({0}): >{1} --> {2}",
                                                         moveNumber, strike, answer));
                if (answer == Answer.FINISHED) {
                    winner_A = true;
                }
            }
            if (!winner_A) {
                Point strike = player_B.nextStrike(defend_B, attack_B);
                Answer answer = defend_A.checkStrike(strike);
                attack_B.recordStrike(strike);
                defend_A.recordStrike(strike);
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
