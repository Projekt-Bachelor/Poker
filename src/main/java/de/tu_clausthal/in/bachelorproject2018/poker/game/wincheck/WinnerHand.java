package de.tu_clausthal.in.bachelorproject2018.poker.game.wincheck;

public class WinnerHand {

    /**
     * WinnerHand gibt zu der Handstatistic einen String zurück, den man dem Spieler ausgeben kann
     * Hierbei wird die Handevaluation in der Handstatisctic aufgeschlüsselt
     * @param handStatistic
     * @return HandString as String
     */
    public String getWinnerHandAsString( HandStatistic handStatistic){
        String result = "";
        switch (handStatistic.getHandEvaluation()[0]){
            case 0:
                result = "Der Gewinner hat mit eine Einzelkarte gewonnen: Die höchsten Einzelkarten waren: "+
                        handStatistic.getHandEvaluation()[1] + ", " +
                        handStatistic.getHandEvaluation()[2] + ", " +
                        handStatistic.getHandEvaluation()[3] + ", " +
                        handStatistic.getHandEvaluation()[4] + ", " +
                        handStatistic.getHandEvaluation()[5];
                        break;
            case 1:
                result = "Der Gewinner hat mit einem " + handStatistic.getHandEvaluation()[1] +
                        "er Paar gewonnen. Zusätzlich hat er noch folgende Karten als Einzelkarten: " +
                        handStatistic.getHandEvaluation()[2] + ", " +
                        handStatistic.getHandEvaluation()[3] + ", " +
                        handStatistic.getHandEvaluation()[4];
                        break;
            case 2:
                result = "Der Gewinner hat mit einem Doppelpaar gewonnen. Die Paare sind ein " +
                        handStatistic.getHandEvaluation()[1] + "er Paar und ein " +
                        handStatistic.getHandEvaluation()[2] + "er Paar. Zusätzlich hat er noch " +
                        handStatistic.getHandEvaluation()[3] + " als Einzelkarte.";
                        break;
            case 3:
                result = "Der Gewinner hat mit einem " + handStatistic.getHandEvaluation()[1] +
                        "er Drilling gewonnen. Zusätzlich hat er noch " +
                        handStatistic.getHandEvaluation()[2] + " und " +
                        handStatistic.getHandEvaluation()[3] +" als Einzelkarten.";
                        break;
            case 4:
                int straightlow = handStatistic.getHandEvaluation()[1]-4;
                result = "Der Gewinner hat mit einer Straße gewonnen. Die Straße geht von " +
                         + straightlow +" bis " + handStatistic.getHandEvaluation()[1];
                        break;
            case 5:
                result = "Der Gewinner hat mit einem Flush gewonnen. Dazu wurden folgende Karten benutzt. Alle" +
                        " sind in der Farbe " + handStatistic.getFlushSuit() + " : " +
                        handStatistic.getHandEvaluation()[1] + ", " +
                        handStatistic.getHandEvaluation()[2] + ", " +
                        handStatistic.getHandEvaluation()[3] + ", " +
                        handStatistic.getHandEvaluation()[4] + ", " +
                        handStatistic.getHandEvaluation()[5];
                        break;
            case 6:
                result = "Der Gewinner hat mit einem Full House gewonnen. Dieses besteht aus einem " +
                        handStatistic.getHandEvaluation()[1] + "er Drilling und einem " +
                        handStatistic.getHandEvaluation()[2] +"er Paar.";
                        break;
            case 7:
                result = "Der Gewinner hat mit einem Vierling gewonnen. Der Vierling hat den Wert " +
                        handStatistic.getHandEvaluation()[1]+" . Zusätzlich hat er noch die Einzelkarte " +
                        handStatistic.getHandEvaluation()[2];
                        break;
            case 8:
                result = "Der Gewinner hat mit einem Straightflush gewonnen. Der Straightflush ging von "+
                        handStatistic.getHandEvaluation()[5] +" bis " +
                        handStatistic.getHandEvaluation()[1] +" in der Farbe " +
                        handStatistic.getFlushSuit();
                        break;
            default:
                result = "Fehler";
                break;

        }


        return result;
    }
}
