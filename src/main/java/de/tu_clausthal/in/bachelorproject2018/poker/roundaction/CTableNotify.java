package de.tu_clausthal.in.bachelorproject2018.poker.roundaction;

public class CTableNotify implements IRoundAction
{
    @Override
    public boolean stop()
    {
        return false;
    }

    @Override
    public IRoundAction get()
    {
        System.out.println("blub");
        return this;
    }
}
