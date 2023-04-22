package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando {

	static final private String NOME = "posa";
	private String attrezzo;

	public ComandoPosa(String attrezzo) {
		this.attrezzo = attrezzo;
	}

	@Override
	public void esegui(Partita partita) {

		IO io = partita.getIO();

		if(attrezzo==null) {
			io.mostraMessaggio("Devi selezionare il nome dell'attrezzo che vuoi posare!");
		} else {
			Attrezzo posa = null;
			posa = partita.getGiocatore().getBorsa().getAttrezzo(attrezzo);
			if(posa==null)
				io.mostraMessaggio("Attrezzo inesistente.");
			else {
				if(partita.getLabirinto().getStanzaCorrente().addAttrezzo(posa)) {
					partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo);
					io.mostraMessaggio("Hai posato: "+ attrezzo + "!");
				}
				else
					io.mostraMessaggio("Non c'è più spazio per attrezzi nuovi nella stanza");
			}
		}
		io.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
	}

	@Override
	public void setParametro(String parametro) {
		this.attrezzo = parametro;

	}

	@Override
	public String getNome() {
		return NOME;
	}

	@Override
	public String getParametro(){
		return this.attrezzo;
	}
}