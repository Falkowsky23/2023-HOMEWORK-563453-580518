package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {
	
	private Labirinto labirinto;
	private boolean finita;
	private Giocatore giocatore;
	private IO io;
	
	public Partita(Labirinto labirinto,IO io){
		this.labirinto = labirinto;
		this.finita = false;
		this.giocatore = new Giocatore();
		this.io = io;
	}

	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.labirinto.getStanzaCorrente() == this.labirinto.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		if (this.vinta()) {
			io.mostraMessaggio("Hai vinto!");
			return true;
		}
		else if(!this.giocatore.hasCfu()) {
			io.mostraMessaggio("Hai perso!");
			return true;
		}
		else return finita;
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}
	
	public Labirinto getLabirinto() {
		return this.labirinto;
	}

	public void setLabirinto(Labirinto labirinto) {
		this.labirinto = labirinto;
	}

	public Giocatore getGiocatore() {
		return giocatore;
	}
	
	public int getCfu() {
		return giocatore.getCfu();
	}
	
	public void setCfu(int cfu) {
		giocatore.setCfu(cfu);
	}
	
	public IO getIO(){
		return this.io;
	}
	
	public Stanza getStanzaCorrente() {
		return this.getLabirinto().getStanzaCorrente();
	}
}