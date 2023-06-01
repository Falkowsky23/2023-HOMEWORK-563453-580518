package it.uniroma3.diadia.personaggi;

import java.util.TreeSet;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.ComparatoreStanzePerNumeroAttrezzi;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio{

	private static final String SALUTATO = "Quanta educazione!" +
			"In una stanza piena di attrezzi" + " avverrà la teletrasportazione";

	private static final String NONSALUTATO = "Quanta maleducazione!" +
			"In una stanza priva di attrezzi" + " avverrà la teletrasportazione";
	
	private static final String RISATA = "AHAHAHAHAHAHAHAHAHA";

	public Strega(String nome, String presentaz) {
		super(nome,presentaz);
	}

	@Override
	public String agisci(Partita partita) {
	        String msg;
	        TreeSet<Stanza> stanzePerNumeroAttrezzi = new TreeSet<Stanza>(new ComparatoreStanzePerNumeroAttrezzi());
	        for(Direzione direzione : partita.getStanzaCorrente().getDirezioni())
	            stanzePerNumeroAttrezzi.add(partita.getStanzaCorrente().getStanzaAdiacente(direzione));
	        if(super.haSalutato()) {
	            partita.getLabirinto().setStanzaCorrente(stanzePerNumeroAttrezzi.last());
	            msg = SALUTATO;
	        }
	        else {
	            partita.getLabirinto().setStanzaCorrente(stanzePerNumeroAttrezzi.first());
	            msg = NONSALUTATO;
	        }
	        return msg;
	    }
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita){
		partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
		return RISATA;
	}
}