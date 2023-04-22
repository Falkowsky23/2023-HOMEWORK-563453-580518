package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando {

	static final private String NOME = "prendi";
	private String attrezzo;

	public ComandoPrendi(String attrezzo) {
		this.attrezzo = attrezzo;
	}

	@Override
	public void esegui(Partita partita) {

		IO io = partita.getIO();

		if(attrezzo==null) {
			io.mostraMessaggio("Devi selezionare un attrezzo da prendere!");
		} else {
			Attrezzo preso = null;
			preso = partita.getStanzaCorrente().getAttrezzo(attrezzo);
			if(preso==null)
				io.mostraMessaggio("Attrezzo inesistente.");
			else {
				if(!(partita.getGiocatore().getBorsa().addAttrezzo(preso)))
					io.mostraMessaggio("Impossibile raccogliere l'oggetto! Non c'è abbastanza spazio.");
				else {
					io.mostraMessaggio("Hai preso: "+ attrezzo + "!");
					partita.getStanzaCorrente().removeAttrezzo(attrezzo);
				}
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