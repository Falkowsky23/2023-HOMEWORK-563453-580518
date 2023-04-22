package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando {

	static final private String NOME = "vai";
	private String direzione;

	public ComandoVai(String direzione) {
		this.direzione = direzione;
	}

	@Override
	public void esegui(Partita partita) {

		IO io = partita.getIO();

		if(this.direzione==null) {
			io.mostraMessaggio("Devi scegliere una direzione dove andare!");
			return;
		} else {
			Stanza prossimaStanza = null;
			Stanza stanzaCorrente = partita.getLabirinto().getStanzaCorrente();
			prossimaStanza = stanzaCorrente.getStanzaAdiacente(direzione);
			if (prossimaStanza == null) {
				io.mostraMessaggio("Direzione inesistente");
				return;
			}
			if(prossimaStanza == stanzaCorrente){
				io.mostraMessaggio("la direzione è bloccata!");
			}
			else {
				partita.getLabirinto().setStanzaCorrente(prossimaStanza);
				int cfu = partita.getCfu();
				partita.setCfu(--cfu);
			}
		}
		io.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
		io.mostraMessaggio("Cfu: " + partita.getCfu());
	}

	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;
	}

	@Override
	public String getNome() {
		return NOME;
	}

	@Override
	public String getParametro(){
		return this.direzione;
	}
}