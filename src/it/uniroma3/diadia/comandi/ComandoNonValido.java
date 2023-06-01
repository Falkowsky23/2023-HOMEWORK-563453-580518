package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando {
	static final private String NOME = "non valido";
	
	public ComandoNonValido(){
		super.setNome(NOME);
	}

	@Override
	public void esegui(Partita partita) {
		IO io = partita.getIO();
		io.mostraMessaggio("Comando non valido!");
	}
}