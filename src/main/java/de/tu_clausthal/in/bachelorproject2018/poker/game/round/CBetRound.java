package de.tu_clausthal.in.bachelorproject2018.poker.game.round;

public final class CBetRound implements IRoundAction {

    boolean stop = false;

    @Override
    public boolean stop() {
        return stop;
    }

    @Override
    public IRoundAction get() {

        stop = true;
        return null;
    }
}
