package it.uniroma3.diadia;

import it.uniroma3.diadia.IOConsole.IOConsole;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

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

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Università, ma oggi è diversa dal solito...\n" +
			"Meglio andare al più presto in biblioteca a studiare. Ma dov'è?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissà!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};

	private Partita partita;
	private IOConsole io;

	public DiaDia(IOConsole io) {
		this.partita = new Partita();
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
		Comando comandoDaEseguire = new Comando(istruzione);

		String nome = comandoDaEseguire.getNome();
		if(nome==null) {
			io.mostraMessaggio("Devi digitare un comando");
			return false;
		}

		if (nome.equals("fine")) {
			this.fine(); 
			return true;
		} else if (nome.equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (nome.equals("aiuto"))
			this.aiuto();
		else if(nome.equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if(nome.equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else
			io.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			io.mostraMessaggio("Hai vinto!");
			return true;
		}
		else if(this.partita.getGiocatore().getCfu() == 0) {
			io.mostraMessaggio("Hai perso!");
			return true;
		}
		else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			io.mostraMessaggio(elencoComandi[i]+" ");
		io.mostraMessaggio("");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			io.mostraMessaggio("Dove vuoi andare?");
		else {
			Stanza prossimaStanza = null;
			Stanza stanzaCorrente = this.partita.getLabirinto().getStanzaCorrente();
			prossimaStanza = stanzaCorrente.getStanzaAdiacente(direzione);
			if (prossimaStanza == null)
				io.mostraMessaggio("Direzione inesistente");
			else {
				this.partita.getLabirinto().setStanzaCorrente(prossimaStanza);
				int cfu = this.partita.getCfu();
				this.partita.setCfu(--cfu);
			}
		}
		io.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
		io.mostraMessaggio("Cfu: " + partita.getCfu());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		io.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	/**
	 * Comando "Prendi".
	 */
	private void prendi(String attrezzo) {
		if(attrezzo==null)
			io.mostraMessaggio("Che attrezzo vuoi prendere?");
		else {
			Attrezzo preso = null;
			preso = this.partita.getLabirinto().getStanzaCorrente().getAttrezzo(attrezzo);
			if(preso==null)
				io.mostraMessaggio("Attrezzo inesistente.");
			else {
				this.partita.getLabirinto().getStanzaCorrente().removeAttrezzo(attrezzo);
				if(!(this.partita.getGiocatore().getBorsa().addAttrezzo(preso)))
					io.mostraMessaggio("Impossibile raccogliere l'oggetto! Non c'è abbastanza spazio.");
				else
					io.mostraMessaggio("Hai preso: "+ attrezzo + "!");
			}
		}
		io.mostraMessaggio(this.partita.getGiocatore().getBorsa().toString());
		io.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
		io.mostraMessaggio("Cfu: " + partita.getCfu());
	}

	/**
	 * Comando "Posa".
	 */
	private void posa(String attrezzo) {
		if(attrezzo==null)
			io.mostraMessaggio("Che attrezzo vuoi posare?");
		else {
			Attrezzo posa = null;
			posa = this.partita.getGiocatore().getBorsa().getAttrezzo(attrezzo);
			if(posa==null)
				io.mostraMessaggio("Attrezzo inesistente.");
			else {
				this.partita.getLabirinto().getStanzaCorrente().addAttrezzo(posa);
				this.partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo);
				io.mostraMessaggio("Hai posato: "+ attrezzo + "!");
			}
		}
		io.mostraMessaggio(this.partita.getGiocatore().getBorsa().toString());
		io.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
		io.mostraMessaggio("Cfu: " + partita.getCfu());
	}

	public static void main(String[] argc) {
		IOConsole io = new IOConsole();
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
	}
}