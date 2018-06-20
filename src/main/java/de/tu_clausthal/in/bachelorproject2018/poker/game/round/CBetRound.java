package de.tu_clausthal.in.bachelorproject2018.poker.game.round;

import de.tu_clausthal.in.bachelorproject2018.poker.game.player.IPlayer;


public final class CBetRound implements IRoundAction {
    /**
     * Spielerobjekt, für den der Bet ausgeführt wird
     */
    private final IPlayer m_player;
    private boolean stop = false;

    public CBetRound( final IPlayer p_player )
    {
        m_player = p_player;
    }

    @Override
    public boolean stop() {
        return stop;
    }

    @Override
    public IRoundAction get() {
        // @todo hier wird der Bet nur für den Spieler m_player ausgeführt

        /*
        if (nachricht noch nicht geschickt)
            schicke nachricht
            this -> neues betround object auf warte stack legen
        */

        stop = true;
        return null;
    }
}
