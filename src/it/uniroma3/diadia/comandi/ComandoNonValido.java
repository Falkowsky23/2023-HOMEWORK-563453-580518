package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando {
	static final private String NOME = "non valido";

	@Override
	public void esegui(Partita partita) {
		IO io = partita.getIO();
		io.mostraMessaggio("Comando non valido!");
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