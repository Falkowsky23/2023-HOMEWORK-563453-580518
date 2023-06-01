package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa extends AbstractComando {

	static final private String NOME = "posa";	

	public ComandoPosa(){
		super.setNome(NOME);
	}

	@Override
	public void esegui(Partita partita) {

		IO io = partita.getIO();
		String attrezzo = super.getParametro();

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
}