package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoRegala extends AbstractComando{

	private static final String NOME = "regala";

	public ComandoRegala() {
		super.setNome(NOME);
	}

	@Override
	public void esegui(Partita partita) {
		IO io = partita.getIO();
		Borsa borsa = partita.getGiocatore().getBorsa();
		String regalo = super.getParametro();
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();

		if(personaggio != null){
			if(regalo == null) {
				io.mostraMessaggio("Cosa vuoi regalare?");
				return;
			}
			if(borsa.hasAttrezzo(regalo)) {
				io.mostraMessaggio(personaggio.riceviRegalo(borsa.getAttrezzo(regalo), partita));
			}
			else {
				io.mostraMessaggio("Non hai questo regalo dentro la borsa");
			}
		}
		else {
			io.mostraMessaggio("Non c'è nessun personaggio a cui dare il regalo!!");
		}
	}
}