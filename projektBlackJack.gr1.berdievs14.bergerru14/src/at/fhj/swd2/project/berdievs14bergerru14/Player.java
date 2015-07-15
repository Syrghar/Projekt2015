package at.fhj.swd2.project.berdievs14bergerru14;

public class Player
{
    private int id; // kann man schmeissen
    private String name;
    private int credits; // Quasi KONTO
    private int bet; // Einsatz
    private boolean active; // Aktiv wenn Spieler gleich verloren bzw gleich gewonnen hat dann wird auf inaktiv gesetzt (active=false)

    
    public boolean isActive()
    {
        return active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getCredits()
    {
        return credits;
    }

    public void setCredits(int credits)
    {
        this.credits = credits;
    }


    
    
    
    public int getBet()
    {
        return bet;
    }

    public void setBet(int bet)
    {
        this.bet = bet;
    }


    public Player()
    {
	active = true;
    }
}


