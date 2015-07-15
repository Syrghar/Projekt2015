package at.fhj.swd2.project.berdievs14bergerru14;

import java.io.*;

import java.util.*;

public class Card
{
    //private static String list;
    private String s_name;
    private String s_farbe;
    private int v_wert;

    public List<String> listKarten = new LinkedList<String>(); // Kartenobjekte

    public Card()
    {

	// Kartendeck erzeugen
	for (int i = 1; i <= 4; i++)
	{
	    for (int j = 1; j <= 13; j++)
	    {
		// Auswahl der Farbe
		switch (i)
		{
		    case 1:
			s_farbe = "kreuz";
			break;
		    case 2:
			s_farbe = "karo";
			break;
		    case 3:
			s_farbe = "pik";
			break;
		    case 4:
			s_farbe = "herz";
			break;
		}

		// Auswahl des Kartennamen und Kartenwerts
		switch (j)
		{
		    case 1:
			s_name = "Zwei";
			v_wert = 2;
			listKarten.add(s_farbe + s_name + v_wert);
			break;
		    case 2:
			s_name = "Drei";
			v_wert = 3;
			listKarten.add(s_farbe + s_name + v_wert);
			break;
		    case 3:
			s_name = "Vier";
			v_wert = 4;
			listKarten.add(s_farbe + s_name + v_wert);
			break;
		    case 4:
			s_name = "Fuenf";
			v_wert = 5;
			listKarten.add(s_farbe + s_name + v_wert);
			break;
		    case 5:
			s_name = "Sechs";
			v_wert = 6;
			listKarten.add(s_farbe + s_name + v_wert);
			break;
		    case 6:
			s_name = "Sieben";
			v_wert = 7;
			listKarten.add(s_farbe + s_name + v_wert);
			break;
		    case 7:
			s_name = "Acht";
			v_wert = 8;
			listKarten.add(s_farbe + s_name + v_wert);
			break;
		    case 8:
			s_name = "Neun";
			v_wert = 9;
			listKarten.add(s_farbe + s_name + v_wert);
			break;
		    case 9:
			s_name = "Zehn";
			v_wert = 10;
			listKarten.add(s_farbe + s_name + v_wert);
			break;
		    case 10:
			s_name = "Bube";
			v_wert = 10;
			listKarten.add(s_farbe + s_name + v_wert);
			break;
		    case 11:
			s_name = "Dame";
			v_wert = 10;
			listKarten.add(s_farbe + s_name + v_wert);
			break;
		    case 12:
			s_name = "Koenig";
			v_wert = 10;
			listKarten.add(s_farbe + s_name + v_wert);
			break;
		    case 13:
			s_name = "As";
			v_wert = 11;
			listKarten.add(s_farbe + s_name + v_wert);
			break;
		}

	    }

	}
    }

    //TODO: try catch block
    public static String showCard(String sign) throws IOException 
    {
	sign = Hand.element;
	
	FileReader fr = new FileReader("files/Card_utf8.txt");
	BufferedReader in = new BufferedReader(fr);

	String zeile = null;
	while ((zeile = in.readLine()) != null)
	{
	    if (zeile.contains(sign) == true)
	    {
		String karte = "";
		for (int i = 0; i < 6; i++)
		{
		    karte += in.readLine() + "\n";
		}
		System.out.printf(karte);
		
	    }
	}
	in.close();
	return sign;
    }
}
