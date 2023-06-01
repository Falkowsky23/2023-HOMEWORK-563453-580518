package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza{
	private static final String DESCRIZIONE_STANZA_BLOCCATA_1 = "Devi posare ";
	private static final String DESCRIZIONE_STANZA_BLOCCATA_2 = " per accedere alla direzione ";
	private Direzione direzioneBloccata;
	private String attrezzoSbloccante;
	
	public StanzaBloccata(String nome,Direzione direzioneBloccata,String attrezzoSbloccante){
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.attrezzoSbloccante = attrezzoSbloccante;
	}
	
	@Override
	public Stanza getStanzaAdiacente(Direzione direzione) {
		if(direzione.equals(this.direzioneBloccata) && !super.hasAttrezzo(attrezzoSbloccante))
			return this;
		return super.getStanzaAdiacente(direzione);
	}
	
	@Override
	public String getDescrizione(){
		if(!super.hasAttrezzo(attrezzoSbloccante)) {
			return super.getDescrizione()+ "\n" +
					DESCRIZIONE_STANZA_BLOCCATA_1 + this.attrezzoSbloccante + 
					DESCRIZIONE_STANZA_BLOCCATA_2 + this.direzioneBloccata;
		}
		else
			return super.getDescrizione();
	}
}
