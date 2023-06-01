package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio{
	
	private final String morso = "ARGHHHH!!";
	private final String buono = "GNAM GNAM!!";
	private Attrezzo regaloCane;
	private String ciboPrefe;
	private boolean posato;
	
	public Cane(String nome, String presentaz,String cibo, Attrezzo regaloCane) {
		super(nome,presentaz);
		this.regaloCane = regaloCane;
		this.ciboPrefe = cibo;
		this.posato = false;
	}
	
	@Override
	public String agisci(Partita partita) {
		int cfu = partita.getCfu();
		partita.getGiocatore().setCfu(--cfu);
		return morso;
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita){
		partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
		if(attrezzo.getNome().equals(ciboPrefe)){
			if(!posato) {
			partita.getStanzaCorrente().addAttrezzo(regaloCane);
			posato = true;
			}
			return buono;
		}
		else {
			partita.getStanzaCorrente().addAttrezzo(attrezzo);
			return this.agisci(partita);
		}
	}
}
