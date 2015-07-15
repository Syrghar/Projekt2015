package at.fhj.swd2.project.berdievs14bergerru14;

import java.util.*;

public class Container
{
    private String cardValue;
    protected int value;
    
    //private String card;
    protected static String element = ""; // String wandeln
    
    
    private Card karte = new Card();
    
    public Container()
    {
	Collections.shuffle(karte.listKarten); // Mischen
    }
    
    protected void throwCard()
    {
	element = ""+ karte.listKarten.get(0); // Puffer damit nicht für immer weg
	String[] out = element.split("[a-zA-Z]"); // Splitte nach Zeichen entsteht nur Zahl/Wert
	for (int i=0; i < out.length;i++)
	{
	    cardValue = out[i]; // 11
	}
	
	value = Integer.parseInt(cardValue); // Konvertiere in INT
	
	karte.listKarten.remove(0); // Lösche das erste elemnt
    }
    
    protected void mixContainer()
    {
	Collections.shuffle(karte.listKarten);
	karte.listKarten.add(element); // An letzter Stelle 
    }
}