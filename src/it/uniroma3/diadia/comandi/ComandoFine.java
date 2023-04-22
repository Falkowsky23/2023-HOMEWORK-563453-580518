package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando {
	static final private String NOME = "fine";

	@Override
	public void esegui(Partita partita) {
		partita.getIO().mostraMessaggio("Grazie di aver giocato!"); 
		partita.setFinita();
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