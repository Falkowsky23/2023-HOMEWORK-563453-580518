package it.uniroma3.diadia;

public class IOSimulator implements IO{

	private String[] messaggi;
	private int messaggiStampati;
	private String[] letture;
	private int lettureLette;
	private int messaggiMostrati;

	public IOSimulator(String[] letture){
		this.messaggiStampati = 0;
		this.messaggi = new String[100];
		this.lettureLette = 0;
		this.messaggiMostrati = 0;
		this.letture = letture;
	}

	@Override
	public void mostraMessaggio(String messaggio){
		this.messaggi[this.messaggiStampati] = messaggio;
		this.messaggiStampati++;
	}

	@Override
	public String leggiRiga(){
		String letta = this.letture[this.lettureLette];
		this.lettureLette++;
		return letta;
	}
	
	public String mostraMessaggio() {
        String next = this.messaggi[this.messaggiMostrati];
        this.messaggiMostrati++;
        return next;
    }

    public boolean hasMessaggio() {
        return this.messaggiMostrati < this.messaggiStampati;
    }
}