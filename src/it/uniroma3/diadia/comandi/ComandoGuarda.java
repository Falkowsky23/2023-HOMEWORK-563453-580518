package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando {
	static final private String NOME = "guarda";
	
	public ComandoGuarda(){
		super.setNome(NOME);
	}

	@Override
	public void esegui(Partita partita) {
		IO io = partita.getIO();
		io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		io.mostraMessaggio("Cfu: " + partita.getCfu());
		}
	
}