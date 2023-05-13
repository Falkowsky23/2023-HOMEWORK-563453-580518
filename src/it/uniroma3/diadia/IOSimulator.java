package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IOSimulator implements IO{

	private List<String> messaggi;
	private int messaggiStampati;
	private List<String> letture;
	private int lettureLette;
	private int messaggiMostrati;

	public IOSimulator(String[] letture){
		this.messaggiStampati = 0;
		this.messaggi = new ArrayList<>();
		this.lettureLette = 0;
		this.messaggiMostrati = 0;
		this.letture = new ArrayList<>(Arrays.asList(letture));
	}

	@Override
	public void mostraMessaggio(String messaggio){
		this.messaggi.add(messaggio);
		this.messaggiStampati++;
	}

	@Override
	public String leggiRiga(){
		String letta = this.letture.get(lettureLette);
		this.lettureLette++;
		return letta;
	}
	
	public String nextMessaggio() {
        String next = this.messaggi.get(messaggiMostrati);
        this.messaggiMostrati++;
        return next;
    }

    public boolean hasMessaggio() {
        return this.messaggiMostrati < this.messaggiStampati;
    }
}