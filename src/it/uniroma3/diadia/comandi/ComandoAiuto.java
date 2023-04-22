package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando {

	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa","guarda"};
	static final private String NOME = "aiuto";

	@Override
	public void esegui(Partita partita) {

		IO io = partita.getIO();

		for(int i=0; i< elencoComandi.length; i++) {
			io.mostraMessaggio(elencoComandi[i]+" ");
		}
		io.mostraMessaggio("");

	}

	@Override
	public void setParametro(String parametro) {
		return;
	}

	@Override
	public String getNome() {
		return NOME;
	}

	@Override
	public String getParametro(){
		return null;
	}
}