package de.tu_clausthal.in.bachelorproject2018.poker.Network_Objects;

import de.tu_clausthal.in.bachelorproject2018.poker.game.action.CAllIn;
import de.tu_clausthal.in.bachelorproject2018.poker.game.action.CCall;
import de.tu_clausthal.in.bachelorproject2018.poker.game.action.IAction;

import java.text.MessageFormat;

public enum EActions {
    CALL,
    CHECK,
    FOLD,
    ALLIN;

    public IAction actionFactory(){
        switch ( this ){
            case CALL:
                return new CCall();

            /*case FOLD:
                return new

            case CHECK:
                return new */

            case ALLIN:
                return new CAllIn();

                default:
                    throw new RuntimeException(MessageFormat.format("Aktion [{0}] nicht bekannt", this));
        }
    }
}
