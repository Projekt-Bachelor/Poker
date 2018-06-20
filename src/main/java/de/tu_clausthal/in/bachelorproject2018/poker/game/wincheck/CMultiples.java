package de.tu_clausthal.in.bachelorproject2018.poker.game.wincheck;

import de.tu_clausthal.in.bachelorproject2018.poker.game.PlayerHand;


public class CMultiples implements IWinCheckAction {

    @Override
    public IWinCheckAction apply( final PlayerHand playerHand )
    {
        //define the needed variables
        int sameCards = 1;
        int sameCards2 = 1;
        int largeGroupRank = 0;
        int smallGroupRank = 0;
        int[] orderedRank = new int[5];
        int orderedRankcounter = 0;

        /*
        final Map<CardValue,  Integer> l_counts = Arrays.stream(CardValue.values() ).collect( Collectors.toMap( i -> i, i -> playerHand.getCount( i ) ) );

        IntStream.range( 4, 1 )
                 .flatMap( i -> l_counts.entrySet().stream().filter( n -> n.getValue() == i ) )

        */

        for (int i = 14; i > 1; i--) {
            if (playerHand.getRankArray()[i] > sameCards) {
                //if we found a triple after we already found a pair, we save the pair before overwriting the triple
                if (sameCards != 1) {
                    sameCards2 = sameCards;
                    smallGroupRank = largeGroupRank;
                }
                //save the highest occurence
                sameCards = playerHand.getRankArray()[i];
                largeGroupRank = i;
            } else if (playerHand.getRankArray()[i] > sameCards2) {
                //if we found a second lower pair
                sameCards2 = playerHand.getRankArray()[i];
                smallGroupRank = i;
            }
            //order every card not used in the evaluation before
            if (playerHand.getRankArray()[i] >= 1 && orderedRankcounter < 5 && i != largeGroupRank && i != smallGroupRank) {
                orderedRank[orderedRankcounter] = i;
                orderedRankcounter++;
            }
        }

        //set the handevaluation depending on sameCards
        if (sameCards == 3) {
            if (sameCards2 >= 2 && 6 > playerHand.getHandEvaluation()[0]) {
                //full house, save triple and pair
                playerHand.setHandEvaluation(0, 6);
                playerHand.setHandEvaluation(1, largeGroupRank);
                playerHand.setHandEvaluation(2, smallGroupRank);
            } else if (3 > playerHand.getHandEvaluation()[0]) {
                //save triple and high cards
                playerHand.setHandEvaluation(0, 3);
                playerHand.setHandEvaluation(1, largeGroupRank);
                playerHand.setHandEvaluation(2, orderedRank[0]);
                playerHand.setHandEvaluation(3, orderedRank[1]);
            }

        }
        if (sameCards == 2) {
            if (sameCards2 == 2 && 2 > playerHand.getHandEvaluation()[0]) {
                //double pair, save both pairs and highcard
                playerHand.setHandEvaluation(0, 2);
                playerHand.setHandEvaluation(1, largeGroupRank);
                playerHand.setHandEvaluation(2, smallGroupRank);
                playerHand.setHandEvaluation(3, orderedRank[0]);
            } else if (1 > playerHand.getHandEvaluation()[0]) {
                //just one pair, save pair and 3 highest cards
                playerHand.setHandEvaluation(0, 1);
                playerHand.setHandEvaluation(1, largeGroupRank);
                playerHand.setHandEvaluation(2, orderedRank[0]);
                playerHand.setHandEvaluation(3, orderedRank[1]);
                playerHand.setHandEvaluation(4, orderedRank[2]);
            }
        }
        if (sameCards == 4 && 7 > playerHand.getHandEvaluation()[0]) {
            //four of a kind, save four and highest card
            playerHand.setHandEvaluation(0, 7);
            playerHand.setHandEvaluation(1, largeGroupRank);
            playerHand.setHandEvaluation(2, orderedRank[0]);
        }
        if (sameCards == 1 && 0 == playerHand.getHandEvaluation()[0]) {
            //high card, save the 5 highest cards
            playerHand.setHandEvaluation(0, 0);
            for (int i = 1; i < 6; i++) {
                playerHand.setHandEvaluation(i, orderedRank[i - 1]);
            }
        }

        return this;
    }
}
