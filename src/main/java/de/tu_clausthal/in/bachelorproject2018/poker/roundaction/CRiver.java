package de.tu_clausthal.in.bachelorproject2018.poker.roundaction;

public class CRiver implements IRoundAction
{

    @Override
    public boolean stop()
    {
        return false;
    }

    @Override
    public IRoundAction get()
    {
        // Logik River

        return this;
    }
}
