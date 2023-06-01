package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio {
	
	private static final String MESSAGGIO_DONO = "Sei un vero simpaticone, " +
			"con una mia magica azione, troverai un nuovo oggetto " +
			"per il tuo borsone!";
	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";
	private static final String MESSAGGIO_REGALO = "Ecco a te!! il peso dell'attrezzo è ora dimezzato";
	private Attrezzo attrezzo;
	
	public Mago(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.attrezzo = attrezzo;
	}
	
	@Override
	public String agisci(Partita partita) {
		String msg;
		if (this.attrezzo!=null) {
			partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
			this.attrezzo = null;
			msg = MESSAGGIO_DONO;
		}
		else {
			msg = MESSAGGIO_SCUSE;
		}
		return msg;
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita){
		int peso = attrezzo.getPeso();
		int pesoNuovo;
		if(peso%2 == 0) {
			pesoNuovo = peso/2;
		}
		else {
			pesoNuovo = (peso+1)/2;
		}
		partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
		attrezzo.setPeso(pesoNuovo);
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
		return MESSAGGIO_REGALO;
	}
}
