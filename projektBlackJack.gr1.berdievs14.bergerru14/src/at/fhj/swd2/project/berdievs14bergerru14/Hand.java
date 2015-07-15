package at.fhj.swd2.project.berdievs14bergerru14;

import java.io.IOException;

public class Hand extends Container
{
    private String playerCard = "";
    //private String handCard;
    private int sum;

    
    private boolean firstTime = true; // Wenn Spieler einsteigt kriegt automatisch eine Karte
    public boolean isFirstTime()
    {
        return firstTime;
    }
    public void setFirstTime(boolean firstTime)
    {
        this.firstTime = firstTime;
    }
    
    
    public String getPlayerCard()
    {
        return playerCard;
    }
    public void setPlayerCard(String playerCard)
    {
        this.playerCard = playerCard;
    }
    public int getSum()
    {
        return sum;
    }
    public void setSum(int sum)
    {
        this.sum = sum;
    }
    
    public Hand()
    {
	
    }
    
    public void getCard() throws IOException
    {
	throwCard();
	
	if (value == 11) // As Karte 
	{
	    if (sum >= 11)
	    {
		value = 1; // Vorteil für den Spieler
	    }
	}
	
	sum = sum + value; // Summieren Kartenwertigkeit der Spieler
	playerCard = element + "|" +   playerCard; // Anzeige der Karten
	System.out.println(playerCard);

	
	Card.showCard(playerCard); // Zeichnet die letzte Karte
	
	System.out.println(sum); //TODO: Übersichtlicher! Zeigt die derzeitige Gesamtsumme an

    }
    
}
