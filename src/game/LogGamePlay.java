package game;

import java.text.MessageFormat;

public class LogGamePlay extends GamePlay {

    @Override
    public void run() {
        // Must win after hitting all squares on the board!
        final int MAX_MOVE_A = board_A.getXSize() * board_A.getYSize();
        final int MAX_MOVE_B = board_B.getXSize() * board_B.getYSize();

        player_A.placeShips(board_A, ships);
        System.out.println("A: ships");
        board_A.printShips();

        player_B.placeShips(board_B, ships);
        System.out.println("B: ships");
        board_B.printShips();

        boolean winner_A = false, hit_A = false;
        boolean winner_B = false, hit_B = false;
        int moveNumber_A = 0, moveNumber_B = 0;

        while (!winner_A && !winner_B) {
            if (moveNumber_A >= MAX_MOVE_A && moveNumber_B >= MAX_MOVE_B) {
                break; // Logic defect from both sides
            }
            if (!hit_B) {
                moveNumber_A++;
                Point strike = player_A.nextStrike();
                Answer answer = board_B.makeStrike(strike);
                player_A.recordMove(strike, answer);
                System.out.println(MessageFormat.format("A({0}): >{1} --> {2}",
                                                         moveNumber_A, strike, answer));
                if (answer == Answer.FINISHED || answer == Answer.KILLED || answer == Answer.HIT) {
                    hit_A = true;
                    if (answer == Answer.FINISHED) {
                        winner_A = true;
                    }
                } else {
                    hit_A = false;
                }
            }
            if (!hit_A) {
                moveNumber_B++;
                Point strike = player_B.nextStrike();
                Answer answer = board_A.makeStrike(strike);
                player_B.recordMove(strike, answer);
                System.out.println(MessageFormat.format("B({0}): >{1} --> {2}",
                                                         moveNumber_B, strike, answer));
                if (answer == Answer.FINISHED || answer == Answer.KILLED || answer == Answer.HIT) {
                    hit_B = true;
                    if (answer == Answer.FINISHED) {
                        winner_B = true;
                    }
                }
                else {
                    hit_B = false;
                }
            }
        }
        if (!winner_A && !winner_B) {
            System.out.println(MessageFormat.format("Forced draw after moves done: A={0}, B={1}",
                                                     moveNumber_A, moveNumber_B));
        }
    }
}
