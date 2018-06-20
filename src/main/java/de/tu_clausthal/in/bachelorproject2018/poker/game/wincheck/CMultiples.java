package de.tu_clausthal.in.bachelorproject2018.poker.game.wincheck;

public class CMultiples implements IWinCheckAction {

    @Override
    public IWinCheckAction apply( final HandStatistic handStatistic )
    {
        //define the needed variables
        int sameCards = 1;
        int sameCards2 = 1;
        int largeGroupRank = 0;
        int smallGroupRank = 0;
        int[] orderedRank = new int[5];
        int orderedRankcounter = 0;

        /*
        final Map<CardValue,  Integer> l_counts = Arrays.stream(CardValue.values() ).collect( Collectors.toMap( i -> i, i -> handStatistic.getCount( i ) ) );

        IntStream.range( 4, 1 )
                 .flatMap( i -> l_counts.entrySet().stream().filter( n -> n.getValue() == i ) )

        */

        for (int i = 14; i > 1; i--) {
            if (handStatistic.getRankArray()[i] > sameCards) {
                //if we found a triple after we already found a pair, we save the pair before overwriting the triple
                if (sameCards != 1) {
                    sameCards2 = sameCards;
                    smallGroupRank = largeGroupRank;
                }
                //save the highest occurence
                sameCards = handStatistic.getRankArray()[i];
                largeGroupRank = i;
            } else if (handStatistic.getRankArray()[i] > sameCards2) {
                //if we found a second lower pair
                sameCards2 = handStatistic.getRankArray()[i];
                smallGroupRank = i;
            }
            //order every card not used in the evaluation before
            if (handStatistic.getRankArray()[i] >= 1 && orderedRankcounter < 5 && i != largeGroupRank && i != smallGroupRank) {
                orderedRank[orderedRankcounter] = i;
                orderedRankcounter++;
            }
        }

        //set the handevaluation depending on sameCards
        if (sameCards == 3) {
            if (sameCards2 >= 2 && 6 > handStatistic.getHandEvaluation()[0]) {
                //full house, save triple and pair
                handStatistic.setHandEvaluation(0, 6);
                handStatistic.setHandEvaluation(1, largeGroupRank);
                handStatistic.setHandEvaluation(2, smallGroupRank);
            } else if (3 > handStatistic.getHandEvaluation()[0]) {
                //save triple and high cards
                handStatistic.setHandEvaluation(0, 3);
                handStatistic.setHandEvaluation(1, largeGroupRank);
                handStatistic.setHandEvaluation(2, orderedRank[0]);
                handStatistic.setHandEvaluation(3, orderedRank[1]);
            }

        }
        if (sameCards == 2) {
            if (sameCards2 == 2 && 2 > handStatistic.getHandEvaluation()[0]) {
                //double pair, save both pairs and highcard
                handStatistic.setHandEvaluation(0, 2);
                handStatistic.setHandEvaluation(1, largeGroupRank);
                handStatistic.setHandEvaluation(2, smallGroupRank);
                handStatistic.setHandEvaluation(3, orderedRank[0]);
            } else if (1 > handStatistic.getHandEvaluation()[0]) {
                //just one pair, save pair and 3 highest cards
                handStatistic.setHandEvaluation(0, 1);
                handStatistic.setHandEvaluation(1, largeGroupRank);
                handStatistic.setHandEvaluation(2, orderedRank[0]);
                handStatistic.setHandEvaluation(3, orderedRank[1]);
                handStatistic.setHandEvaluation(4, orderedRank[2]);
            }
        }
        if (sameCards == 4 && 7 > handStatistic.getHandEvaluation()[0]) {
            //four of a kind, save four and highest card
            handStatistic.setHandEvaluation(0, 7);
            handStatistic.setHandEvaluation(1, largeGroupRank);
            handStatistic.setHandEvaluation(2, orderedRank[0]);
        }
        if (sameCards == 1 && 0 == handStatistic.getHandEvaluation()[0]) {
            //high card, save the 5 highest cards
            handStatistic.setHandEvaluation(0, 0);
            for (int i = 1; i < 6; i++) {
                handStatistic.setHandEvaluation(i, orderedRank[i - 1]);
            }
        }

        return this;
    }
}
