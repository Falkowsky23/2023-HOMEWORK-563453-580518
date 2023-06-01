package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi extends AbstractComando {

	static final private String NOME = "prendi";

	public ComandoPrendi(){
		super.setNome(NOME);
	}

	@Override
	public void esegui(Partita partita) {

		IO io = partita.getIO();
		String attrezzo = super.getParametro();

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
}