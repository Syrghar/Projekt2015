package at.fhj.swd2.project.berdievs14bergerru14;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlackJack
{
    static Scanner scanner = new Scanner(System.in); // Scanner erstellen zum Auslesen
    static List<Player> player = new ArrayList<Player>(); // ObjektListe erstellen weil man nicht weiss wieviele
							    // Spieler
    static List<Hand> spielerHand = new ArrayList<Hand>(); // Hand Objekte erstellen

    static int countPlayer;
    static int nextPlayer;

    static Container deck = new Container();
    static Hand dealerHand = new Hand();

    public static void main(String[] args) throws IOException
    {

	System.out.println(" -- Willkommen bei BlackJack -- ");

	System.out.println("========================================");
	System.out.println("\n Wieviele Spieler sollen mitspielen? ");
	System.out.println("========================================");

	// bei Fehler, keine Zahl oder größerer Wert als 10 eingegeben, wird der Wert nachfolgend auf 1 gesetzt und die
	// Schleife nochmal durchlaufen.
	int a = 0;
	do
	{
	    a = 0;
	    String inputPlayerCount = scanner.nextLine(); // eingabe anzahl der spieler

	    /*
	     * Könnte möglich sein, dass keine Zahl eingegeben wird. Hier wird daher geprüft ob eine Zahl eingegeben
	     * wurde. Und dem Spieler bei Falscheingabe eine Meldung ausgegeben
	     */
	    try
	    {
		countPlayer = Integer.parseInt(inputPlayerCount); // konvertiere string -> int

		// es ist eine max. Anzahl von 4 Spielern erlaubt, wenn größer, erneute Eingabe möglich
		if (countPlayer > 4)
		{
		    System.out.println("Max. Anzahl der Spieler ist 4.");
		    System.out.println("Bitte Anzahl der Spieler korrekt eingeben.");

		    a = 1;
		}
	    }
	    // Dieser Teil wird ausgeführt, wenn der Spieler keine Zahl, für die ANzahl der Spieler, eingegeben hat
	    catch (NumberFormatException nfe)
	    {
		System.out.println("Bitte Anzahl der Spieler korrekt eingeben. Muss eine Zahl sein. ");

		a = 1;
	    }
	}
	while (a == 1);

	int numberCount;// interner Zähler

	for (int i = 0; i < countPlayer; i++)
	{
	    numberCount = i + 1;// weil es keinen 0. Spieler gibt NUR für TEXTAUSGABE

	    System.out.println("========================================");
	    System.out.println("Wie heisst der " + numberCount + ". Spieler ?");
	    System.out.println("========================================");
	    String inputPlayerName = scanner.nextLine();
	    player.add(new Player()); // Objekt erstellen (Spieler)
	    spielerHand.add(new Hand()); // erstelle Hand zum Spieler
	    player.get(i).setName(inputPlayerName); // der 0. Spieler erhält einen Namen

	    System.out.println("========================================");
	    System.out.println("Wie hoch ist " + player.get(i).getName() + "'s Kredit? ");
	    System.out.println("========================================");
	    // Hier wird geprüft ob für den Kredit tatsächlich ein Ganzzahlwert angegeben wurde. Wenn nicht, wird eine
	    // Fehlermeldung ausgegeben und b auf 1 gesetzt.
	    int b = 0;
	    do
	    {
		b = 0;
		String inputPlayerCreditString = scanner.nextLine();

		// Prüfen ob für den Kredit ein Ganzahlwert angegebn wurde und wenn nicht Spieler darüber informieren
		try
		{
		    int inputPlayerCredit = Integer.parseInt(inputPlayerCreditString);
		    player.get(i).setCredits(inputPlayerCredit); // Der 0. Spieler bekommt Geld
		}
		catch (NumberFormatException nfe)
		{
		    System.out.println("Kredit bitte als Ganzzahlwert eingeben. ");

		    b = 1;
		}

	    }
	    while (b == 1);

	}

	// System.out.println(player.get(0).name + player.get(0).credits + "\n"); // Den 0. (1.) Spieler anzeigen

	// Karte stappelEins = new Karte(6); // 6 Packungen
	// stappelEins.showCard();

	nextPlayer = countPlayer;
	nextPlayer -= countPlayer; // fängt beim ersten Spieler an

	startGame();

    }

    /*
     * Place bets
     */

    public static void placeBet()
    {
	if (player.get(nextPlayer).getCredits() != 0) // Wenn am Konto mehr als 0 
	{
	    System.out.println("========================================");
	    System.out.println(player.get(nextPlayer).getName() + " Einsatz:"); // Dann Einsatz
	    System.out.println("========================================");
	    /*
	     * Im Falle, dass der Spieler für`s setzen keinen Wert als Ganzzahl angibt muss ein Fehler ausgegeben werden
	     * und der Spieler die Möglichkeit haben nochmal zu setzen. Wenn ein Fehler auftritt wird c auf 1 gesetzt.
	     */
	    int c = 0;
	    do
	    {
		c = 0;
		String inputPlayerBetString = scanner.nextLine(); // Eingabe

		try
		{
		    int inputPlayerBet = Integer.parseInt(inputPlayerBetString); // Eingabe in INT

		    if (player.get(nextPlayer).getCredits() < inputPlayerBet) // EInsatz kleiner als Konto
		    {
			System.out.println("Nicht genug Kredit bitte einen kleineren Wert eingeben. Kleiner gleich: "
				+ player.get(nextPlayer).getCredits());
			placeBet();
		    }
		    else
		    {
			player.get(nextPlayer).setBet(inputPlayerBet); // Setzen
		    }
		}
		catch (NumberFormatException nfe)
		{
		    System.out.println("Bet bitte als Ganzzahlwert eingeben. ");

		    c = 1;
		}
	    }
	    while (c == 1);
	}
	else // Wenn Konto 0
	{
	    System.out.println("========================================");
	    System.out.println(player.get(nextPlayer).getName() + " Hat kein GELD MEHR !!");
	    System.out.println("========================================");
	    player.get(nextPlayer).setActive(false); // Inaktiv weil kein Geld
	    // nextPlayer++;
	}
    }

    /*
     * Spielmethode
     */

    public static void startGame() throws IOException
    {
	if (dealerHand.isFirstTime() == true) // Ist der Dealer das erste mal dran
	{
	    System.out.println("========================================");
	    System.out.println("Dealers Hand:");
	    dealerHand.getCard(); // Dann Karte erhalten
	    dealerHand.setFirstTime(false); // nicht mehr das 1. mal gesetzt
	}

	if (spielerHand.get(nextPlayer).isFirstTime() == true && player.get(nextPlayer).isActive() == true) // Ist das erste mal und hat geld
	{
	    placeBet();
	    System.out.println("========================================");
	    System.out.println(player.get(nextPlayer).getName() + "'s Hand:");
	    System.out.println("========================================");
	    spielerHand.get(nextPlayer).getCard();
	    spielerHand.get(nextPlayer).setFirstTime(false); // -> damit der Spieler automatisch eine karte kriegt
	}

	else if (spielerHand.get(nextPlayer).isFirstTime() == true && player.get(nextPlayer).isActive() == false) // Wenn kein geld
	{
	    System.out.println("========================================");
	    System.out.println(player.get(nextPlayer).getName() + " ist ausgeschieden");
	    System.out.println("========================================");
	    turnNextPlayer(); // Naechster Spieler
	}

	if (player.get(nextPlayer).getCredits() >= player.get(nextPlayer).getBet()
		&& player.get(nextPlayer).isActive() == true) // Wenn er noch was am Konto hat zum Setzen
	{
	    System.out.println("========================================");
	    System.out.println(player.get(nextPlayer).getName() + " Hit or Stand?");
	    System.out.println("========================================");
	}

	String spielOption = scanner.nextLine();
	switch (spielOption)
	{
	    case "h":
		spielerHand.get(nextPlayer).getCard(); // erhält eine Karte
		check(); // prüft
		turnNextPlayer(); // 
		break;
	    case "s": // bleibt bei seinem Kartenwert stehen
		nextPlayer++; // der nächste Spieler dran
		turnNextPlayer();
		break;
	    default:
		System.out.println("ERROR");
	}
    }

    /*
     * Check ob gewonnen
     */

    private static void check()
    {
	if (spielerHand.get(nextPlayer).getSum() == 21) // Hat er gleich die 21 ?
	{
	    player.get(nextPlayer).setActive(false);
	    player.get(nextPlayer)
		    .setCredits(player.get(nextPlayer).getCredits() + 2 * player.get(nextPlayer).getBet()); // doppelt GEwonnen
													    // BET

	    System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
	    System.out.println(player.get(nextPlayer).getName() + " hat gewonnen");
	    System.out.println("Kredit: " + player.get(nextPlayer).getCredits());
	    System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

	    // spielerHand.get(nextPlayer).setSum(0);

	    nextPlayer++;
	}
	else if (spielerHand.get(nextPlayer).getSum() > 21) // hat er gleich verloren
	{
	    player.get(nextPlayer).setActive(false);

	    System.out.println(player.get(nextPlayer).isActive());

	    player.get(nextPlayer).setCredits(player.get(nextPlayer).getCredits() - player.get(nextPlayer).getBet()); // weniger
														      // BET

	    System.out.println("----------------------------------------");
	    System.out.println(player.get(nextPlayer).getName() + ": BUST !");
	    System.out.println("Kredit: " + player.get(nextPlayer).getCredits());
	    System.out.println("----------------------------------------");

	    // spielerHand.get(nextPlayer).setSum(0);

	    nextPlayer++;
	}
    }

    /*
     * ENDGAME
     */

    private static void endGame() throws IOException
    {
	while (dealerHand.getSum() < 17) // Soft 17
	{
	    dealerHand.getCard(); // solange karte nehmen bis oder über 17 hat
	}

	for (int i = 0; i < countPlayer; i++) // So oft durchlaufen so wie Spieleranzahl
	{

	    if (dealerHand.getSum() == 21) // Wenn dealer 21
	    {
		if (player.get(i).isActive() == true) // wenn Spieler kleiner als 21
		{
		    player.get(i).setCredits(player.get(i).getCredits() - player.get(i).getBet()); // weniger BET
		    System.out.println("----------------------------------------");
		    System.out.println(player.get(i).getName() + " hat verloren");
		    System.out.println("Kredit: " + player.get(i).getCredits());
		    System.out.println("----------------------------------------");

		}

		
	    }
	    else if (dealerHand.getSum() > 21 && player.get(i).isActive() == true) // Spieler unter 21-> gewonnen
	    {
		player.get(i).setCredits(player.get(i).getCredits() + 2 * player.get(i).getBet()); // doppelt BET
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println(player.get(i).getName() + " hat gewonnen");
		System.out.println("Kredit: " + player.get(i).getCredits());
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

		// nextRound();
	    }
	    else if (dealerHand.getSum() < 21 && player.get(i).isActive() == true) // Wenn beide unter 21
	    {
		if (spielerHand.get(i).getSum() >= dealerHand.getSum()) // Wenn spieler groesser als der Dealer aber beide unter 21
		{
		    player.get(i).setCredits(player.get(i).getCredits() + 2 * player.get(i).getBet()); // doppelt BET GEWONNEN
		    System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		    System.out.println(player.get(i).getName() + " hat gewonnen" + player.get(nextPlayer).isActive());
		    System.out.println("Kredit: " + player.get(i).getCredits());
		    System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		}
		else if (spielerHand.get(i).getSum() < dealerHand.getSum()) // Wenn spieler kleiner als der Dealer aber beide unter 21
		{

		    player.get(i).setCredits(player.get(i).getCredits() - player.get(i).getBet()); // weniger BET Verloren
		    System.out.println("----------------------------------------");
		    System.out.println(player.get(i).getName() + " hat verloren");
		    System.out.println("Kredit: " + player.get(i).getCredits());
		    System.out.println("----------------------------------------");

		}
	    }
	    else
	    {
		// System.out.println(player.get(i).getName()+" hat verloren");
	    }
	}
	nextRound(); // Nächste Runde

    }

    /*
     * NEXT ROUND
     */

    private static void nextRound() throws IOException
    {
	dealerHand.setSum(0); // Wertigkeit vom Dealer auf 0
	dealerHand.setFirstTime(true); // Wieder das erste mal
	dealerHand.setPlayerCard(""); // Seine Spielkarten werden quasi entfernt

	for (int i = 0; i < countPlayer; i++) // für jeden Spieler
	{
	    spielerHand.get(i).setSum(0);// Wertigkeit vom Dealer auf 0
	    spielerHand.get(i).setPlayerCard("");// Seine Spielkarten werden quasi entfernt
	    spielerHand.get(i).setFirstTime(true);// Wieder das erste mal
	    player.get(i).setActive(true); // Sind alle aktiv
	}
	nextPlayer -= countPlayer; // Zähler der Spieler wieder auf Null damit der 1. Spieler als erster anfangen kann
	startGame(); // Starte Spiel 
    }

    /*
     * Nächster Spieler
     */

    private static void turnNextPlayer() throws IOException
    {
	if (nextPlayer == countPlayer) // War das der Letzte Spieler ?
	{
	    System.out.println("========================================");
	    System.out.println("Dealer ist dran !");
	    System.out.println("========================================");
	    dealerHand.getCard(); // Karte erhalten
	    deck.mixContainer(); // mischen
	    endGame(); // Runde vorbei !
	}
	else if (nextPlayer < countPlayer)
	{
	    startGame();
	}
    }
}
