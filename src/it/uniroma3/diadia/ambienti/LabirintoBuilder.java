package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder {
	Labirinto labirinto;
	Map<String, Stanza> stanze;
	Stanza ultima;
	
	public LabirintoBuilder(){
		labirinto = new Labirinto();
		stanze = new HashMap<>();
	}
	
	public Labirinto getLabirinto() {
		return labirinto;
	}

	public LabirintoBuilder addStanzaIniziale(String nomeStanza){
		Stanza stanza = new Stanza(nomeStanza);
		this.labirinto.setStanzaCorrente(stanza);
		this.stanze.put(nomeStanza, stanza);
		this.ultima = stanza;
		return this;
	}
	
	public LabirintoBuilder addStanzaVincente(String nomeStanza){
		Stanza stanzaVincente = new Stanza(nomeStanza);
		labirinto.setStanzaVincente(stanzaVincente);
		this.stanze.put(nomeStanza, stanzaVincente);
		this.ultima = stanzaVincente;
		return this;
	}
	
	public LabirintoBuilder addAdiacenza(String partenza, String destinazione, String direzione){
		Stanza stanzaPartenza = stanze.get(partenza);
		Stanza stanzaDestinazione = stanze.get(destinazione);
		stanzaPartenza.impostaStanzaAdiacente(direzione, stanzaDestinazione);
		return this;
	}
	
	public LabirintoBuilder addAttrezzo(String attrezzo, int peso) {
		Attrezzo a = new Attrezzo(attrezzo, peso);
		this.ultima.addAttrezzo(a);
		return this;
	}
	
	public LabirintoBuilder addStanza(String nomeStanza) {
		Stanza stanza = new Stanza(nomeStanza);
		this.stanze.put(nomeStanza, stanza);
		this.ultima = stanza;
		return this;
	}
	
	public LabirintoBuilder addStanzaMagica(String nomeStanza) {
		Stanza stanza = new StanzaMagica(nomeStanza);
		this.stanze.put(nomeStanza, stanza);
		this.ultima = stanza;
		return this;
	}
	
	public LabirintoBuilder addStanzaBuia(String nomeStanza, String attrezzo) {
		Stanza stanza = new StanzaBuia(nomeStanza, attrezzo);
		this.stanze.put(nomeStanza, stanza);
		this.ultima = stanza;
		return this;
	}
	
	public LabirintoBuilder addStanzaBloccata(String nomeStanza, String direzione, String attrezzo) {
		Stanza stanza = new StanzaBloccata(nomeStanza,direzione,attrezzo);
		this.stanze.put(nomeStanza, stanza);
		this.ultima = stanza;
		return this;
	}
}