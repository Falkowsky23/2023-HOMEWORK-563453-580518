package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {

	private static final String STANZA_ADIACENTE_LIBERA = "stanzaAdiacenteLibera";
	private static final String STANZA_ADIACENTE_BLOCCATA = "stanzaAdiacenteBloccata";
	private static final Direzione DIREZIONE_BLOCCATA = Direzione.NORD;
	private static final Direzione DIREZIONE_LIBERA = Direzione.SUD;
	private static final String CHIAVE_TEST = "chiaveTest";
	private static final String STANZA_BLOCCATA = "StanzaBloccata";
	
	private Stanza stanzaBloccata;

	@Before
	public void setUp() {
		Stanza stanzaBloccata = new StanzaBloccata(STANZA_BLOCCATA, DIREZIONE_BLOCCATA, CHIAVE_TEST);
		this.stanzaBloccata = stanzaBloccata;
	}

	private void setStanzeAdiacenti(Stanza stanzaBloccata) {
		Stanza stanzaAdiacenteLibera = new Stanza(STANZA_ADIACENTE_LIBERA);
		Stanza stanzaAdiacenteBloccata = new Stanza(STANZA_ADIACENTE_BLOCCATA);
		stanzaBloccata.impostaStanzaAdiacente(DIREZIONE_BLOCCATA, stanzaAdiacenteBloccata);
		stanzaBloccata.impostaStanzaAdiacente(DIREZIONE_LIBERA, stanzaAdiacenteLibera);
	}
	
	@Test
	public void testGetStanzaAdiacenteBloccata() {
		setStanzeAdiacenti(this.stanzaBloccata);
		assertEquals(this.stanzaBloccata, this.stanzaBloccata.getStanzaAdiacente(DIREZIONE_BLOCCATA));
	}
	
	@Test
	public void testGetStanzaAdiacenteSbloccata() {
		setStanzeAdiacenti(this.stanzaBloccata);
		Attrezzo attrezzo = new Attrezzo(CHIAVE_TEST,1);
		this.stanzaBloccata.addAttrezzo(attrezzo);
		assertEquals(STANZA_ADIACENTE_BLOCCATA, this.stanzaBloccata.getStanzaAdiacente(DIREZIONE_BLOCCATA).getNome());
	}
	
	@Test
	public void testGetStanzaAdiacenteLibera() {
		setStanzeAdiacenti(this.stanzaBloccata);
		assertEquals(STANZA_ADIACENTE_LIBERA, this.stanzaBloccata.getStanzaAdiacente(DIREZIONE_LIBERA).getNome());
	}
	
	@Test
	public void testGetAttrezzoInesistente() {
		Attrezzo attrezzo = new Attrezzo("AttrezzoTest",1);
		this.stanzaBloccata.addAttrezzo(attrezzo);
		assertNull(this.stanzaBloccata.getAttrezzo("inesistente"));		
	}
}	