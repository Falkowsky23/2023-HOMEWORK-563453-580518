package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.Configurazione;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Giocatore {
	static final private int CFU_INIZIALI = Configurazione.getCFU();
	private int cfu;
	private Borsa borsa;
	
	public Giocatore(){
		this.borsa = new Borsa();
		this.cfu = CFU_INIZIALI;
	}
	
	public boolean hasCfu(){
		return (this.cfu!=0);
	}

	public int getCfu() {
		return cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;
	}
	
	public void prendi(Attrezzo attrezzo){
		this.borsa.addAttrezzo(attrezzo);
	}
	
	public void posa(String attrezzo) {
		this.borsa.removeAttrezzo(attrezzo);
	}

	public Borsa getBorsa() {
		return borsa;
	}
}
