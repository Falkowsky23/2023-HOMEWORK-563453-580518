package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoSaluta extends AbstractComando{
	
	private String NOME = "saluta";
	private final String MSG = "Chi dovrei salutare?...";
	
	public ComandoSaluta() {
		super.setNome(NOME);
	}
	
	@Override
	public void esegui(Partita partita) {
		IO io = partita.getIO();
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if(partita.getStanzaCorrente().getPersonaggio()!=null){
			io.mostraMessaggio(personaggio.saluta());
		}
		else {
			io.mostraMessaggio(MSG);
		}
	}
}