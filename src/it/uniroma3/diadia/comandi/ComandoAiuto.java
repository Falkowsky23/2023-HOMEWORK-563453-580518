package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando{

	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa","guarda","interagisci","saluta","regala"};
	static final private String NOME = "aiuto";

	public ComandoAiuto() {
		super.setNome(NOME);
	}

	@Override
	public void esegui(Partita partita) {

		IO io = partita.getIO();

		for(int i=0; i< elencoComandi.length; i++) {
			io.mostraMessaggio(elencoComandi[i]+" ");
		}
		io.mostraMessaggio("");
	}
}