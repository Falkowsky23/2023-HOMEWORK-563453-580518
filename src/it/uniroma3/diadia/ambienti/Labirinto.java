package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class Labirinto {
	
	private Stanza stanzaCorrente;
	private Stanza stanzaVincente;
	
	public Labirinto() {
		creaLabirinto();
	}
	
	private void creaLabirinto() {

       //crea gli attrezzi /
        Attrezzo lanterna = new Attrezzo("lanterna",3);
        Attrezzo osso = new Attrezzo("osso",1);

        // crea stanze del labirinto /
        Stanza atrio = new Stanza("Atrio");
        Stanza aulaN11 = new Stanza("Aula N11");
        Stanza aulaN10 = new Stanza("Aula N10");
        Stanza laboratorio = new Stanza("Laboratorio Campus");
        Stanza biblioteca = new Stanza("Biblioteca");

       // collega le stanze /
        atrio.impostaStanzaAdiacente(Direzione.NORD, biblioteca);
        atrio.impostaStanzaAdiacente(Direzione.EST, aulaN11);
        atrio.impostaStanzaAdiacente(Direzione.SUD, aulaN10);
        atrio.impostaStanzaAdiacente(Direzione.OVEST, laboratorio);
        aulaN11.impostaStanzaAdiacente(Direzione.EST, laboratorio);
        aulaN11.impostaStanzaAdiacente(Direzione.OVEST, atrio);
        aulaN10.impostaStanzaAdiacente(Direzione.NORD, atrio);
        aulaN10.impostaStanzaAdiacente(Direzione.EST, aulaN11);
        aulaN10.impostaStanzaAdiacente(Direzione.OVEST, laboratorio);
        laboratorio.impostaStanzaAdiacente(Direzione.EST, atrio);
        laboratorio.impostaStanzaAdiacente(Direzione.OVEST, aulaN11);
        biblioteca.impostaStanzaAdiacente(Direzione.SUD, atrio);

       // pone gli attrezzi nelle stanze */
        aulaN10.addAttrezzo(lanterna);
        atrio.addAttrezzo(osso);

        // il gioco comincia nell'atrio
        this.stanzaCorrente = atrio;
        this.stanzaVincente = biblioteca;
    }


	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}

	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}

	public static class LabirintoBuilder {
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

		public LabirintoBuilder addAdiacenza(String partenza, String destinazione, Direzione direzione){
			Stanza stanzaPartenza = stanze.get(partenza);
			Stanza stanzaDestinazione = stanze.get(destinazione);
			stanzaPartenza.impostaStanzaAdiacente(direzione, stanzaDestinazione);
			return this;
		}

		public LabirintoBuilder addAttrezzo(String stanza,String attrezzo, int peso) {
			Stanza s = stanze.get(stanza);
			Attrezzo a = new Attrezzo(attrezzo, peso);
			s.addAttrezzo(a);
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

		public LabirintoBuilder addStanzaBloccata(String nomeStanza, Direzione direzione, String attrezzo) {
			Stanza stanza = new StanzaBloccata(nomeStanza,direzione,attrezzo);
			this.stanze.put(nomeStanza, stanza);
			this.ultima = stanza;
			return this;
		}

		public LabirintoBuilder addCane(String nomeCane, String descrizione, String cibo, Attrezzo regalo, String stanza) {
			Cane cane = new Cane(nomeCane, descrizione, cibo, regalo);
			Stanza stanzaCane = this.stanze.get(stanza);
			stanzaCane.setPersonaggio(cane);
			return this;
		}

		public LabirintoBuilder addMago(String nomeMago, String descrizione, Attrezzo regalo, String stanza) {
			Mago mago = new Mago(nomeMago, descrizione, regalo);
			Stanza stanzaMago = this.stanze.get(stanza);
			stanzaMago.setPersonaggio(mago);
			return this;
		}

		public LabirintoBuilder addStrega(String nomeStrega, String descrizione, String stanza) {
			Strega strega = new Strega(nomeStrega, descrizione);
			Stanza stanzaStrega = this.stanze.get(stanza);
			stanzaStrega.setPersonaggio(strega);
			return this;
		}

	}
	
	public static LabirintoBuilder newBuilder() {
		return new LabirintoBuilder();
	}
}
