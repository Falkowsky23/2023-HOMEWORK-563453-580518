package it.uniroma3.diadia;

import java.io.StringReader;

import it.uniroma3.diadia.ambienti.CaricatoreLabirinto;
import it.uniroma3.diadia.ambienti.FormatoFileNonValidoException;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final public String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	public Partita partita;
	private IO io;

	public DiaDia(Labirinto labirinto,IO io) {
		this.partita = new Partita(labirinto,io);
		this.io = io;
	}

	public void gioca() {
		String istruzione;

		io.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do	
			istruzione = this.io.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   

	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		FabbricaDiComandi fabbrica = new FabbricaDiComandiRiflessiva();

		Comando comando = fabbrica.costruisci(istruzione);
		comando.esegui(this.partita);

		if(this.partita.isFinita()) {
			return true;
		}
		return false;
	}   

	public static void main(String[] argc) throws FormatoFileNonValidoException {
		String prova = "Stanze: biblioteca, N10, N11 \n"+
				"Stanze Bloccate: N18 nord pinza \n"+
				"Stanze Buie: AulaCampus lanterna \n"+
				"Stanze Magiche: Atrio \n"+
				"Inizio: N10 \n"+
				"Vincente: N11 \n"+
				"Attrezzi: martello 10 biblioteca, pinza 2 N10, osso 3 biblioteca, lanterna 4 N18 \n"+
				"Uscite: biblioteca nord N10, biblioteca sud N11, biblioteca est N18, N18 nord AulaCampus, N18 sud Atrio \n"+
				"Cani: Ugo, WOF WOF, osso, dente, 1, N10 \n"+
				"Maghi: Pippo, Faccio magie bellissime!, bacchetta, 2, biblioteca \n"+
				"Streghe: Marcella, Sono molto permalosa!, N18 \n";
		
		IO io = new IOConsole();
		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(prova));
		caricatore.carica();
		Labirinto labirinto = caricatore.getLabirinto();
		DiaDia gioco = new DiaDia(labirinto,io);
		gioco.gioca();
	}
}