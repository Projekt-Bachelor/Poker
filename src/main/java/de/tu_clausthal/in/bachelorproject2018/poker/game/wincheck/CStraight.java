package de.tu_clausthal.in.bachelorproject2018.poker.game.wincheck;

public class CStraight implements IWinCheckAction {

    @Override
    public IWinCheckAction apply(final HandStatistic handStatistic) {
        //have to initialize with values
        boolean straight = false;
        int straightHigh = 0;
        //check for straight with ace as 1
        if (handStatistic.getRankArray()[14] >= 1 && handStatistic.getRankArray()[2] >= 1 && handStatistic.getRankArray()[3] >= 1 && handStatistic.getRankArray()[4] >= 1 && handStatistic.getRankArray()[5] >= 1) {
            straight = true;
            straightHigh = 5;
        }
        for (int i = 2; i < 11; i++) {
            //check fpr normal straight
            if (handStatistic.getRankArray()[i] >= 1 && handStatistic.getRankArray()[i + 1] >= 1 && handStatistic.getRankArray()[i + 2] >= 1 && handStatistic.getRankArray()[i + 3] >= 1 &&
                    handStatistic.getRankArray()[i + 4] >= 1) {
                straight = true;
                handStatistic.straightFound();
                //save highest element of straight
                straightHigh = i + 4;
            }
        }
        if (straight && 4 > handStatistic.getHandEvaluation()[0]) {
            handStatistic.setHandEvaluation(0, 4);
            handStatistic.setHandEvaluation(1, straightHigh);
        }


        return this;


    }
}
