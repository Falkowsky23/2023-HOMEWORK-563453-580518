package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando {

	static final private String NOME = "vai";

	public ComandoVai(){
		super.setNome(NOME);
	}

	@Override
	public void esegui(Partita partita) {

		IO io = partita.getIO();
		String direzione = super.getParametro();

		if(direzione==null) {
			io.mostraMessaggio("Devi scegliere una direzione dove andare!");
			return;
		} 
		Stanza prossimaStanza = null;
		Stanza stanzaCorrente = partita.getLabirinto().getStanzaCorrente();
		Direzione dir = null;
		try{
			dir = Direzione.valueOf(direzione.toUpperCase());
		}
		finally {
			if(dir == null) {
				io.mostraMessaggio("Direzione inesistente");
				return;
			}
			prossimaStanza = partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(dir);
			if(prossimaStanza == null) {
				io.mostraMessaggio("Direzione non disponibile");
				return;
			}
			if(prossimaStanza == stanzaCorrente){
				io.mostraMessaggio("la direzione è bloccata!");
				return;
			}
			partita.getLabirinto().setStanzaCorrente(prossimaStanza);
			int cfu = partita.getCfu();
			partita.setCfu(--cfu);


			io.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
			io.mostraMessaggio("Cfu: " + partita.getCfu());
		}
	}
}